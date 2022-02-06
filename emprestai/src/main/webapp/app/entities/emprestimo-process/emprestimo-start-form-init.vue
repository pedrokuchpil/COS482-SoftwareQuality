<template>
  <div class="row justify-content-center">
    <div class="col-10">
      <form name="editForm" role="form" novalidate v-on:submit.prevent="save()">
        <h2
          id="emprestaiApp.emprestimoStartForm.home.createOrEditLabel"
          data-cy="EmprestimoStartFormCreateUpdateHeading"
          v-text="$t('emprestaiApp.emprestimoStartForm.home.createOrEditLabel')"
        >
          Create or edit a EmprestimoStartForm
        </h2>
        <div v-if="emprestimoProcess.processInstance">
          <akip-show-process-definition :processDefinition="emprestimoProcess.processInstance.processDefinition">
            <template v-slot:body>
              <hr />
              <div v-if="emprestimoProcess.emprestimo">
                <div class="form-group">
                  <label class="form-control-label" v-text="$t('emprestaiApp.emprestimoStartForm.date')" for="emprestimo-start-form-date"
                    >Date</label
                  >
                  <b-input-group class="mb-3">
                    <b-input-group-prepend>
                      <b-form-datepicker
                        aria-controls="emprestimo-start-form-date"
                        v-model="$v.emprestimoProcess.emprestimo.date.$model"
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
                      id="emprestimo-start-form-date"
                      data-cy="date"
                      type="text"
                      class="form-control"
                      name="date"
                      :class="{
                        valid: !$v.emprestimoProcess.emprestimo.date.$invalid,
                        invalid: $v.emprestimoProcess.emprestimo.date.$invalid,
                      }"
                      v-model="$v.emprestimoProcess.emprestimo.date.$model"
                    />
                  </b-input-group>
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
            :disabled="$v.emprestimoProcess.$invalid || isSaving"
            class="btn btn-primary"
          >
            <font-awesome-icon icon="play"></font-awesome-icon>&nbsp;<span>Start</span>
          </button>
        </div>
      </form>
    </div>
  </div>
</template>
<script lang="ts" src="./emprestimo-start-form-init.component.ts"></script>
