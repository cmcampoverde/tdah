import { Routes } from '@angular/router';
import { edahTestRoute } from './test/edahTest.route';

const EDAH_TEST_ROUTES = [edahTestRoute];

export const edahTestState: Routes = [
  {
    path: '',
    children: EDAH_TEST_ROUTES,
  },
];
