<template>
  <div>
    <h2 class="jh-entity-heading" data-cy="readProcessDetailsHeading">
      <span v-text="$t('emprestaiApp.readProcess.home.title')">ReadProcess</span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="$t('emprestaiApp.readProcess.home.refreshListLabel')">Refresh List</span>
        </button>

        <router-link :to="`/process-definition/ReadProcess/init`" tag="button" class="btn btn-primary mr-2">
          <font-awesome-icon icon="plus"></font-awesome-icon>
          <span v-text="$t('emprestaiApp.readProcess.home.createLabel')"> Create a new Travel Plan Process Instance </span>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && readProcessList && readProcessList.length === 0">
      <span v-text="$t('emprestaiApp.readProcess.home.notFound')">No readProcess found</span>
    </div>
    <div class="table-responsive" v-if="readProcessList && readProcessList.length > 0">
      <table class="table table-striped" aria-describedby="readProcessList">
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
          <tr v-for="readProcess in readProcessList" :key="readProcess.id" data-cy="entityTable">
            <td>{{ readProcess.id }}</td>
            <td>
              <div v-if="readProcess.processInstance">
                <router-link :to="`/process-definition/ReadProcess/instance/${readProcess.processInstance.id}/view`">
                  {{ readProcess.processInstance.businessKey }}
                </router-link>
              </div>
            </td>
            <td>
              <div v-if="readProcess.emprestimo">
                <router-link :to="{ name: 'EmprestimoView', params: { emprestimoId: readProcess.emprestimo.id } }">{{
                  readProcess.emprestimo.id
                }}</router-link>
              </div>
            </td>
            <td>
              <akip-show-process-instance-status :status="readProcess.processInstance.status"></akip-show-process-instance-status>
            </td>
            <td>{{ readProcess.processInstance.startDate ? $d(Date.parse(readProcess.processInstance.startDate), 'short') : '' }}</td>
            <td>{{ readProcess.processInstance.endDate ? $d(Date.parse(readProcess.processInstance.endDate), 'short') : '' }}</td>
            <td class="text-right">
              <div class="btn-group">
                <router-link
                  :to="`/process-definition/ReadProcess/instance/${readProcess.processInstance.id}/view`"
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

<script lang="ts" src="./read-process-list.component.ts"></script>
