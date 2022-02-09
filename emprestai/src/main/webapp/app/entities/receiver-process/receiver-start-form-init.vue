<template>
  <div class="row justify-content-center">
    <div class="col-10">
      <form name="editForm" role="form" novalidate v-on:submit.prevent="save()">
        <h2
          id="emprestaiApp.receiverStartForm.home.createOrEditLabel"
          data-cy="ReceiverStartFormCreateUpdateHeading"
          v-text="$t('emprestaiApp.receiverStartForm.home.createOrEditLabel')"
        >
          Create or edit a ReceiverStartForm
        </h2>
        <div v-if="receiverProcess.processInstance">
          <akip-show-process-definition :processDefinition="receiverProcess.processInstance.processDefinition">
            <template v-slot:body>
              <hr />
              <div v-if="receiverProcess.emprestimo">
                <div class="form-group">
                  <label class="form-control-label" v-text="$t('emprestaiApp.receiverStartForm.date')" for="receiver-start-form-date"
                    >Date</label
                  >
                  <b-input-group class="mb-3">
                    <b-input-group-prepend>
                      <b-form-datepicker
                        aria-controls="receiver-start-form-date"
                        v-model="$v.receiverProcess.emprestimo.date.$model"
                        name="date"
                        class="form-control"
                        :locale="currentLanguage"
                        button-only
                        today-button
                        reset-button
                        close-button
                      >
                      </b-form-datepicker>
                    </b-input-group-prepend>
                    <b-form-input
                      id="receiver-start-form-date"
                      data-cy="date"
                      type="text"
                      class="form-control"
                      name="date"
                      :class="{ valid: !$v.receiverProcess.emprestimo.date.$invalid, invalid: $v.receiverProcess.emprestimo.date.$invalid }"
                      v-model="$v.receiverProcess.emprestimo.date.$model"
                    />
                  </b-input-group>
                </div>
                <div class="form-group">
                  <label
                    class="form-control-label"
                    v-text="$t('emprestaiApp.receiverStartForm.username')"
                    for="receiver-start-form-username"
                    >Username</label
                  >
                  <input
                    type="text"
                    class="form-control"
                    name="username"
                    id="receiver-start-form-username"
                    data-cy="username"
                    :class="{
                      valid: !$v.receiverProcess.emprestimo.username.$invalid,
                      invalid: $v.receiverProcess.emprestimo.username.$invalid,
                    }"
                    v-model="$v.receiverProcess.emprestimo.username.$model"
                  />
                </div>
              </div>
            </template>
          </akip-show-process-definition>
          <br />
          <button type="button" id="cancel-save" class="btn btn-secondary" v-on:click="previousState()">
            <font-awesome-icon icon="ban"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.cancel')">Cancel</span>
          </button>
          <button
            type="submit"
            id="save-entity"
            data-cy="entityCreateSaveButton"
            :disabled="$v.receiverProcess.$invalid || isSaving"
            class="btn btn-primary"
          >
            <font-awesome-icon icon="play"></font-awesome-icon>&nbsp;<span>Start</span>
          </button>
        </div>
      </form>
    </div>
  </div>
</template>
<script lang="ts" src="./receiver-start-form-init.component.ts"></script>
