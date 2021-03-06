/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';

import * as config from '@/shared/config/config';
import EmprestimoComponent from '@/entities/emprestimo/emprestimo.vue';
import EmprestimoClass from '@/entities/emprestimo/emprestimo.component';
import EmprestimoService from '@/entities/emprestimo/emprestimo.service';

const localVue = createLocalVue();

config.initVueApp(localVue);
const i18n = config.initI18N(localVue);
const store = config.initVueXStore(localVue);
localVue.component('font-awesome-icon', {});
localVue.component('b-badge', {});
localVue.directive('b-modal', {});
localVue.component('b-button', {});
localVue.component('router-link', {});

const bModalStub = {
  render: () => {},
  methods: {
    hide: () => {},
    show: () => {},
  },
};

describe('Component Tests', () => {
  describe('Emprestimo Management Component', () => {
    let wrapper: Wrapper<EmprestimoClass>;
    let comp: EmprestimoClass;
    let emprestimoServiceStub: SinonStubbedInstance<EmprestimoService>;

    beforeEach(() => {
      emprestimoServiceStub = sinon.createStubInstance<EmprestimoService>(EmprestimoService);
      emprestimoServiceStub.retrieve.resolves({ headers: {} });

      wrapper = shallowMount<EmprestimoClass>(EmprestimoComponent, {
        store,
        i18n,
        localVue,
        stubs: { bModal: bModalStub as any },
        provide: {
          emprestimoService: () => emprestimoServiceStub,
        },
      });
      comp = wrapper.vm;
    });

    it('Should call load all on init', async () => {
      // GIVEN
      emprestimoServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });

      // WHEN
      comp.retrieveAllEmprestimos();
      await comp.$nextTick();

      // THEN
      expect(emprestimoServiceStub.retrieve.called).toBeTruthy();
      expect(comp.emprestimos[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
