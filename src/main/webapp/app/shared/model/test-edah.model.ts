export interface ITestEdah {
  id?: number;
  teacherEmail?: string;
  answered?: boolean;
  key?: string;
  teacherName?: string;
  instructions?: string;
  patientId?: number;
}

export class TestEdah implements ITestEdah {
  constructor(
    public id?: number,
    public teacherEmail?: string,
    public answered?: boolean,
    public key?: string,
    public teacherName?: string,
    public instructions?: string,
    public patientId?: number
  ) {
    this.answered = this.answered || false;
  }
}
