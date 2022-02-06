import { mixins } from 'vue-class-component';

import { Component, Vue, Inject } from 'vue-property-decorator';
import Vue2Filters from 'vue2-filters';
import { IEmprestimo } from '@/shared/model/emprestimo.model';

import EmprestimoService from './emprestimo.service';

@Component({
  mixins: [Vue2Filters.mixin],
})
export default class Emprestimo extends Vue {
  @Inject('emprestimoService') private emprestimoService: () => EmprestimoService;
  private removeId: number = null;

  public emprestimos: IEmprestimo[] = [];

  public isFetching = false;

  public mounted(): void {
    this.retrieveAllEmprestimos();
  }

  public clear(): void {
    this.retrieveAllEmprestimos();
  }

  public retrieveAllEmprestimos(): void {
    this.isFetching = true;

    this.emprestimoService()
      .retrieve()
      .then(
        res => {
          this.emprestimos = res.data;
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
