import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ChildrenComponent } from './children.component';
import { RouterModule } from '@angular/router';
import { childrenRoute } from './children.route';
import { StudentCardComponent } from './student-card/student-card.component';
import { JhMaterialModule } from '../../JhMaterialModule.module';
import { FlexLayoutModule } from '@angular/flex-layout';

@NgModule({
  declarations: [ChildrenComponent, StudentCardComponent],
  imports: [CommonModule, JhMaterialModule, FlexLayoutModule, RouterModule.forChild([childrenRoute])],
})
export class ChildrenModule {}
