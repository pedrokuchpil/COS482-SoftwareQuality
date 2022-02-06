<template>
  <div>
    <h2 id="page-heading" data-cy="UsuarioHeading">
      <span v-text="$t('emprestaiApp.usuario.home.title')" id="usuario-heading">Usuarios</span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="$t('emprestaiApp.usuario.home.refreshListLabel')">Refresh List</span>
        </button>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && usuarios && usuarios.length === 0">
      <span v-text="$t('emprestaiApp.usuario.home.notFound')">No usuarios found</span>
    </div>
    <div class="table-responsive" v-if="usuarios && usuarios.length > 0">
      <table class="table table-striped" aria-describedby="usuarios">
        <thead>
          <tr>
            <th scope="row"><span v-text="$t('global.field.id')">ID</span></th>
            <th scope="row"><span v-text="$t('emprestaiApp.usuario.username')">Username</span></th>
            <th scope="row"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="usuario in usuarios" :key="usuario.id" data-cy="entityTable">
            <td>
              <router-link :to="{ name: 'UsuarioView', params: { usuarioId: usuario.id } }">{{ usuario.id }}</router-link>
            </td>
            <td>{{ usuario.username }}</td>
            <td class="text-right">
              <div class="btn-group">
                <router-link :to="{ name: 'UsuarioView', params: { usuarioId: usuario.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="$t('entity.action.view')">View</span>
                  </button>
                </router-link>
              </div>
            </td>
          </tr>
        </tbody>
      </table>
    </div>
    <b-modal ref="removeEntity" id="removeEntity">
      <span slot="modal-title"
        ><span id="emprestaiApp.usuario.delete.question" data-cy="usuarioDeleteDialogHeading" v-text="$t('entity.delete.title')"
          >Confirm delete operation</span
        ></span
      >
      <div class="modal-body">
        <p id="jhi-delete-usuario-heading" v-text="$t('emprestaiApp.usuario.delete.question', { id: removeId })">
          Are you sure you want to delete this Usuario?
        </p>
      </div>
      <div slot="modal-footer">
        <button type="button" class="btn btn-secondary" v-text="$t('entity.action.cancel')" v-on:click="closeDialog()">Cancel</button>
        <button
          type="button"
          class="btn btn-primary"
          id="jhi-confirm-delete-usuario"
          data-cy="entityConfirmDeleteButton"
          v-text="$t('entity.action.delete')"
          v-on:click="removeUsuario()"
        >
          Delete
        </button>
      </div>
    </b-modal>
  </div>
</template>

<script lang="ts" src="./usuario.component.ts"></script>
