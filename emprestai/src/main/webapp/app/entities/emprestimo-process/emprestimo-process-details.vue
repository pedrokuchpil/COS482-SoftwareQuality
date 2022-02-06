<template>
  <div class="row justify-content-center">
    <div class="col-10">
      <div v-if="emprestimoProcess.processInstance">
        <h2 class="jh-entity-heading" data-cy="emprestimoProcessDetailsHeading">
          <span v-text="$t('emprestaiApp.emprestimoProcess.detail.title')">EmprestimoProcess</span> {{ emprestimoProcess.id }}
        </h2>
        <akip-show-process-instance :processInstance="emprestimoProcess.processInstance">
          <template v-slot:title>
            <span v-text="$t('emprestaiApp.emprestimoProcess.detail.title')">EmprestimoProcess</span> #{{ emprestimoProcess.id }}
          </template>
          <template v-slot:details>
            <div class="card" v-if="emprestimoProcess.emprestimo">
              <h5 class="card-header">
                <span class="title"> Emprestimo #{{ emprestimoProcess.emprestimo.id }} </span>
              </h5>
              <div class="card-body py-0">
                <div class="form-group">
                  <label class="form-control-label" v-text="$t('emprestaiApp.emprestimoProcess.date')">date</label>
                  <input
                    readonly
                    type="text"
                    class="form-control"
                    name="date"
                    id="emprestimo-date"
                    data-cy="date"
                    v-model="emprestimoProcess.emprestimo.date"
                  />
                </div>
              </div>
              <div class="card-body py-0">
                <div class="form-group">
                  <label class="form-control-label" v-text="$t('emprestaiApp.emprestimoProcess.receiver')" for="emprestimo-process-receiver"
                    >Receiver</label
                  >
                  <input
                    v-if="emprestimoProcess.emprestimo.receiver"
                    readonly
                    type="text"
                    class="form-control"
                    name="receiver"
                    id="emprestimo-receiver"
                    data-cy="receiver"
                    :value="emprestimoProcess.emprestimo.receiver.username"
                  />
                  <input
                    v-else
                    readonly
                    type="text"
                    class="form-control"
                    name="receiver"
                    id="emprestimo-receiver"
                    data-cy="receiver"
                    value=""
                  />
                </div>
              </div>
              <div class="card-body py-0">
                <div class="form-group">
                  <label class="form-control-label" v-text="$t('emprestaiApp.emprestimoProcess.book')" for="emprestimo-process-book"
                    >Book</label
                  >
                  <input
                    v-if="emprestimoProcess.emprestimo.book"
                    readonly
                    type="text"
                    class="form-control"
                    name="book"
                    id="emprestimo-book"
                    data-cy="book"
                    :value="emprestimoProcess.emprestimo.book.id"
                  />
                  <input v-else readonly type="text" class="form-control" name="book" id="emprestimo-book" data-cy="book" value="" />
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

<script lang="ts" src="./emprestimo-process-details.component.ts"></script>
