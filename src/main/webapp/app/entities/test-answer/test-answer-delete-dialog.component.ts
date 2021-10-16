import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ITestAnswer } from 'app/shared/model/test-answer.model';
import { TestAnswerService } from './test-answer.service';

@Component({
  templateUrl: './test-answer-delete-dialog.component.html',
})
export class TestAnswerDeleteDialogComponent {
  testAnswer?: ITestAnswer;

  constructor(
    protected testAnswerService: TestAnswerService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.testAnswerService.delete(id).subscribe(() => {
      this.eventManager.broadcast('testAnswerListModification');
      this.activeModal.close();
    });
  }
}
