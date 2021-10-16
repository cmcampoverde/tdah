import { Score } from './score.model';

export interface IResultLower {
  idGame?: number;
  scores?: Score[];
}
export class ResultLower implements IResultLower {
  constructor(public idGame?: number, public scores?: Score[]) {}
}
