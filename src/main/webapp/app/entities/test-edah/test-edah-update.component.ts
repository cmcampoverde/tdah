import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { ITestEdah, TestEdah } from 'app/shared/model/test-edah.model';
import { TestEdahService } from './test-edah.service';
import { IPatient } from 'app/shared/model/patient.model';
import { PatientService } from 'app/entities/patient/patient.service';

@Component({
  selector: 'jhi-test-edah-update',
  templateUrl: './test-edah-update.component.html',
})
export class TestEdahUpdateComponent implements OnInit {
  isSaving = false;
  patients: IPatient[] = [];

  editForm = this.fb.group({
    id: [],
    teacherEmail: [],
    answered: [],
    key: [],
    teacherName: [],
    instructions: [],
    patientId: [],
  });

  constructor(
    protected testEdahService: TestEdahService,
    protected patientService: PatientService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ testEdah }) => {
      this.updateForm(testEdah);

      this.patientService.query().subscribe((res: HttpResponse<IPatient[]>) => (this.patients = res.body || []));
    });
  }

  updateForm(testEdah: ITestEdah): void {
    this.editForm.patchValue({
      id: testEdah.id,
      teacherEmail: testEdah.teacherEmail,
      answered: testEdah.answered,
      key: testEdah.key,
      teacherName: testEdah.teacherName,
      instructions: testEdah.instructions,
      patientId: testEdah.patientId,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const testEdah = this.createFromForm();
    if (testEdah.id !== undefined) {
      this.subscribeToSaveResponse(this.testEdahService.update(testEdah));
    } else {
      this.subscribeToSaveResponse(this.testEdahService.create(testEdah));
    }
  }

  private createFromForm(): ITestEdah {
    return {
      ...new TestEdah(),
      id: this.editForm.get(['id'])!.value,
      teacherEmail: this.editForm.get(['teacherEmail'])!.value,
      answered: this.editForm.get(['answered'])!.value,
      key: this.editForm.get(['key'])!.value,
      teacherName: this.editForm.get(['teacherName'])!.value,
      instructions: this.editForm.get(['instructions'])!.value,
      patientId: this.editForm.get(['patientId'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ITestEdah>>): void {
    result.subscribe(
      () => this.onSaveSuccess(),
      () => this.onSaveError()
    );
  }

  protected onSaveSuccess(): void {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError(): void {
    this.isSaving = false;
  }

  trackById(index: number, item: IPatient): any {
    return item.id;
  }
}
