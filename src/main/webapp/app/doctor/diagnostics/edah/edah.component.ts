import { Component, OnInit } from '@angular/core';
import { QuestionService } from '../../../entities/question/question.service';
import { IQuestion } from '../../../shared/model/question.model';
import { TestEdahService } from '../../../entities/test-edah/test-edah.service';
import { ITestEdah, TestEdah } from '../../../shared/model/test-edah.model';
import { PatientService } from '../../../entities/patient/patient.service';
import { TestAnswerService } from '../../../entities/test-answer/test-answer.service';
import { ITestAnswer } from '../../../shared/model/test-answer.model';

@Component({
  selector: 'jhi-edah',
  templateUrl: './edah.component.html',
  styleUrls: ['./edah.component.scss'],
})
export class EdahComponent implements OnInit {
  displayedColumns: string[] = ['question', 'options', 'type'];

  dataSource?: IQuestion[];
  teacherEmail?: string;
  teacherName?: string;
  edahDetails?: string;

  noYetMessage = false;
  testAnswered = false;

  testEdah?: ITestEdah;

  answers?: ITestAnswer[];

  constructor(
    protected questionService: QuestionService,
    protected testEdahService: TestEdahService,
    private testAnswerService: TestAnswerService,
    protected patientService: PatientService
  ) {}

  ngOnInit(): void {
    this.loadEdah();
  }

  loadEdah = () => {
    this.questionService.query().subscribe(response => {
      this.dataSource = response.body as IQuestion[];
      this.loadTestAnswered();
    });
  };

  sendEdahEmail = () => {
    const testEdah = new TestEdah();
    testEdah.teacherName = this.teacherName;
    testEdah.teacherEmail = this.teacherEmail;
    testEdah.instructions = this.edahDetails;
    const patientServiceAux = this.patientService.currentPatient;
    testEdah.patientId = patientServiceAux ? patientServiceAux.id : 0;
    this.testEdahService.sendEmailEdah(testEdah).subscribe((response: any) => {
      // eslint-disable-next-line no-console
      console.log(response);
    });
  };

  loadTestAnswered = () => {
    if (this.patientService.currentPatient)
      this.testEdahService.query({ 'patient_id.equals': this.patientService.currentPatient.id }).subscribe(response => {
        if (response.ok) {
          const testEdahs = response.body as ITestEdah[];
          this.testEdah = testEdahs.pop();
          if (this.testEdah) {
            this.testAnswered = this.testEdah.answered ?? false;
            if (this.testAnswered) this.loadAnswers(this.testEdah.id);
          }
        } else {
          this.noYetMessage = true;
        }
      });
  };

  loadAnswers = (testId: number | undefined) => {
    this.testAnswerService.query({ 'test_id.equals': testId }).subscribe(response => {
      // eslint-disable-next-line no-console
      console.log(response);
      if (response.ok) {
        this.answers = response.body as ITestAnswer[];
      }
    });
  };
}
