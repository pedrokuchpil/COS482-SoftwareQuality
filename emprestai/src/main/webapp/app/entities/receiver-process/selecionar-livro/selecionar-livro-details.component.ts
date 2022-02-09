import { Component, Vue, Inject } from 'vue-property-decorator';

import SelecionarLivroService from './selecionar-livro.service';
import { SelecionarLivroContext } from './selecionar-livro.model';

@Component
export default class SelecionarLivroDetailsComponent extends Vue {
  private selecionarLivroService: SelecionarLivroService = new SelecionarLivroService();
  private taskContext: SelecionarLivroContext = {};

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.taskInstanceId) {
        vm.retrieveContext(to.params.taskInstanceId);
      }
    });
  }

  public retrieveContext(taskInstanceId) {
    this.selecionarLivroService.loadContext(taskInstanceId).then(res => {
      this.taskContext = res;
    });
  }

  public previousState() {
    this.$router.go(-1);
  }
}
