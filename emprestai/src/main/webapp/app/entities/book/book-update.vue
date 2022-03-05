<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" role="form" novalidate v-on:submit.prevent="save()">
        <h2
          id="emprestaiApp.book.home.createOrEditLabel"
          data-cy="BookCreateUpdateHeading"
          v-text="$t('emprestaiApp.book.home.createOrEditLabel')"
        >
          Create or edit a Book
        </h2>
        <div>
          <div class="form-group" v-if="book.id">
            <label for="id" v-text="$t('global.field.id')">ID</label>
            <input type="text" class="form-control" id="id" name="id" v-model="book.id" readonly />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('emprestaiApp.book.title')" for="book-title">Title</label>
            <input
              type="text"
              class="form-control"
              name="title"
              id="book-title"
              data-cy="title"
              :class="{ valid: !$v.book.title.$invalid, invalid: $v.book.title.$invalid }"
              v-model="$v.book.title.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('emprestaiApp.book.author')" for="book-author">Author</label>
            <input
              type="text"
              class="form-control"
              name="author"
              id="book-author"
              data-cy="author"
              :class="{ valid: !$v.book.author.$invalid, invalid: $v.book.author.$invalid }"
              v-model="$v.book.author.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('emprestaiApp.book.owner')" for="book-owner">Owner</label>
            <select class="form-control" id="book-owner" data-cy="owner" name="owner" v-model="book.owner">
              <option v-bind:value="null"></option>
              <option
                v-bind:value="book.owner && UsuarioOption.id === book.owner.id ? book.owner : UsuarioOption"
                v-for="UsuarioOption in Usuarios"
                :key="UsuarioOption.id"
              >
                {{ UsuarioOption.username }}
              </option>
            </select>
          </div>
        </div>
        <div>
          <button type="button" id="cancel-save" class="btn btn-secondary" v-on:click="previousState()">
            <font-awesome-icon icon="ban"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.cancel')">Cancel</span>
          </button>
          <button
            type="submit"
            id="save-entity"
            data-cy="entityCreateSaveButton"
            :disabled="$v.book.$invalid || isSaving"
            class="btn btn-primary"
          >
            <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.save')">Save</span>
          </button>
        </div>
      </form>
    </div>
  </div>
</template>
<script lang="ts" src="./book-update.component.ts"></script>
