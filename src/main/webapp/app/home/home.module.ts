import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TdahSharedModule } from 'app/shared/shared.module';
import { HOME_ROUTE } from './home.route';
import { HomeComponent } from './home.component';
import { JhMaterialModule } from 'app/JhMaterialModule.module';

@NgModule({
  imports: [TdahSharedModule, RouterModule.forChild([HOME_ROUTE]), JhMaterialModule],
  declarations: [HomeComponent],
})
export class TdahHomeModule {}
