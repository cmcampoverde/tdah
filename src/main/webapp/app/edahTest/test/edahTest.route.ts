import { Route } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { EdahTestComponent } from './edahTest.component';
import { Authority } from 'app/shared/constants/authority.constants';

export const edahTestRoute: Route = {
  path: 'test-edah',
  component: EdahTestComponent,
  data: {
    authorities: [],
    pageTitle: 'global.menu.account.password',
  },
  canActivate: [UserRouteAccessService],
};
