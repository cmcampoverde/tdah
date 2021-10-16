export interface ITestAnswer {
  id?: number;
  value?: number;
  testEdahId?: number;
  questionId?: number;
}

export class TestAnswer implements ITestAnswer {
  constructor(public id?: number, public value?: number, public testEdahId?: number, public questionId?: number) {}
}
