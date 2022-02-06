/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import VueRouter from 'vue-router';

import * as config from '@/shared/config/config';
import EmprestimoDetailComponent from '@/entities/emprestimo/emprestimo-details.vue';
import EmprestimoClass from '@/entities/emprestimo/emprestimo-details.component';
import EmprestimoService from '@/entities/emprestimo/emprestimo.service';
import router from '@/router';

const localVue = createLocalVue();
localVue.use(VueRouter);

config.initVueApp(localVue);
const i18n = config.initI18N(localVue);
const store = config.initVueXStore(localVue);
localVue.component('font-awesome-icon', {});
localVue.component('router-link', {});

describe('Component Tests', () => {
  describe('Emprestimo Management Detail Component', () => {
    let wrapper: Wrapper<EmprestimoClass>;
    let comp: EmprestimoClass;
    let emprestimoServiceStub: SinonStubbedInstance<EmprestimoService>;

    beforeEach(() => {
      emprestimoServiceStub = sinon.createStubInstance<EmprestimoService>(EmprestimoService);

      wrapper = shallowMount<EmprestimoClass>(EmprestimoDetailComponent, {
        store,
        i18n,
        localVue,
        router,
        provide: { emprestimoService: () => emprestimoServiceStub },
      });
      comp = wrapper.vm;
    });

    describe('OnInit', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        const foundEmprestimo = { id: 123 };
        emprestimoServiceStub.find.resolves(foundEmprestimo);

        // WHEN
        comp.retrieveEmprestimo(123);
        await comp.$nextTick();

        // THEN
        expect(comp.emprestimo).toBe(foundEmprestimo);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        const foundEmprestimo = { id: 123 };
        emprestimoServiceStub.find.resolves(foundEmprestimo);

        // WHEN
        comp.beforeRouteEnter({ params: { emprestimoId: 123 } }, null, cb => cb(comp));
        await comp.$nextTick();

        // THEN
        expect(comp.emprestimo).toBe(foundEmprestimo);
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
