import { IEmprestimo } from '@/shared/model/emprestimo.model';

export interface IChooseProcess {
  id?: number;
  processInstance?: any | null;
  emprestimo?: IEmprestimo | null;
}

export class ChooseProcess implements IChooseProcess {
  constructor(public id?: number, public processInstance?: any | null, public emprestimo?: IEmprestimo | null) {}
}
