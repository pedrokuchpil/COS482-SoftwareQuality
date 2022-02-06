/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import VueRouter from 'vue-router';

import * as config from '@/shared/config/config';
import UsuarioDetailComponent from '@/entities/usuario/usuario-details.vue';
import UsuarioClass from '@/entities/usuario/usuario-details.component';
import UsuarioService from '@/entities/usuario/usuario.service';
import router from '@/router';

const localVue = createLocalVue();
localVue.use(VueRouter);

config.initVueApp(localVue);
const i18n = config.initI18N(localVue);
const store = config.initVueXStore(localVue);
localVue.component('font-awesome-icon', {});
localVue.component('router-link', {});

describe('Component Tests', () => {
  describe('Usuario Management Detail Component', () => {
    let wrapper: Wrapper<UsuarioClass>;
    let comp: UsuarioClass;
    let usuarioServiceStub: SinonStubbedInstance<UsuarioService>;

    beforeEach(() => {
      usuarioServiceStub = sinon.createStubInstance<UsuarioService>(UsuarioService);

      wrapper = shallowMount<UsuarioClass>(UsuarioDetailComponent, {
        store,
        i18n,
        localVue,
        router,
        provide: { usuarioService: () => usuarioServiceStub },
      });
      comp = wrapper.vm;
    });

    describe('OnInit', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        const foundUsuario = { id: 123 };
        usuarioServiceStub.find.resolves(foundUsuario);

        // WHEN
        comp.retrieveUsuario(123);
        await comp.$nextTick();

        // THEN
        expect(comp.usuario).toBe(foundUsuario);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        const foundUsuario = { id: 123 };
        usuarioServiceStub.find.resolves(foundUsuario);

        // WHEN
        comp.beforeRouteEnter({ params: { usuarioId: 123 } }, null, cb => cb(comp));
        await comp.$nextTick();

        // THEN
        expect(comp.usuario).toBe(foundUsuario);
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
