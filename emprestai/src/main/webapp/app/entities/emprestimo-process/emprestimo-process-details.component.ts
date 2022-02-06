import { Component, Vue, Inject } from 'vue-property-decorator';

import { IEmprestimoProcess } from '@/shared/model/emprestimo-process.model';
import EmprestimoProcessService from './emprestimo-process.service';

@Component
export default class EmprestimoProcessDetailsComponent extends Vue {
  @Inject('emprestimoProcessService') private emprestimoProcessService: () => EmprestimoProcessService;
  public emprestimoProcess: IEmprestimoProcess = {};

  public isFetching: boolean = false;

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.processInstanceId) {
        vm.retrieveEmprestimoProcess(to.params.processInstanceId);
      }
    });
  }

  public retrieveEmprestimoProcess(emprestimoProcessId) {
    this.isFetching = true;
    this.emprestimoProcessService()
      .find(emprestimoProcessId)
      .then(
        res => {
          this.emprestimoProcess = res;
          this.isFetching = false;
        },
        err => {
          this.isFetching = false;
        }
      );
  }

  public previousState() {
    this.$router.go(-1);
  }
}
