import axios from 'axios';

import { IEmprestimoProcess } from '@/shared/model/emprestimo-process.model';

const baseApiUrl = 'api/emprestimo-processes';

export default class EmprestimoProcessService {
  public find(id: number): Promise<IEmprestimoProcess> {
    return new Promise<IEmprestimoProcess>((resolve, reject) => {
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

  public create(entity: IEmprestimoProcess): Promise<IEmprestimoProcess> {
    return new Promise<IEmprestimoProcess>((resolve, reject) => {
      axios
        .post(`${baseApiUrl}`, entity)
        .then(res => {
          resolve(res.data);
        })
        .catch(err => {
          reject(err);
        });
    });
  }
}
