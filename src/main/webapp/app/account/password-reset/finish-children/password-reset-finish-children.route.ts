import { Route } from '@angular/router';

import { PasswordResetFinishChildrenComponent } from './password-reset-finish-children.component';

export const passwordResetFinishChildrenRoute: Route = {
  path: 'reset-children/finish',
  component: PasswordResetFinishChildrenComponent,
  data: {
    authorities: [],
    pageTitle: 'global.menu.account.password',
  },
};
