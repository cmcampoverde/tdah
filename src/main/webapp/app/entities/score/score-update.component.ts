import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IScore, Score } from 'app/shared/model/score.model';
import { ScoreService } from './score.service';
import { IPatient } from 'app/shared/model/patient.model';
import { PatientService } from 'app/entities/patient/patient.service';
import { IGame } from 'app/shared/model/game.model';
import { GameService } from 'app/entities/game/game.service';

type SelectableEntity = IPatient | IGame;

@Component({
  selector: 'jhi-score-update',
  templateUrl: './score-update.component.html',
})
export class ScoreUpdateComponent implements OnInit {
  isSaving = false;
  patients: IPatient[] = [];
  games: IGame[] = [];

  editForm = this.fb.group({
    id: [],
    time: [],
    level: [],
    creationDate: [],
    patientId: [],
    gameId: [],
  });

  constructor(
    protected scoreService: ScoreService,
    protected patientService: PatientService,
    protected gameService: GameService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ score }) => {
      if (!score.id) {
        const today = moment().startOf('day');
        score.creation_date = today;
      }

      this.updateForm(score);

      this.patientService.query().subscribe((res: HttpResponse<IPatient[]>) => (this.patients = res.body || []));

      this.gameService.query().subscribe((res: HttpResponse<IGame[]>) => (this.games = res.body || []));
    });
  }

  updateForm(score: IScore): void {
    this.editForm.patchValue({
      id: score.id,
      time: score.time,
      level: score.level,
      creationDate: score.creationDate ? score.creationDate.format(DATE_TIME_FORMAT) : null,
      patientId: score.patientId,
      gameId: score.gameId,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const score = this.createFromForm();
    if (score.id !== undefined) {
      this.subscribeToSaveResponse(this.scoreService.update(score));
    } else {
      this.subscribeToSaveResponse(this.scoreService.create(score));
    }
  }

  private createFromForm(): IScore {
    return {
      ...new Score(),
      id: this.editForm.get(['id'])!.value,
      time: this.editForm.get(['time'])!.value,
      level: this.editForm.get(['level'])!.value,
      creationDate: this.editForm.get(['creation_date'])!.value
        ? moment(this.editForm.get(['creation_date'])!.value, DATE_TIME_FORMAT)
        : undefined,
      patientId: this.editForm.get(['patientId'])!.value,
      gameId: this.editForm.get(['gameId'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IScore>>): void {
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

  trackById(index: number, item: SelectableEntity): any {
    return item.id;
  }
}
