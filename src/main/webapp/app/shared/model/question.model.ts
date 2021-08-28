export interface IQuestion {
  id?: number;
  question?: string;
  type?: string;
}

export class Question implements IQuestion {
  constructor(public id?: number, public question?: string, public type?: string) {}
}
