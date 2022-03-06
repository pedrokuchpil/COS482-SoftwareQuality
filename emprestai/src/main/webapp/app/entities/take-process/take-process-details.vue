<template>
  <div class="row justify-content-center">
    <div class="col-10">
      <div v-if="takeProcess.processInstance">
        <h2 class="jh-entity-heading" data-cy="takeProcessDetailsHeading">
          <span v-text="$t('emprestaiApp.takeProcess.detail.title')">TakeProcess</span> {{ takeProcess.id }}
        </h2>
        <akip-show-process-instance :processInstance="takeProcess.processInstance">
          <template v-slot:title>
            <span v-text="$t('emprestaiApp.takeProcess.detail.title')">TakeProcess</span> #{{ takeProcess.id }}
          </template>
          <template v-slot:details>
            <div class="card" v-if="takeProcess.emprestimo">
              <h5 class="card-header">
                <span class="title"> Emprestimo #{{ takeProcess.emprestimo.id }} </span>
              </h5>
              <div class="card-body py-0">
                <div class="form-group">
                  <label class="form-control-label" v-text="$t('emprestaiApp.takeProcess.date')">date</label>
                  <input
                    readonly
                    type="text"
                    class="form-control"
                    name="date"
                    id="emprestimo-date"
                    data-cy="date"
                    v-model="takeProcess.emprestimo.date"
                  />
                </div>
              </div>
              <div class="card-body py-0">
                <div class="form-group">
                  <label class="form-control-label" v-text="$t('emprestaiApp.takeProcess.username')">username</label>
                  <input
                    readonly
                    type="text"
                    class="form-control"
                    name="username"
                    id="emprestimo-username"
                    data-cy="username"
                    v-model="takeProcess.emprestimo.username"
                  />
                </div>
              </div>
              <div class="card-body py-0">
                <div class="form-group">
                  <label class="form-control-label" v-text="$t('emprestaiApp.takeProcess.title')">title</label>
                  <input
                    readonly
                    type="text"
                    class="form-control"
                    name="title"
                    id="emprestimo-title"
                    data-cy="title"
                    v-model="takeProcess.emprestimo.title"
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

<script lang="ts" src="./take-process-details.component.ts"></script>
