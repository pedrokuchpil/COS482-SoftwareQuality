import { IEmprestimo } from '@/shared/model/emprestimo.model';

export interface ITakeProcess {
  id?: number;
  processInstance?: any | null;
  emprestimo?: IEmprestimo | null;
}

export class TakeProcess implements ITakeProcess {
  constructor(public id?: number, public processInstance?: any | null, public emprestimo?: IEmprestimo | null) {}
}
