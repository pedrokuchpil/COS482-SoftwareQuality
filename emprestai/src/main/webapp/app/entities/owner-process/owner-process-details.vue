<template>
  <div class="row justify-content-center">
    <div class="col-10">
      <div v-if="ownerProcess.processInstance">
        <h2 class="jh-entity-heading" data-cy="ownerProcessDetailsHeading">
          <span v-text="$t('emprestaiApp.ownerProcess.detail.title')">OwnerProcess</span> {{ ownerProcess.id }}
        </h2>
        <akip-show-process-instance :processInstance="ownerProcess.processInstance">
          <template v-slot:title>
            <span v-text="$t('emprestaiApp.ownerProcess.detail.title')">OwnerProcess</span> #{{ ownerProcess.id }}
          </template>
          <template v-slot:details>
            <div class="card" v-if="ownerProcess.emprestimo">
              <h5 class="card-header">
                <span class="title"> Emprestimo #{{ ownerProcess.emprestimo.id }} </span>
              </h5>
              <div class="card-body py-0">
                <div class="form-group">
                  <label class="form-control-label" v-text="$t('emprestaiApp.ownerProcess.date')">date</label>
                  <input
                    readonly
                    type="text"
                    class="form-control"
                    name="date"
                    id="emprestimo-date"
                    data-cy="date"
                    v-model="ownerProcess.emprestimo.date"
                  />
                </div>
              </div>
              <div class="card-body py-0">
                <div class="form-group">
                  <label class="form-control-label" v-text="$t('emprestaiApp.ownerProcess.username')">username</label>
                  <input
                    readonly
                    type="text"
                    class="form-control"
                    name="username"
                    id="emprestimo-username"
                    data-cy="username"
                    v-model="ownerProcess.emprestimo.username"
                  />
                </div>
              </div>
              <div class="card-body py-0">
                <div class="form-group">
                  <label class="form-control-label" v-text="$t('emprestaiApp.ownerProcess.title')">title</label>
                  <input
                    readonly
                    type="text"
                    class="form-control"
                    name="title"
                    id="emprestimo-title"
                    data-cy="title"
                    v-model="ownerProcess.emprestimo.title"
                  />
                </div>
              </div>
            </div>
          </template>
        </akip-show-process-instance>
        <br />
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.back')"> Back</span>
        </button>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./owner-process-details.component.ts"></script>
