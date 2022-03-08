import { Authority } from '@/shared/security/authority';
/* tslint:disable */
// prettier-ignore

// prettier-ignore
const Emprestimo = () => import('@/entities/emprestimo/emprestimo.vue');
// prettier-ignore
const EmprestimoDetails = () => import('@/entities/emprestimo/emprestimo-details.vue');
// prettier-ignore
const ChooseProcessDetails = () => import('@/entities/choose-process/choose-process-details.vue');
// prettier-ignore
const ChooseProcessList = () => import('@/entities/choose-process/choose-process-list.vue');
// prettier-ignore
const TakeProcessDetails = () => import('@/entities/take-process/take-process-details.vue');
// prettier-ignore
const TakeProcessList = () => import('@/entities/take-process/take-process-list.vue');
// prettier-ignore
const ChooseStartFormInit = () => import('@/entities/choose-process/choose-start-form-init.vue');
// prettier-ignore
const ReadProcessDetails = () => import('@/entities/read-process/read-process-details.vue');
// prettier-ignore
const ReadProcessList = () => import('@/entities/read-process/read-process-list.vue');
// prettier-ignore
const ReceiverProcessDetails = () => import('@/entities/receiver-process/receiver-process-details.vue');
// prettier-ignore
const ReceiverProcessList = () => import('@/entities/receiver-process/receiver-process-list.vue');
// prettier-ignore
const AcceptProcessDetails = () => import('@/entities/accept-process/accept-process-details.vue');
// prettier-ignore
const AcceptProcessList = () => import('@/entities/accept-process/accept-process-list.vue');
// prettier-ignore
const ReceiverProcess_SelecionarLivroDetails = () => import('@/entities/receiver-process/selecionar-livro/selecionar-livro-details.vue');
// prettier-ignore
const ReceiverProcess_SelecionarLivroExecute = () => import('@/entities/receiver-process/selecionar-livro/selecionar-livro-execute.vue');
// prettier-ignore
const Usuario = () => import('@/entities/usuario/usuario.vue');
// prettier-ignore
const UsuarioUpdate = () => import('@/entities/usuario/usuario-update.vue');
// prettier-ignore
const UsuarioDetails = () => import('@/entities/usuario/usuario-details.vue');
// prettier-ignore
const Book = () => import('@/entities/book/book.vue');
// prettier-ignore
const BookUpdate = () => import('@/entities/book/book-update.vue');
// prettier-ignore
const BookDetails = () => import('@/entities/book/book-details.vue');
// prettier-ignore
const ReceiverStartFormInit = () => import('@/entities/receiver-process/receiver-start-form-init.vue');
// jhipster-needle-add-entity-to-router-import - JHipster will import entities to the router here

export default [
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
    path: '/process-definition/ChooseProcess/instance/:processInstanceId/view',
    name: 'ChooseProcessView',
    component: ChooseProcessDetails,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/process-definition/ChooseProcess/instances',
    name: 'ChooseProcessList',
    component: ChooseProcessList,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/process-definition/TakeProcess/instance/:processInstanceId/view',
    name: 'TakeProcessView',
    component: TakeProcessDetails,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/process-definition/TakeProcess/instances',
    name: 'TakeProcessList',
    component: TakeProcessList,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/process-definition/ChooseProcess/init',
    name: 'ChooseStartFormInit',
    component: ChooseStartFormInit,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/process-definition/ReadProcess/instance/:processInstanceId/view',
    name: 'ReadProcessView',
    component: ReadProcessDetails,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/process-definition/ReadProcess/instances',
    name: 'ReadProcessList',
    component: ReadProcessList,
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
    path: '/process-definition/AcceptProcess/instance/:processInstanceId/view',
    name: 'AcceptProcessView',
    component: AcceptProcessDetails,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/process-definition/AcceptProcess/instances',
    name: 'AcceptProcessList',
    component: AcceptProcessList,
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
  {
    path: '/usuario',
    name: 'Usuario',
    component: Usuario,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/usuario/new',
    name: 'UsuarioCreate',
    component: UsuarioUpdate,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/usuario/:usuarioId/edit',
    name: 'UsuarioEdit',
    component: UsuarioUpdate,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/usuario/:usuarioId/view',
    name: 'UsuarioView',
    component: UsuarioDetails,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/book',
    name: 'Book',
    component: Book,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/book/new',
    name: 'BookCreate',
    component: BookUpdate,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/book/:bookId/edit',
    name: 'BookEdit',
    component: BookUpdate,
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
  // jhipster-needle-add-entity-to-router - JHipster will add entities to the router here
];
