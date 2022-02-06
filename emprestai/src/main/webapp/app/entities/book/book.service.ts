import axios from 'axios';

import { IBook } from '@/shared/model/book.model';

const baseApiUrl = 'api/books';

export default class BookService {
  public find(id: number): Promise<IBook> {
    return new Promise<IBook>((resolve, reject) => {
      axios
        .get(`${baseApiUrl}/${id}`)
        .then(res => {
          resolve(res.data);
        })
        .catch(err => {
          reject(err);
        });
    });
  }

  public retrieve(): Promise<any> {
    return new Promise<any>((resolve, reject) => {
      axios
        .get(baseApiUrl)
        .then(res => {
          resolve(res);
        })
        .catch(err => {
          reject(err);
        });
    });
  }
}
