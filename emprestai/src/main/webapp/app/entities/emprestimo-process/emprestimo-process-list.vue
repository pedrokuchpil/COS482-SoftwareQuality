<template>
  <div>
    <h2 class="jh-entity-heading" data-cy="emprestimoProcessDetailsHeading">
      <span v-text="$t('emprestaiApp.emprestimoProcess.home.title')">EmprestimoProcess</span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="$t('emprestaiApp.emprestimoProcess.home.refreshListLabel')">Refresh List</span>
        </button>

        <router-link :to="`/process-definition/EmprestimoProcess/init`" tag="button" class="btn btn-primary mr-2">
          <font-awesome-icon icon="plus"></font-awesome-icon>
          <span v-text="$t('emprestaiApp.emprestimoProcess.home.createLabel')"> Create a new Travel Plan Process Instance </span>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && emprestimoProcessList && emprestimoProcessList.length === 0">
      <span v-text="$t('emprestaiApp.emprestimoProcess.home.notFound')">No emprestimoProcess found</span>
    </div>
    <div class="table-responsive" v-if="emprestimoProcessList && emprestimoProcessList.length > 0">
      <table class="table table-striped" aria-describedby="emprestimoProcessList">
        <thead>
          <tr>
            <th scope="row"><span v-text="$t('global.field.id')">ID</span></th>
            <th scope="row"><span>Process Instance</span></th>
            <th scope="row"><span>Emprestimo</span></th>
            <th scope="row"><span>Status</span></th>
            <th scope="row"><span>Start Date</span></th>
            <th scope="row"><span>End Date</span></th>
            <th scope="row"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="emprestimoProcess in emprestimoProcessList" :key="emprestimoProcess.id" data-cy="entityTable">
            <td>{{ emprestimoProcess.id }}</td>
            <td>
              <div v-if="emprestimoProcess.processInstance">
                <router-link :to="`/process-definition/EmprestimoProcess/instance/${emprestimoProcess.processInstance.id}/view`">
                  {{ emprestimoProcess.processInstance.businessKey }}
                </router-link>
              </div>
            </td>
            <td>
              <div v-if="emprestimoProcess.emprestimo">
                <router-link :to="{ name: 'EmprestimoView', params: { emprestimoId: emprestimoProcess.emprestimo.id } }">{{
                  emprestimoProcess.emprestimo.id
                }}</router-link>
              </div>
            </td>
            <td>
              <akip-show-process-instance-status :status="emprestimoProcess.processInstance.status"></akip-show-process-instance-status>
            </td>
            <td>
              {{ emprestimoProcess.processInstance.startDate ? $d(Date.parse(emprestimoProcess.processInstance.startDate), 'short') : '' }}
            </td>
            <td>
              {{ emprestimoProcess.processInstance.endDate ? $d(Date.parse(emprestimoProcess.processInstance.endDate), 'short') : '' }}
            </td>
            <td class="text-right">
              <div class="btn-group">
                <router-link
                  :to="`/process-definition/EmprestimoProcess/instance/${emprestimoProcess.processInstance.id}/view`"
                  tag="button"
                  class="btn btn-info btn-sm details"
                  data-cy="entityDetailsButton"
                >
                  <font-awesome-icon icon="eye"></font-awesome-icon>
                  <span class="d-none d-md-inline" v-text="$t('entity.action.view')">View</span>
                </router-link>
              </div>
            </td>
          </tr>
        </tbody>
      </table>
    </div>
    <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
      <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.back')"> Back</span>
    </button>
  </div>
</template>

<script lang="ts" src="./emprestimo-process-list.component.ts"></script>
