import { IUsuario } from '@/shared/model/usuario.model';
import { IBook } from '@/shared/model/book.model';

export interface IEmprestimo {
  id?: number;
  date?: Date | null;
  username?: string | null;
  title?: string | null;
  receiver?: IUsuario | null;
  book?: IBook | null;
}

export class Emprestimo implements IEmprestimo {
  constructor(
    public id?: number,
    public date?: Date | null,
    public username?: string | null,
    public title?: string | null,
    public receiver?: IUsuario | null,
    public book?: IBook | null
  ) {}
}
