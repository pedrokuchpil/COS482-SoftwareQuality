import axios from 'axios';

import { IReceiverProcess } from '@/shared/model/receiver-process.model';

const baseApiUrl = 'api/receiver-processes';

export default class ReceiverProcessService {
  public find(id: number): Promise<IReceiverProcess> {
    return new Promise<IReceiverProcess>((resolve, reject) => {
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

  public create(entity: IReceiverProcess): Promise<IReceiverProcess> {
    return new Promise<IReceiverProcess>((resolve, reject) => {
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
