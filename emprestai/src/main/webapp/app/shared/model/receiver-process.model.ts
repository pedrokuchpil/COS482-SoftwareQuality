import { IEmprestimo } from '@/shared/model/emprestimo.model';

export interface IReceiverProcess {
  id?: number;
  processInstance?: any | null;
  emprestimo?: IEmprestimo | null;
}

export class ReceiverProcess implements IReceiverProcess {
  constructor(public id?: number, public processInstance?: any | null, public emprestimo?: IEmprestimo | null) {}
}
