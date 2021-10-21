import { Route } from '@angular/router';

import { EdahTestComponent } from './edahTest.component';

export const edahTestRoute: Route = {
  path: 'test-edah',
  component: EdahTestComponent,
  data: {
    authorities: [],
    pageTitle: 'Test EDAH',
  },
};
