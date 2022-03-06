import axios from 'axios';

import { IChooseProcess } from '@/shared/model/choose-process.model';

const baseApiUrl = 'api/choose-processes';

export default class ChooseProcessService {
  public find(id: number): Promise<IChooseProcess> {
    return new Promise<IChooseProcess>((resolve, reject) => {
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

  public create(entity: IChooseProcess): Promise<IChooseProcess> {
    return new Promise<IChooseProcess>((resolve, reject) => {
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
