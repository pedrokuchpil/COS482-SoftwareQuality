import { mixins } from 'vue-class-component';

import { Component, Vue, Inject } from 'vue-property-decorator';
import Vue2Filters from 'vue2-filters';
import { IBook } from '@/shared/model/book.model';

import BookService from './book.service';

@Component({
  mixins: [Vue2Filters.mixin],
})
export default class Book extends Vue {
  @Inject('bookService') private bookService: () => BookService;
  private removeId: number = null;

  public books: IBook[] = [];

  public isFetching = false;

  public mounted(): void {
    this.retrieveAllBooks();
  }

  public clear(): void {
    this.retrieveAllBooks();
  }

  public retrieveAllBooks(): void {
    this.isFetching = true;

    this.bookService()
      .retrieve()
      .then(
        res => {
          this.books = res.data;
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

  public prepareRemove(instance: IBook): void {
    this.removeId = instance.id;
    if (<any>this.$refs.removeEntity) {
      (<any>this.$refs.removeEntity).show();
    }
  }

  public removeBook(): void {
    this.bookService()
      .delete(this.removeId)
      .then(() => {
        const message = this.$t('emprestaiApp.book.deleted', { param: this.removeId });
        this.$bvToast.toast(message.toString(), {
          toaster: 'b-toaster-top-center',
          title: 'Info',
          variant: 'danger',
          solid: true,
          autoHideDelay: 5000,
        });
        this.removeId = null;
        this.retrieveAllBooks();
        this.closeDialog();
      });
  }

  public closeDialog(): void {
    (<any>this.$refs.removeEntity).hide();
  }
}
