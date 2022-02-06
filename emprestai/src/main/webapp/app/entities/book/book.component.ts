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

  public closeDialog(): void {
    (<any>this.$refs.removeEntity).hide();
  }
}
