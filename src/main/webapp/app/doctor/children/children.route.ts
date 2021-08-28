import { Route } from '@angular/router';

import { ChildrenComponent } from './children.component';

export const childrenRoute: Route = {
  path: '',
  component: ChildrenComponent,
  data: {
    pageTitle: 'doctor.title',
  },
};
