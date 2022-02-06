import { IEmprestimo } from '@/shared/model/emprestimo.model';

export interface IEmprestimoProcess {
  id?: number;
  processInstance?: any | null;
  emprestimo?: IEmprestimo | null;
}

export class EmprestimoProcess implements IEmprestimoProcess {
  constructor(public id?: number, public processInstance?: any | null, public emprestimo?: IEmprestimo | null) {}
}
