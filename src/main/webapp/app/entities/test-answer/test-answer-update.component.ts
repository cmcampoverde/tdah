import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { ITestAnswer, TestAnswer } from 'app/shared/model/test-answer.model';
import { TestAnswerService } from './test-answer.service';
import { ITestEdah } from 'app/shared/model/test-edah.model';
import { TestEdahService } from 'app/entities/test-edah/test-edah.service';
import { IQuestion } from 'app/shared/model/question.model';
import { QuestionService } from 'app/entities/question/question.service';

type SelectableEntity = ITestEdah | IQuestion;

@Component({
  selector: 'jhi-test-answer-update',
  templateUrl: './test-answer-update.component.html',
})
export class TestAnswerUpdateComponent implements OnInit {
  isSaving = false;
  testedahs: ITestEdah[] = [];
  questions: IQuestion[] = [];

  editForm = this.fb.group({
    id: [],
    value: [],
    testEdahId: [],
    questionId: [],
  });

  constructor(
    protected testAnswerService: TestAnswerService,
    protected testEdahService: TestEdahService,
    protected questionService: QuestionService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ testAnswer }) => {
      this.updateForm(testAnswer);

      this.testEdahService.query().subscribe((res: HttpResponse<ITestEdah[]>) => (this.testedahs = res.body || []));

      this.questionService.query().subscribe((res: HttpResponse<IQuestion[]>) => (this.questions = res.body || []));
    });
  }

  updateForm(testAnswer: ITestAnswer): void {
    this.editForm.patchValue({
      id: testAnswer.id,
      value: testAnswer.value,
      testEdahId: testAnswer.testEdahId,
      questionId: testAnswer.questionId,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const testAnswer = this.createFromForm();
    if (testAnswer.id !== undefined) {
      this.subscribeToSaveResponse(this.testAnswerService.update(testAnswer));
    } else {
      this.subscribeToSaveResponse(this.testAnswerService.create(testAnswer));
    }
  }

  private createFromForm(): ITestAnswer {
    return {
      ...new TestAnswer(),
      id: this.editForm.get(['id'])!.value,
      value: this.editForm.get(['value'])!.value,
      testEdahId: this.editForm.get(['testEdahId'])!.value,
      questionId: this.editForm.get(['questionId'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ITestAnswer>>): void {
    result.subscribe(
      () => this.onSaveSuccess(),
      () => this.onSaveError()
    );
  }

  protected onSaveSuccess(): void {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError(): void {
    this.isSaving = false;
  }

  trackById(index: number, item: SelectableEntity): any {
    return item.id;
  }
}
