import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'patient',
        loadChildren: () => import('./patient/patient.module').then(m => m.TdahPatientModule),
      },
      {
        path: 'question',
        loadChildren: () => import('./question/question.module').then(m => m.TdahQuestionModule),
      },
      {
        path: 'test-edah',
        loadChildren: () => import('./test-edah/test-edah.module').then(m => m.TdahTestEdahModule),
      },
      {
        path: 'user',
        loadChildren: () => import('./user/user.module').then(m => m.TdahUserModule),
      },
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ]),
  ],
})
export class TdahEntityModule {}
