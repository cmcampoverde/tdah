import { Component, OnInit } from '@angular/core';
import { IPatient } from '../../../shared/model/patient.model';
import { PatientService } from '../../../entities/patient/patient.service';

@Component({
  selector: 'jhi-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.scss'],
})
export class ProfileComponent implements OnInit {
  patient?: IPatient;

  constructor(private patientService: PatientService) {}

  ngOnInit(): void {
    this.loadPatientData();
  }

  loadPatientData = () => {
    this.patient = this.patientService.currentPatient;
  };
}
