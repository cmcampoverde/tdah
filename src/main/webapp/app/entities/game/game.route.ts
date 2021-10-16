import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRouteSnapshot, Resolve, Router, Routes } from '@angular/router';
import { EMPTY, Observable, of } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Game, IGame } from 'app/shared/model/game.model';
import { GameService } from './game.service';
import { GameComponent } from './game.component';
import { GameDetailComponent } from './game-detail.component';

@Injectable({ providedIn: 'root' })
export class GameResolve implements Resolve<IGame> {
  constructor(private service: GameService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IGame> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((game: HttpResponse<Game>) => {
          if (game.body) {
            return of(game.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new Game());
  }
}

export const gameRoute: Routes = [
  {
    path: '',
    component: GameComponent,
    data: {
      authorities: [Authority.USER, Authority.DOCTOR],
      defaultSort: 'id,asc',
      pageTitle: 'tdahApp.game.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: GameDetailComponent,
    resolve: {
      game: GameResolve,
    },
    data: {
      authorities: [Authority.USER, Authority.DOCTOR],
      pageTitle: 'tdahApp.game.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
