import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ITestAnswer } from 'app/shared/model/test-answer.model';

@Component({
  selector: 'jhi-test-answer-detail',
  templateUrl: './test-answer-detail.component.html',
})
export class TestAnswerDetailComponent implements OnInit {
  testAnswer: ITestAnswer | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ testAnswer }) => (this.testAnswer = testAnswer));
  }

  previousState(): void {
    window.history.back();
  }
}
