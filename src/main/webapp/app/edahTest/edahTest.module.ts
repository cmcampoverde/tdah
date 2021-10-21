import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { edahTestState } from './edahTest.route';
import { EdahTestComponent } from './test/edahTest.component';
import { MatTableModule } from '@angular/material/table';
import { MatRadioModule } from '@angular/material/radio';
import { MatCardModule } from '@angular/material/card';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { NgJhipsterModule } from 'ng-jhipster';

@NgModule({
  imports: [
    RouterModule.forChild(edahTestState),
    MatTableModule,
    MatRadioModule,
    MatCardModule,
    FormsModule,
    CommonModule,
    NgJhipsterModule,
  ],
  declarations: [EdahTestComponent],
})
export class EdahTestModule {}
