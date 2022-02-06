import axios from 'axios';

import { IUsuario } from '@/shared/model/usuario.model';

const baseApiUrl = 'api/usuarios';

export default class UsuarioService {
  public find(id: number): Promise<IUsuario> {
    return new Promise<IUsuario>((resolve, reject) => {
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
