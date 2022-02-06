export interface IUsuario {
  id?: number;
  username?: string | null;
}

export class Usuario implements IUsuario {
  constructor(public id?: number, public username?: string | null) {}
}
