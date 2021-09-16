export interface IUser {
  id?: number;
  patientId?: number;
}

export class User implements IUser {
  constructor(public id?: number, public patientId?: number) {}
}
