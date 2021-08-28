import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { ITestEdah, TestEdah } from 'app/shared/model/test-edah.model';
import { TestEdahService } from './test-edah.service';
import { TestEdahComponent } from './test-edah.component';
import { TestEdahDetailComponent } from './test-edah-detail.component';
import { TestEdahUpdateComponent } from './test-edah-update.component';

@Injectable({ providedIn: 'root' })
export class TestEdahResolve implements Resolve<ITestEdah> {
  constructor(private service: TestEdahService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ITestEdah> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((testEdah: HttpResponse<TestEdah>) => {
          if (testEdah.body) {
            return of(testEdah.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new TestEdah());
  }
}

export const testEdahRoute: Routes = [
  {
    path: '',
    component: TestEdahComponent,
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'tdahApp.testEdah.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: TestEdahDetailComponent,
    resolve: {
      testEdah: TestEdahResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'tdahApp.testEdah.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: TestEdahUpdateComponent,
    resolve: {
      testEdah: TestEdahResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'tdahApp.testEdah.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: TestEdahUpdateComponent,
    resolve: {
      testEdah: TestEdahResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'tdahApp.testEdah.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
