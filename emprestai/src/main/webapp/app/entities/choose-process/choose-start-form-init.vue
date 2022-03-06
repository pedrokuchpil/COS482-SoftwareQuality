<template>
  <div class="row justify-content-center">
    <div class="col-10">
      <form name="editForm" role="form" novalidate v-on:submit.prevent="save()">
        <h2
          id="emprestaiApp.chooseStartForm.home.createOrEditLabel"
          data-cy="ChooseStartFormCreateUpdateHeading"
          v-text="$t('emprestaiApp.chooseStartForm.home.createOrEditLabel')"
        >
          Create or edit a ChooseStartForm
        </h2>
        <div v-if="chooseProcess.processInstance">
          <akip-show-process-definition :processDefinition="chooseProcess.processInstance.processDefinition">
            <template v-slot:body>
              <hr />
              <div v-if="chooseProcess.emprestimo">
                <div class="form-group">
                  <label class="form-control-label" v-text="$t('emprestaiApp.chooseStartForm.date')" for="choose-start-form-date"
                    >Date</label
                  >
                  <b-input-group class="mb-3">
                    <b-input-group-prepend>
                      <b-form-datepicker
                        aria-controls="choose-start-form-date"
                        v-model="$v.chooseProcess.emprestimo.date.$model"
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
                      id="choose-start-form-date"
                      data-cy="date"
                      type="text"
                      class="form-control"
                      name="date"
                      :class="{ valid: !$v.chooseProcess.emprestimo.date.$invalid, invalid: $v.chooseProcess.emprestimo.date.$invalid }"
                      v-model="$v.chooseProcess.emprestimo.date.$model"
                    />
                  </b-input-group>
                </div>
                <div class="form-group">
                  <label class="form-control-label" v-text="$t('emprestaiApp.chooseStartForm.username')" for="choose-start-form-username"
                    >Username</label
                  >
                  <input
                    type="text"
                    class="form-control"
                    name="username"
                    id="choose-start-form-username"
                    data-cy="username"
                    :class="{
                      valid: !$v.chooseProcess.emprestimo.username.$invalid,
                      invalid: $v.chooseProcess.emprestimo.username.$invalid,
                    }"
                    v-model="$v.chooseProcess.emprestimo.username.$model"
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
            :disabled="$v.chooseProcess.$invalid || isSaving"
            class="btn btn-primary"
          >
            <font-awesome-icon icon="play"></font-awesome-icon>&nbsp;<span>Start</span>
          </button>
        </div>
      </form>
    </div>
  </div>
</template>
<script lang="ts" src="./choose-start-form-init.component.ts"></script>
