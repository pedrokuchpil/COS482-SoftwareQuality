import { Component, Vue, Inject } from 'vue-property-decorator';

import { IEmprestimo } from '@/shared/model/emprestimo.model';
import EmprestimoService from './emprestimo.service';

@Component
export default class EmprestimoDetails extends Vue {
  @Inject('emprestimoService') private emprestimoService: () => EmprestimoService;
  public emprestimo: IEmprestimo = {};

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.emprestimoId) {
        vm.retrieveEmprestimo(to.params.emprestimoId);
      }
    });
  }

  public retrieveEmprestimo(emprestimoId) {
    this.emprestimoService()
      .find(emprestimoId)
      .then(res => {
        this.emprestimo = res;
      });
  }

  public previousState() {
    this.$router.go(-1);
  }
}
