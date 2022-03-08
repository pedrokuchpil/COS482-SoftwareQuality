/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import Router from 'vue-router';

import * as config from '@/shared/config/config';
import BookUpdateComponent from '@/entities/book/book-update.vue';
import BookClass from '@/entities/book/book-update.component';
import BookService from '@/entities/book/book.service';

const localVue = createLocalVue();

config.initVueApp(localVue);
const i18n = config.initI18N(localVue);
const store = config.initVueXStore(localVue);
const router = new Router();
localVue.use(Router);
localVue.component('font-awesome-icon', {});
localVue.component('b-input-group', {});
localVue.component('b-input-group-prepend', {});
localVue.component('b-form-datepicker', {});
localVue.component('b-form-input', {});

describe('Component Tests', () => {
  describe('Book Management Update Component', () => {
    let wrapper: Wrapper<BookClass>;
    let comp: BookClass;
    let bookServiceStub: SinonStubbedInstance<BookService>;

    beforeEach(() => {
      bookServiceStub = sinon.createStubInstance<BookService>(BookService);

      wrapper = shallowMount<BookClass>(BookUpdateComponent, {
        store,
        i18n,
        localVue,
        router,
        provide: {
          bookService: () => bookServiceStub,
        },
      });
      comp = wrapper.vm;
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', async () => {
        // GIVEN
        const entity = { id: 123 };
        comp.book = entity;
        bookServiceStub.update.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(bookServiceStub.update.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        comp.book = entity;
        bookServiceStub.create.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(bookServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        const foundBook = { id: 123 };
        bookServiceStub.find.resolves(foundBook);
        bookServiceStub.retrieve.resolves([foundBook]);

        // WHEN
        comp.beforeRouteEnter({ params: { bookId: 123 } }, null, cb => cb(comp));
        await comp.$nextTick();

        // THEN
        expect(comp.book).toBe(foundBook);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        comp.previousState();
        await comp.$nextTick();

        expect(comp.$router.currentRoute.fullPath).toContain('/');
      });
    });
  });
});
