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
              <label class="form-control-label" v-text="$t('emprestaiApp.selecionarLivro.date')" for="selecionar-livro-date">Date</label>
              <b-input-group class="mb-3">
                <b-form-input
                  id="selecionar-livro-date"
                  readonly
                  data-cy="date"
                  type="text"
                  class="form-control"
                  name="date"
                  :class="{
                    valid: !$v.taskContext.receiverProcess.emprestimo.date.$invalid,
                    invalid: $v.taskContext.receiverProcess.emprestimo.date.$invalid,
                  }"
                  v-model="$v.taskContext.receiverProcess.emprestimo.date.$model"
                />
              </b-input-group>
            </div>
            <div class="form-group">
              <label class="form-control-label" v-text="$t('emprestaiApp.selecionarLivro.book')" for="selecionar-livro-book">Book</label>
              <select
                class="form-control"
                id="selecionar-livro-book"
                data-cy="book"
                name="book"
                v-model="taskContext.receiverProcess.emprestimo.book"
              >
                <option v-bind:value="null"></option>
                <option
                  v-bind:value="
                    taskContext.receiverProcess.emprestimo.book && bookOption.id === taskContext.receiverProcess.emprestimo.book.id
                      ? taskContext.receiverProcess.emprestimo.book
                      : bookOption
                  "
                  v-for="bookOption in books"
                  :key="bookOption.id"
                >
                  {{ bookOption.title }}
                </option>
              </select>
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
