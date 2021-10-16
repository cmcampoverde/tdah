import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TdahSharedModule } from 'app/shared/shared.module';
import { TestAnswerComponent } from './test-answer.component';
import { TestAnswerDetailComponent } from './test-answer-detail.component';
import { TestAnswerUpdateComponent } from './test-answer-update.component';
import { TestAnswerDeleteDialogComponent } from './test-answer-delete-dialog.component';
import { testAnswerRoute } from './test-answer.route';

@NgModule({
  imports: [TdahSharedModule, RouterModule.forChild(testAnswerRoute)],
  declarations: [TestAnswerComponent, TestAnswerDetailComponent, TestAnswerUpdateComponent, TestAnswerDeleteDialogComponent],
  entryComponents: [TestAnswerDeleteDialogComponent],
})
export class TdahTestAnswerModule {}
