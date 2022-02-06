import { IUsuario } from '@/shared/model/usuario.model';
import { IBook } from '@/shared/model/book.model';

export interface IEmprestimo {
  id?: number;
  date?: Date | null;
  receiver?: IUsuario | null;
  book?: IBook | null;
}

export class Emprestimo implements IEmprestimo {
  constructor(public id?: number, public date?: Date | null, public receiver?: IUsuario | null, public book?: IBook | null) {}
}
