import { Moment } from 'moment';

export interface IScore {
  id?: number;
  time?: number;
  level?: number;
  creationDate?: Moment;
  patientId?: number;
  gameId?: number;
}

export class Score implements IScore {
  constructor(
    public id?: number,
    public time?: number,
    public level?: number,
    public creationDate?: Moment,
    public patientId?: number,
    public gameId?: number
  ) {}
}
