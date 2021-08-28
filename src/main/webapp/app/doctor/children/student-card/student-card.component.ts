import { Component, Input, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { IPatient } from 'app/shared/model/patient.model';
import { PatientService } from '../../../entities/patient/patient.service';

@Component({
  selector: 'jhi-student-card',
  templateUrl: './student-card.component.html',
  styleUrls: ['./student-card.component.scss'],
})
export class StudentCardComponent implements OnInit {
  @Input() patient?: IPatient;

  constructor(private router: Router, private patientService: PatientService) {}

  ngOnInit(): void {}

  openDiagnostic(): void {
    this.patientService.currentPatient = this.patient;
    this.router.navigate(['/doctor/diagnostics']);
  }
}
