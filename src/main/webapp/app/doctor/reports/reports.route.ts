import { Route } from '@angular/router';
import { ReportsComponent } from './reports.component';

export const reportsRoute: Route = {
  path: '',
  component: ReportsComponent,
  data: {
    pageTitle: 'doctor.title',
  },
};
