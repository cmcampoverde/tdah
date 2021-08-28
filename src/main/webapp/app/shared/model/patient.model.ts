import { Moment } from 'moment';

export interface IPatient {
  id?: number;
  name?: string;
  lastName?: string;
  age?: number;
  description?: string;
  diagnostic?: string;
  sex?: string;
  parent?: string;
  birthday?: Moment;
  address?: string;
  phoneParent?: string;
}

export class Patient implements IPatient {
  constructor(
    public id?: number,
    public name?: string,
    public lastName?: string,
    public age?: number,
    public description?: string,
    public diagnostic?: string,
    public sex?: string,
    public parent?: string,
    public birthday?: Moment,
    public address?: string,
    public phoneParent?: string
  ) {}
}
