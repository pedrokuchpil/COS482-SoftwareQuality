import { IBook } from '@/shared/model/book.model';

export interface IEmprestimo {
  id?: number;
  date?: Date | null;
  book?: IBook | null;
}

export class Emprestimo implements IEmprestimo {
  constructor(public id?: number, public date?: Date | null, public book?: IBook | null) {}
}
