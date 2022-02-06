import { Authority } from '@/shared/security/authority';
/* tslint:disable */
// prettier-ignore

// prettier-ignore
const Usuario = () => import('@/entities/usuario/usuario.vue');
// prettier-ignore
const UsuarioDetails = () => import('@/entities/usuario/usuario-details.vue');
// prettier-ignore
const Emprestimo = () => import('@/entities/emprestimo/emprestimo.vue');
// prettier-ignore
const EmprestimoDetails = () => import('@/entities/emprestimo/emprestimo-details.vue');
// prettier-ignore
const Book = () => import('@/entities/book/book.vue');
// prettier-ignore
const BookDetails = () => import('@/entities/book/book-details.vue');
// prettier-ignore
const EmprestimoProcessDetails = () => import('@/entities/emprestimo-process/emprestimo-process-details.vue');
// prettier-ignore
const EmprestimoProcessList = () => import('@/entities/emprestimo-process/emprestimo-process-list.vue');
// prettier-ignore
const EmprestimoStartFormInit = () => import('@/entities/emprestimo-process/emprestimo-start-form-init.vue');
// jhipster-needle-add-entity-to-router-import - JHipster will import entities to the router here

export default [
  {
    path: '/usuario',
    name: 'Usuario',
    component: Usuario,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/usuario/:usuarioId/view',
    name: 'UsuarioView',
    component: UsuarioDetails,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/emprestimo',
    name: 'Emprestimo',
    component: Emprestimo,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/emprestimo/:emprestimoId/view',
    name: 'EmprestimoView',
    component: EmprestimoDetails,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/book',
    name: 'Book',
    component: Book,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/book/:bookId/view',
    name: 'BookView',
    component: BookDetails,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/process-definition/EmprestimoProcess/instance/:processInstanceId/view',
    name: 'EmprestimoProcessView',
    component: EmprestimoProcessDetails,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/process-definition/EmprestimoProcess/instances',
    name: 'EmprestimoProcessList',
    component: EmprestimoProcessList,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/process-definition/EmprestimoProcess/init',
    name: 'EmprestimoStartFormInit',
    component: EmprestimoStartFormInit,
    meta: { authorities: [Authority.USER] },
  },
  // jhipster-needle-add-entity-to-router - JHipster will add entities to the router here
];
