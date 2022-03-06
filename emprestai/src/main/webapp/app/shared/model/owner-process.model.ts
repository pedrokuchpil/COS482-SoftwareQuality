import { IEmprestimo } from '@/shared/model/emprestimo.model';

export interface IOwnerProcess {
  id?: number;
  processInstance?: any | null;
  emprestimo?: IEmprestimo | null;
}

export class OwnerProcess implements IOwnerProcess {
  constructor(public id?: number, public processInstance?: any | null, public emprestimo?: IEmprestimo | null) {}
}
