import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { JhMaterialModule } from '../../JhMaterialModule.module';
import { RouterModule } from '@angular/router';
import { diagnosticsRoute } from './diagnostics.route';
import { DiagnosticsComponent } from './diagnostics.component';
import { ProfileComponent } from './profile/profile.component';
import { EdahComponent } from './edah/edah.component';
import { PsicologoComponent } from './psicologo/psicologo.component';
import { EducacionComponent } from './educacion/educacion.component';
import { TdahSharedModule } from '../../shared/shared.module';

@NgModule({
  declarations: [DiagnosticsComponent, ProfileComponent, EdahComponent, PsicologoComponent, EducacionComponent],
  imports: [CommonModule, JhMaterialModule, RouterModule.forChild([diagnosticsRoute]), TdahSharedModule],
})
export class DiagnosticsModule {}
