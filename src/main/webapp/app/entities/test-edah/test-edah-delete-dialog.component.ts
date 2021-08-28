import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ITestEdah } from 'app/shared/model/test-edah.model';
import { TestEdahService } from './test-edah.service';

@Component({
  templateUrl: './test-edah-delete-dialog.component.html',
})
export class TestEdahDeleteDialogComponent {
  testEdah?: ITestEdah;

  constructor(protected testEdahService: TestEdahService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.testEdahService.delete(id).subscribe(() => {
      this.eventManager.broadcast('testEdahListModification');
      this.activeModal.close();
    });
  }
}
