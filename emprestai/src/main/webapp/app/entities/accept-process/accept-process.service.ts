import axios from 'axios';

import { IAcceptProcess } from '@/shared/model/accept-process.model';

const baseApiUrl = 'api/accept-processes';

export default class AcceptProcessService {
  public find(id: number): Promise<IAcceptProcess> {
    return new Promise<IAcceptProcess>((resolve, reject) => {
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

  public create(entity: IAcceptProcess): Promise<IAcceptProcess> {
    return new Promise<IAcceptProcess>((resolve, reject) => {
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
