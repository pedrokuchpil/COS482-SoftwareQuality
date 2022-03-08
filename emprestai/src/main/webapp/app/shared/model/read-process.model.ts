import { IEmprestimo } from '@/shared/model/emprestimo.model';

export interface IReadProcess {
  id?: number;
  processInstance?: any | null;
  emprestimo?: IEmprestimo | null;
}

export class ReadProcess implements IReadProcess {
  constructor(public id?: number, public processInstance?: any | null, public emprestimo?: IEmprestimo | null) {}
}
