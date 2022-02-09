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
const ReceiverStartFormInit = () => import('@/entities/receiver-process/receiver-start-form-init.vue');
// prettier-ignore
const ReceiverProcessDetails = () => import('@/entities/receiver-process/receiver-process-details.vue');
// prettier-ignore
const ReceiverProcessList = () => import('@/entities/receiver-process/receiver-process-list.vue');
// prettier-ignore
const ReceiverProcess_SelecionarLivroDetails = () => import('@/entities/receiver-process/selecionar-livro/selecionar-livro-details.vue');
// prettier-ignore
const ReceiverProcess_SelecionarLivroExecute = () => import('@/entities/receiver-process/selecionar-livro/selecionar-livro-execute.vue');
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
    path: '/process-definition/ReceiverProcess/init',
    name: 'ReceiverStartFormInit',
    component: ReceiverStartFormInit,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/process-definition/ReceiverProcess/instance/:processInstanceId/view',
    name: 'ReceiverProcessView',
    component: ReceiverProcessDetails,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/process-definition/ReceiverProcess/instances',
    name: 'ReceiverProcessList',
    component: ReceiverProcessList,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/process-definition/ReceiverProcess/task/SelecionarLivro/:taskInstanceId/view',
    name: 'ReceiverProcess_SelecionarLivroDetails',
    component: ReceiverProcess_SelecionarLivroDetails,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/process-definition/ReceiverProcess/task/SelecionarLivro/:taskInstanceId/execute',
    name: 'ReceiverProcess_SelecionarLivroExecute',
    component: ReceiverProcess_SelecionarLivroExecute,
    meta: { authorities: [Authority.USER] },
  },
  // jhipster-needle-add-entity-to-router - JHipster will add entities to the router here
];
