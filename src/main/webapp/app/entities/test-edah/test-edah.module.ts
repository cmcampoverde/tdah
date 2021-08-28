import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TdahSharedModule } from 'app/shared/shared.module';
import { TestEdahComponent } from './test-edah.component';
import { TestEdahDetailComponent } from './test-edah-detail.component';
import { TestEdahUpdateComponent } from './test-edah-update.component';
import { TestEdahDeleteDialogComponent } from './test-edah-delete-dialog.component';
import { testEdahRoute } from './test-edah.route';

@NgModule({
  imports: [TdahSharedModule, RouterModule.forChild(testEdahRoute)],
  declarations: [TestEdahComponent, TestEdahDetailComponent, TestEdahUpdateComponent, TestEdahDeleteDialogComponent],
  entryComponents: [TestEdahDeleteDialogComponent],
})
export class TdahTestEdahModule {}
