import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ReportsComponent } from './reports.component';
import { RouterModule } from '@angular/router';
import { reportsRoute } from './reports.route';
import { JhMaterialModule } from '../../JhMaterialModule.module';

@NgModule({
  declarations: [ReportsComponent],
  imports: [CommonModule, JhMaterialModule, RouterModule.forChild([reportsRoute])],
})
export class ReportsModule {}
