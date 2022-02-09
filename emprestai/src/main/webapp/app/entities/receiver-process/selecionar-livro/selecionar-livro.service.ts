import axios from 'axios';
import { SelecionarLivroContext } from './selecionar-livro.model';

const baseApiUrl = 'api/receiver-process/selecionar-livro';

export default class SelecionarLivroService {
  public loadContext(taskId: number): Promise<SelecionarLivroContext> {
    return new Promise<SelecionarLivroContext>((resolve, reject) => {
      axios
        .get(`${baseApiUrl}/${taskId}`)
        .then(res => {
          resolve(res.data);
        })
        .catch(err => {
          reject(err);
        });
    });
  }

  public claim(taskId: number): Promise<SelecionarLivroContext> {
    return new Promise<SelecionarLivroContext>((resolve, reject) => {
      axios
        .get(`${baseApiUrl}/${taskId}/claim`)
        .then(res => {
          resolve(res.data);
        })
        .catch(err => {
          reject(err);
        });
    });
  }

  public complete(selecionarLivroContext: SelecionarLivroContext): Promise<any> {
    return new Promise<any>((resolve, reject) => {
      axios
        .post(`${baseApiUrl}/complete`, selecionarLivroContext)
        .then(res => {
          resolve(res);
        })
        .catch(err => {
          reject(err);
        });
    });
  }
}
