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

  public prepareRemove(instance: IUsuario): void {
    this.removeId = instance.id;
    if (<any>this.$refs.removeEntity) {
      (<any>this.$refs.removeEntity).show();
    }
  }

  public removeUsuario(): void {
    this.usuarioService()
      .delete(this.removeId)
      .then(() => {
        const message = this.$t('emprestaiApp.usuario.deleted', { param: this.removeId });
        this.$bvToast.toast(message.toString(), {
          toaster: 'b-toaster-top-center',
          title: 'Info',
          variant: 'danger',
          solid: true,
          autoHideDelay: 5000,
        });
        this.removeId = null;
        this.retrieveAllUsuarios();
        this.closeDialog();
      });
  }

  public closeDialog(): void {
    (<any>this.$refs.removeEntity).hide();
  }
}
