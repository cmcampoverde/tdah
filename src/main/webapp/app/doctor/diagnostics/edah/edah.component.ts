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
  displayedColumns: string[] = ['number', 'question', 'options', 'type'];

  questions?: IQuestion[];
  teacherEmail?: string;
  teacherName?: string;
  edahDetails?: string;

  noYetMessage = false;
  testAnswered = false;

  testEdah?: ITestEdah;

  answers?: ITestAnswer[];

  totalH = 0;
  totalDA = 0;
  totalTC = 0;

  results = [];

  centiles = [99, 98, 97, 96, 95, 9, 93, 92, 91, 90, 85, 80, 75, 70, 65, 60, 55, 50, 45, 40, 35, 30, 25, 20, 15, 10, 5];
  HValues = [13, 12, 11, -1, 10, -1, -1, -1, 9, -1, 8, 7, -1, 6, -1, 5, -1, 4, -1, -1, 3, -1, 2, -1, 1, -1, 0];
  DAValues = [13, -1, 12, 11, -1, -1, 10, -1, -1, 9, 8, 7, -1, 6, -1, 5, 4, -1, 3, -1, 2, -1, 1, -1, -1, 0, -1];
  TCValues = [22, 21, 20, 19, 18, -1, 17, -1, -1, 16, 14 /* -15  */, 13, 12, 11, 10, -1, 9, 8, 7, -1, 6, 5, 4, -1, 3, 2, 0 /* - 1 */];
  TTNO = [17 /* -30  */, 16, 15, 14, 13, -1, 12, -1, 11, 10, 9, 8, 7, 6, 5, -1, 4, 3, -1, 2, -1, 1, -1, -1, -1, 0, -1];
  GLOBAL = [
    36 /* -60  */,
    34 /* -35 */,
    33,
    31 /* - 32  */,
    30,
    29,
    28,
    27,
    26,
    24 /* - 25 */,
    22 /* - 23 */,
    19 /* - 21 */,
    18,
    17,
    15 /*  - 16 */,
    14,
    13,
    11 /* - 12 */,
    10,
    9,
    8,
    7,
    6,
    4 /* - 5 */,
    3,
    2,
    0 /* - 1*/,
  ];

  HIndex = 0;
  DAIndex = 0;
  TCIndex = 0;
  TTNOIndex = 0;
  GLOBALIndex = 0;

  rH = '';
  rDA = '';
  rTC = '';
  rTTNO = '';
  rGLOBAL = '';

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
      this.questions = response.body as IQuestion[];
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
    if (this.patientService.currentPatient) {
      // eslint-disable-next-line no-console
      console.log(this.patientService.currentPatient.id);
      this.testEdahService.query({ 'patientId.equals': this.patientService.currentPatient.id }).subscribe(response => {
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
    }
  };

  loadAnswers = (testId: number | undefined) => {
    this.testAnswerService.query({ 'testEdahId.equals': testId }).subscribe(response => {
      if (response.ok) {
        this.answers = response.body as ITestAnswer[];
        // eslint-disable-next-line no-console
        console.log(this.answers);
        this.calculateEdahValues();
      }
    });
  };

  calculateEdahValues = () => {
    this.totalDA = 0;
    this.totalH = 0;
    this.totalTC = 0;
    let question: IQuestion | undefined;
    if (this.answers) {
      this.answers.map(answer => {
        if (this.questions) {
          question = this.questions.find(q => q.id === answer.questionId);
          if (question!.type === 'H') this.totalH += answer.value!;
          if (question!.type === 'DA') this.totalDA += answer.value!;
          if (question!.type === 'TC') this.totalTC += answer.value!;
        }
      });
      this.evaluateBaremo();
    }
  };

  evaluateBaremo(): void {
    this.evaluateH();
    this.rH = this.totalH > 10 ? 'Riesgo Elevado' : 'Sin Riesgo';
    this.evaluateDA();
    this.rDA = this.totalDA > 10 ? 'Riesgo Elevado' : 'Sin Riesgo';
    this.evaluateTC();
    this.rTC = this.totalTC > 18 ? 'Riesgo Elevado' : 'Sin Riesgo';
    this.evaluateTTNO();
    this.rTTNO = this.totalH + this.totalDA > 11 ? 'Riesgo Elevado' : 'Sin Riesgo';
    this.evaluateGLOBAL();
    this.rGLOBAL = this.totalH + this.totalDA + this.totalTC > 30 ? 'Riesgo Elevado' : 'Sin Riesgo';
  }

  evaluateH(): void {
    if (this.totalH >= 13 && this.totalH <= 15) {
      this.HIndex = 0;
      return;
    }
    this.HValues.map((h, index) => {
      if (h === this.totalH) this.HIndex = index;
    });
  }

  evaluateDA(): void {
    if (this.totalDA >= 13 && this.totalDA <= 15) {
      this.DAIndex = 0;
      return;
    }
    this.DAValues.map((da, index) => {
      if (da === this.totalDA) this.DAIndex = index;
    });
  }

  evaluateTC(): void {
    if (this.totalTC >= 22 && this.totalTC <= 30) {
      this.TCIndex = 0;
      return;
    }
    if (this.totalTC >= 14 && this.totalTC <= 15) {
      this.TCIndex = 10;
      return;
    }
    if (this.totalTC >= 0 && this.totalTC <= 1) {
      this.TCIndex = this.TCValues.length - 1;
      return;
    }
    this.TCValues.map((tc, index) => {
      if (tc === this.totalTC) this.TCIndex = index;
    });
  }

  evaluateTTNO(): void {
    const sumHDa = this.totalH + this.totalDA;
    if (sumHDa >= 17 && sumHDa <= 30) {
      this.TCIndex = 0;
      return;
    }
    this.TTNO.map((ttno, index) => {
      if (ttno === sumHDa) this.TTNOIndex = index;
    });
  }

  evaluateGLOBAL(): void {
    const sumHDaTc = this.totalH + this.totalDA + this.totalTC;
    if (sumHDaTc >= 36 && sumHDaTc <= 60) {
      this.TCIndex = 0;
      return;
    }
    if (sumHDaTc >= 34 && sumHDaTc <= 35) {
      this.TCIndex = 1;
      return;
    }
    if (sumHDaTc >= 31 && sumHDaTc <= 32) {
      this.TCIndex = 3;
      return;
    }
    if (sumHDaTc >= 22 && sumHDaTc <= 23) {
      this.TCIndex = 10;
      return;
    }
    if (sumHDaTc >= 19 && sumHDaTc <= 21) {
      this.TCIndex = 11;
      return;
    }
    if (sumHDaTc >= 15 && sumHDaTc <= 16) {
      this.TCIndex = 14;
      return;
    }
    if (sumHDaTc >= 11 && sumHDaTc <= 12) {
      this.TCIndex = 17;
      return;
    }
    if (sumHDaTc >= 4 && sumHDaTc <= 5) {
      this.TCIndex = 23;
      return;
    }
    if (sumHDaTc >= 0 && sumHDaTc <= 1) {
      this.TCIndex = 26;
      return;
    }
    this.GLOBAL.map((global, index) => {
      if (global === sumHDaTc) this.GLOBALIndex = index;
    });
  }
}
