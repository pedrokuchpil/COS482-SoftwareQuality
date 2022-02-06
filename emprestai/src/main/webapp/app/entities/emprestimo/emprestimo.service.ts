import axios from 'axios';

import { IEmprestimo } from '@/shared/model/emprestimo.model';

const baseApiUrl = 'api/emprestimos';

export default class EmprestimoService {
  public find(id: number): Promise<IEmprestimo> {
    return new Promise<IEmprestimo>((resolve, reject) => {
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
