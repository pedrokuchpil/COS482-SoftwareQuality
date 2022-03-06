/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';

import * as config from '@/shared/config/config';
import UsuarioComponent from '@/entities/usuario/usuario.vue';
import UsuarioClass from '@/entities/usuario/usuario.component';
import UsuarioService from '@/entities/usuario/usuario.service';

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
  describe('Usuario Management Component', () => {
    let wrapper: Wrapper<UsuarioClass>;
    let comp: UsuarioClass;
    let usuarioServiceStub: SinonStubbedInstance<UsuarioService>;

    beforeEach(() => {
      usuarioServiceStub = sinon.createStubInstance<UsuarioService>(UsuarioService);
      usuarioServiceStub.retrieve.resolves({ headers: {} });

      wrapper = shallowMount<UsuarioClass>(UsuarioComponent, {
        store,
        i18n,
        localVue,
        stubs: { bModal: bModalStub as any },
        provide: {
          usuarioService: () => usuarioServiceStub,
        },
      });
      comp = wrapper.vm;
    });

    it('Should call load all on init', async () => {
      // GIVEN
      usuarioServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });

      // WHEN
      comp.retrieveAllUsuarios();
      await comp.$nextTick();

      // THEN
      expect(usuarioServiceStub.retrieve.called).toBeTruthy();
      expect(comp.usuarios[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
    it('Should call delete service on confirmDelete', async () => {
      // GIVEN
      usuarioServiceStub.delete.resolves({});

      // WHEN
      comp.prepareRemove({ id: 123 });
      comp.removeUsuario();
      await comp.$nextTick();

      // THEN
      expect(usuarioServiceStub.delete.called).toBeTruthy();
      expect(usuarioServiceStub.retrieve.callCount).toEqual(1);
    });
  });
});
