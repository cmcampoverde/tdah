import { Component, OnInit } from '@angular/core';

import { IQuestion } from '../../shared/model/question.model';
import { QuestionService } from '../../entities/question/question.service';
import { ActivatedRoute } from '@angular/router';
import { TestEdahService } from '../../entities/test-edah/test-edah.service';
import { ITestEdah } from '../../shared/model/test-edah.model';
import { PatientService } from '../../entities/patient/patient.service';
import { IPatient } from '../../shared/model/patient.model';
import { TestAnswer } from '../../shared/model/test-answer.model';
import { TestAnswerService } from '../../entities/test-answer/test-answer.service';

@Component({
  selector: 'jhi-edah-test',
  templateUrl: './edahTest.component.html',
  styleUrls: ['./edahTest.component.scss'],
})
export class EdahTestComponent implements OnInit {
  doNotMatch = false;
  error = false;
  success = false;

  displayedColumns: string[] = ['question', 'options'];

  dataSource?: IQuestion[];
  key?: string;

  testEdah?: ITestEdah;
  patient?: IPatient;
  answers: TestAnswer[] = [];

  testAnswered = false;
  successMessage = false;

  constructor(
    private questionService: QuestionService,
    private route: ActivatedRoute,
    private patientService: PatientService,
    private testAnswerService: TestAnswerService,
    private testEdahService: TestEdahService
  ) {}

  ngOnInit(): void {
    this.route.queryParams.subscribe(params => {
      if (params['key']) {
        this.key = params['key'];
        this.loadEdah();
      }
    });
  }

  loadEdah = () => {
    this.questionService.readQuestions().subscribe(response => {
      // eslint-disable-next-line no-console
      console.log(response);
      this.dataSource = response.body as IQuestion[];
      this.loadDataTestEdah();
    });
  };

  loadDataTestEdah = () => {
    this.testEdahService.readTestEdahs({ 'key.equals': this.key }).subscribe(response => {
      // eslint-disable-next-line no-console
      console.log(response);
      const testEdahs = response.body as ITestEdah[];
      this.testEdah = testEdahs.pop();
      if (this.testEdah) {
        this.testAnswered = this.testEdah.answered ?? false;
        this.loadPatientData(this.testEdah.patientId);
        this.buildAnswers();
      }
    });
  };

  loadPatientData = (test: number | undefined) => {
    if (test) {
      this.patientService.readPatient({ 'id.equals': test }).subscribe(response => {
        // eslint-disable-next-line no-console
        console.log(response);
        this.patient = (response.body as IPatient[]).pop();
      });
    }
  };

  buildAnswers = () => {
    this.answers = [];
    if (this.dataSource)
      this.dataSource.map(question => {
        const answer = new TestAnswer();
        if (!this.testEdah) return;
        answer.testEdahId = this.testEdah.id;
        answer.questionId = question.id;
        answer.value = 0;
        this.answers.push(answer);
      });
    // eslint-disable-next-line no-console
    console.log(this.answers);
  };

  sendAnswers = () => {
    // eslint-disable-next-line no-console
    console.log(this.answers);
    this.answers.map(answer => [
      this.testAnswerService.insertAnswer(answer).subscribe(success => {
        // eslint-disable-next-line no-console
        console.log(success);
      }),
    ]);
    if (!this.testEdah) return;
    this.testEdah.answered = true;
    this.testEdahService.updatePublic(this.testEdah).subscribe(response => {
      if (response.ok) {
        this.successMessage = true;
      }
    });
  };
}
