import { Component, OnInit } from '@angular/core';
import { IPatient } from '../../shared/model/patient.model';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';
import { PatientService } from '../../entities/patient/patient.service';
import { Subscription } from 'rxjs';
import { ITEMS_PER_PAGE } from '../../shared/constants/pagination.constants';
import { ScoreService } from '../../entities/score/score.service';
import { IResultLower } from '../../shared/model/result-lower.model';

@Component({
  selector: 'jhi-reports',
  templateUrl: './reports.component.html',
  styleUrls: ['./reports.component.scss'],
})
export class ReportsComponent implements OnInit {
  displayedColumns: string[] = ['name', 'age'];
  isLoadingResults = false;

  patients?: IPatient[];
  eventSubscriber?: Subscription;
  totalItems = 0;
  itemsPerPage = ITEMS_PER_PAGE;
  page!: number;
  predicate!: string;
  ascending!: boolean;
  ngbPaginationPage = 1;

  resultLowers?: IResultLower[];

  constructor(
    protected activatedRoute: ActivatedRoute,
    protected patientService: PatientService,
    protected router: Router,
    protected scoreService: ScoreService
  ) {}

  ngOnInit(): void {
    this.loadPage();
  }

  loadPage(): void {
    this.patientService
      .query({
        size: this.itemsPerPage,
      })
      .subscribe(
        (res: HttpResponse<IPatient[]>) => this.onSuccess(res.body, res.headers),
        () => this.onError()
      );
  }

  sort(): string[] {
    const result = [this.predicate + ',' + (this.ascending ? 'asc' : 'desc')];
    if (this.predicate !== 'id') {
      result.push('id');
    }
    return result;
  }

  protected onSuccess(data: IPatient[] | null, headers: HttpHeaders): void {
    this.totalItems = Number(headers.get('X-Total-Count'));
    this.patients = data || [];
    this.ngbPaginationPage = this.page;
  }

  protected onError(): void {
    this.ngbPaginationPage = this.page ?? 1;
  }

  selectPatient(patient: IPatient): void {
    this.scoreService.getLowerScores(patient.id).subscribe(response => {
      if (response.ok) {
        if (response.body != null) this.resultLowers = response.body;
      }
    });
  }
}
