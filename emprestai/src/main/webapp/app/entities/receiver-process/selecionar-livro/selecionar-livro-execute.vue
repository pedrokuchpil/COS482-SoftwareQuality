<template>
  <div class="row justify-content-center">
    <div class="col-10">
      <div v-if="taskContext.taskInstance">
        <h2 id="page-heading" data-cy="TaskInstanceHeading">
          <span v-text="$t('emprestaiApp.taskInstance.execute.title')" id="task-instance-heading">Task Execution</span>
        </h2>
        <akip-show-task-instance :taskInstance="taskContext.taskInstance">
          <template v-slot:body>
            <hr />
            <div class="form-group">
              <label class="form-control-label" v-text="$t('emprestaiApp.selecionarLivro.title')" for="selecionar-livro-title">Title</label>
              <input
                type="text"
                class="form-control"
                name="title"
                id="selecionar-livro-title"
                data-cy="title"
                :class="{
                  valid: !$v.taskContext.receiverProcess.emprestimo.title.$invalid,
                  invalid: $v.taskContext.receiverProcess.emprestimo.title.$invalid,
                }"
                v-model="$v.taskContext.receiverProcess.emprestimo.title.$model"
              />
            </div>
          </template>
        </akip-show-task-instance>
        <br />
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.back')"> Back</span>
        </button>
        <button type="submit" v-on:click.prevent="complete()" class="btn btn-success" data-cy="complete">
          <font-awesome-icon icon="check-circle"></font-awesome-icon>&nbsp;Complete
        </button>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./selecionar-livro-execute.component.ts"></script>
