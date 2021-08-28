import { Route } from '@angular/router';
import { DiagnosticsComponent } from './diagnostics.component';

export const diagnosticsRoute: Route = {
  path: '',
  component: DiagnosticsComponent,
  data: {
    pageTitle: 'doctor.title',
  },
};
