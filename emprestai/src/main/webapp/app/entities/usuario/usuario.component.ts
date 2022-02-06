import { mixins } from 'vue-class-component';

import { Component, Vue, Inject } from 'vue-property-decorator';
import Vue2Filters from 'vue2-filters';
import { IUsuario } from '@/shared/model/usuario.model';

import UsuarioService from './usuario.service';

@Component({
  mixins: [Vue2Filters.mixin],
})
export default class Usuario extends Vue {
  @Inject('usuarioService') private usuarioService: () => UsuarioService;
  private removeId: number = null;

  public usuarios: IUsuario[] = [];

  public isFetching = false;

  public mounted(): void {
    this.retrieveAllUsuarios();
  }

  public clear(): void {
    this.retrieveAllUsuarios();
  }

  public retrieveAllUsuarios(): void {
    this.isFetching = true;

    this.usuarioService()
      .retrieve()
      .then(
        res => {
          this.usuarios = res.data;
          this.isFetching = false;
        },
        err => {
          this.isFetching = false;
        }
      );
  }

  public handleSyncList(): void {
    this.clear();
  }

  public closeDialog(): void {
    (<any>this.$refs.removeEntity).hide();
  }
}
