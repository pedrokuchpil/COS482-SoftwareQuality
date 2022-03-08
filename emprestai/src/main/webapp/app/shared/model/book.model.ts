export interface IBook {
  id?: number;
  title?: string | null;
  author?: string | null;
}

export class Book implements IBook {
  constructor(public id?: number, public title?: string | null, public author?: string | null) {}
}
