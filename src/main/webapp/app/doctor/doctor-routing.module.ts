import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { FormsModule } from '@angular/forms';

/* jhipster-needle-add-admin-module-import - JHipster will add admin modules imports here */

@NgModule({
  imports: [
    FormsModule,
    /* jhipster-needle-add-admin-module - JHipster will add admin modules here */
    RouterModule.forChild([
      {
        path: 'children',
        loadChildren: () => import('./children/children.module').then(m => m.ChildrenModule),
        data: {
          pageTitle: 'doctor.title',
        },
      },
      {
        path: 'reports',
        loadChildren: () => import('./reports/reports.module').then(m => m.ReportsModule),
      },
      {
        path: 'diagnostics',
        loadChildren: () => import('./diagnostics/diagnostics.module').then(m => m.DiagnosticsModule),
      },
    ]),
  ],
})
export class DoctorRoutingModule {}
