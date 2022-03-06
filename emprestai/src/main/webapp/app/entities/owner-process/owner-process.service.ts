import axios from 'axios';

import { IOwnerProcess } from '@/shared/model/owner-process.model';

const baseApiUrl = 'api/owner-processes';

export default class OwnerProcessService {
  public find(id: number): Promise<IOwnerProcess> {
    return new Promise<IOwnerProcess>((resolve, reject) => {
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

  public create(entity: IOwnerProcess): Promise<IOwnerProcess> {
    return new Promise<IOwnerProcess>((resolve, reject) => {
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
