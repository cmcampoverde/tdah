import { Component, OnInit } from '@angular/core';
import { QuestionService } from '../../../entities/question/question.service';
import { IQuestion } from '../../../shared/model/question.model';
import { TestEdahService } from '../../../entities/test-edah/test-edah.service';
import { TestEdah } from '../../../shared/model/test-edah.model';
import { PatientService } from '../../../entities/patient/patient.service';

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

  constructor(
    protected questionService: QuestionService,
    protected testEdahService: TestEdahService,
    protected patientService: PatientService
  ) {}

  ngOnInit(): void {
    this.loadEdah();
  }

  loadEdah = () => {
    this.questionService.query().subscribe((response: { body: IQuestion[]; }) => {
      this.dataSource = response.body as IQuestion[];
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
}
