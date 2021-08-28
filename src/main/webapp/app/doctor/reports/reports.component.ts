import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'jhi-reports',
  templateUrl: './reports.component.html',
  styleUrls: ['./reports.component.scss'],
})
export class ReportsComponent implements OnInit {
  dataSource = [
    { question: 'Tiene excesiva inquietud motora', type: 'H' },
    { question: 'Tiene dificultades de aprendizaje escolar', type: 'DA' },
    { question: 'Molesta frecuentemente a otros niños', type: 'H' },
    { question: 'Se distrae fácilmente, muestra escasa atención', type: 'DA' },
    { question: 'Exige inmediata satisfacción a sus demandas', type: 'H' },
    { question: 'Tiene dificultad para las actividades cooperativa', type: 'TC' },
    { question: 'Está en las nubes, ensimismado', type: 'DA' },
    { question: 'Deja por terminar las tareas que empieza', type: 'DA' },
    { question: 'Es mal aceptado por el grupo', type: 'TC' },
    { question: 'Niega sus errores o echa la culpa a otros', type: 'TC' },
    { question: 'A menudo grita en situaciones inadecuadas', type: 'TC' },
    { question: 'Contesta con facilidad. Es irrespetuoso y arrogante', type: 'TC' },
    { question: 'Se mueve constantemente, intranquilo', type: 'H' },
    { question: 'Discute y pelea por cualquier cosa', type: 'TC' },
    { question: 'Tiene explosiones impredecibles de mal genio', type: 'TC' },
    { question: 'Le falta sentido de la regla, del “juego limpio', type: 'TC' },
    { question: 'Es impulsivo e irritable', type: 'H' },
    { question: 'Se lleva mal con la mayoría de sus compañeros', type: '  TC' },
    { question: 'Sus esfuerzos se frustran fácilmente, es inconstante', type: '  DA' },
    { question: 'Acepta mal las críticas del profeso', type: '  TC' },
  ];

  displayedColumns: string[] = ['question', 'options', 'type'];

  constructor() {}

  ngOnInit(): void {}
}
