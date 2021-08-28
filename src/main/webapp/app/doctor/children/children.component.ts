import { Component, OnInit } from '@angular/core';
import { PatientService } from '../../entities/patient/patient.service';
import { ActivatedRoute, Router } from '@angular/router';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { IPatient } from '../../shared/model/patient.model';
import { Subscription } from 'rxjs';
import { ITEMS_PER_PAGE } from '../../shared/constants/pagination.constants';

@Component({
  selector: 'jhi-children',
  templateUrl: './children.component.html',
  styleUrls: ['./children.component.scss'],
})
export class ChildrenComponent implements OnInit {
  children = [
    {
      name: 'Carlos Campoverde',
      edge: 7,
      diagnostic:
        'Está inquieto o da golpecitos con las manos o los pies, o se retuerce en el asiento\n' +
        'Le cuesta permanecer sentado en el aula o en otras situaciones\n' +
        'Está en constante movimiento\n' +
        'Va de un lado para otro o trepa en situaciones no apropiadas',
      tutor: 'Sr. Campoverde',
    },
    {
      name: 'Joel Cabrera',
      edge: 6,
      diagnostic:
        'Tiene problemas para jugar o realizar actividades tranquilas\n' +
        'Habla demasiado\n' +
        'Da respuestas apresuradas o interrumpe a quien le hace preguntas\n' +
        'Tiene dificultades para esperar su turno',
      tutor: 'Sr. Campoverde',
    },
    {
      name: 'Kathya Velaszques',
      edge: 8,
      diagnostic:
        'Tiene dificultades para esperar su turno\n' + 'Interrumpe conversaciones, juegos o actividades de otros, o se entromete en ellas',
      tutor: 'Sr. Campoverde',
    },
    {
      name: 'Manuel Ordoñez',
      edge: 8,
      diagnostic:
        'Va de un lado para otro o trepa en situaciones no apropiadas\n' +
        'Tiene problemas para jugar o realizar actividades tranquilas\n' +
        'Habla demasiado',
      tutor: 'Sr. Campoverde',
    },
    {
      name: 'Sofia Andrade',
      edge: 7,
      diagnostic:
        'TDLe cuesta permanecer sentado en el aula o en otras situaciones\n' +
        'Está en constante movimiento\n' +
        'Va de un lado para otro o trepa en situaciones no apropiadasAH',
      tutor: 'Sr. Campoverde',
    },
    {
      name: 'Jhon Smith',
      edge: 6,
      diagnostic:
        'Tiene problemas para jugar o realizar actividades tranquilas\n' +
        'Habla demasiado\n' +
        'Da respuestas apresuradas o interrumpe a quien le hace preguntas',
      tutor: 'Sr. Campoverde',
    },
  ];

  isGridView = true;

  displayedColumns: string[] = ['number', 'edge', 'name', 'diagnostic', 'tutor'];
  isLoadingResults = false;

  textButtonGridView = this.isGridView ? 'Ver en Lista' : 'Ver Cuadrícula';

  patients?: IPatient[];
  eventSubscriber?: Subscription;
  totalItems = 0;
  itemsPerPage = ITEMS_PER_PAGE;
  page!: number;
  predicate!: string;
  ascending!: boolean;
  ngbPaginationPage = 1;

  constructor(protected activatedRoute: ActivatedRoute, protected patientService: PatientService, protected router: Router) {}

  ngOnInit(): void {
    this.loadPage();
  }

  public setGridView(): void {
    this.isGridView = !this.isGridView;
    this.textButtonGridView = this.isGridView ? 'Ver en Lista' : 'Ver Cuadrícula';
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
}
