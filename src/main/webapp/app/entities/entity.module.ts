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
      {
        path: 'game',
        loadChildren: () => import('./game/game.module').then(m => m.TdahGameModule),
      },
      {
        path: 'score',
        loadChildren: () => import('./score/score.module').then(m => m.TdahScoreModule),
      },
      {
        path: 'test-answer',
        loadChildren: () => import('./test-answer/test-answer.module').then(m => m.TdahTestAnswerModule),
      },
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ]),
  ],
})
export class TdahEntityModule {}
