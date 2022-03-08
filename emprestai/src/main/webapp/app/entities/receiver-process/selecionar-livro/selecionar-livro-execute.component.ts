import { Component, Vue, Inject } from 'vue-property-decorator';

import BookService from '@/entities/book/book.service';
import { IBook } from '@/shared/model/book.model';

import SelecionarLivroService from './selecionar-livro.service';
import { SelecionarLivroContext } from './selecionar-livro.model';

const validations: any = {
  taskContext: {
    receiverProcess: {
      emprestimo: {
        date: {},
      },
    },
  },
};

@Component({
  validations,
})
export default class SelecionarLivroExecuteComponent extends Vue {
  private selecionarLivroService: SelecionarLivroService = new SelecionarLivroService();
  private taskContext: SelecionarLivroContext = {};

  @Inject('bookService') private bookService: () => BookService;

  public books: IBook[] = [];
  public isSaving = false;

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.taskInstanceId) {
        vm.claimTaskInstance(to.params.taskInstanceId);
      }
      vm.initRelationships();
    });
  }

  public claimTaskInstance(taskInstanceId) {
    this.selecionarLivroService.claim(taskInstanceId).then(res => {
      this.taskContext = res;
    });
  }

  public previousState() {
    this.$router.go(-1);
  }

  public complete() {
    this.selecionarLivroService.complete(this.taskContext).then(res => {
      this.$router.go(-1);
    });
  }

  public initRelationships(): void {
    this.bookService()
      .retrieve()
      .then(res => {
        this.books = res.data;
      });
  }
}
