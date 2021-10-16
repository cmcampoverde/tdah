import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TdahSharedModule } from 'app/shared/shared.module';

import { edahTestState } from './edahTest.route';
import { EdahTestComponent } from './test/edahTest.component';
import { MatTableModule } from '@angular/material/table';
import { MatRadioModule } from '@angular/material/radio';
import { MatCardModule } from '@angular/material/card';

@NgModule({
  imports: [TdahSharedModule, RouterModule.forChild(edahTestState), MatTableModule, MatRadioModule, MatCardModule],
  declarations: [EdahTestComponent],
})
export class EdahTestModule {}
