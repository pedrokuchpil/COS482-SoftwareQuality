import { IEmprestimo } from '@/shared/model/emprestimo.model';

export interface IAcceptProcess {
  id?: number;
  processInstance?: any | null;
  emprestimo?: IEmprestimo | null;
}

export class AcceptProcess implements IAcceptProcess {
  constructor(public id?: number, public processInstance?: any | null, public emprestimo?: IEmprestimo | null) {}
}
