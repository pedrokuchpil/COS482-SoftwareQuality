<template>
  <div>
    <h2 id="page-heading" data-cy="EmprestimoHeading">
      <span v-text="$t('emprestaiApp.emprestimo.home.title')" id="emprestimo-heading">Emprestimos</span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="$t('emprestaiApp.emprestimo.home.refreshListLabel')">Refresh List</span>
        </button>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && emprestimos && emprestimos.length === 0">
      <span v-text="$t('emprestaiApp.emprestimo.home.notFound')">No emprestimos found</span>
    </div>
    <div class="table-responsive" v-if="emprestimos && emprestimos.length > 0">
      <table class="table table-striped" aria-describedby="emprestimos">
        <thead>
          <tr>
            <th scope="row"><span v-text="$t('global.field.id')">ID</span></th>
            <th scope="row"><span v-text="$t('emprestaiApp.emprestimo.date')">Date</span></th>
            <th scope="row"><span v-text="$t('emprestaiApp.emprestimo.book')">Book</span></th>
            <th scope="row"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="emprestimo in emprestimos" :key="emprestimo.id" data-cy="entityTable">
            <td>
              <router-link :to="{ name: 'EmprestimoView', params: { emprestimoId: emprestimo.id } }">{{ emprestimo.id }}</router-link>
            </td>
            <td>{{ emprestimo.date }}</td>
            <td>
              <div v-if="emprestimo.book">
                <router-link :to="{ name: 'BookView', params: { BookId: emprestimo.book.id } }">{{ emprestimo.book.title }}</router-link>
              </div>
            </td>
            <td class="text-right">
              <div class="btn-group">
                <router-link :to="{ name: 'EmprestimoView', params: { emprestimoId: emprestimo.id } }" custom v-slot="{ navigate }">
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
        ><span id="emprestaiApp.emprestimo.delete.question" data-cy="emprestimoDeleteDialogHeading" v-text="$t('entity.delete.title')"
          >Confirm delete operation</span
        ></span
      >
      <div class="modal-body">
        <p id="jhi-delete-emprestimo-heading" v-text="$t('emprestaiApp.emprestimo.delete.question', { id: removeId })">
          Are you sure you want to delete this Emprestimo?
        </p>
      </div>
      <div slot="modal-footer">
        <button type="button" class="btn btn-secondary" v-text="$t('entity.action.cancel')" v-on:click="closeDialog()">Cancel</button>
        <button
          type="button"
          class="btn btn-primary"
          id="jhi-confirm-delete-emprestimo"
          data-cy="entityConfirmDeleteButton"
          v-text="$t('entity.action.delete')"
          v-on:click="removeEmprestimo()"
        >
          Delete
        </button>
      </div>
    </b-modal>
  </div>
</template>

<script lang="ts" src="./emprestimo.component.ts"></script>
