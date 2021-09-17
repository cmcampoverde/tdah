import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TdahSharedModule } from 'app/shared/shared.module';
import { GameComponent } from './game.component';
import { GameDetailComponent } from './game-detail.component';
import { gameRoute } from './game.route';

@NgModule({
  imports: [TdahSharedModule, RouterModule.forChild(gameRoute)],
  declarations: [GameComponent, GameDetailComponent],
})
export class TdahGameModule {}
