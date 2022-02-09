<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="emprestimo">
        <h2 class="jh-entity-heading" data-cy="emprestimoDetailsHeading">
          <span v-text="$t('emprestaiApp.emprestimo.detail.title')">Emprestimo</span> {{ emprestimo.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="$t('emprestaiApp.emprestimo.date')">Date</span>
          </dt>
          <dd>
            <span>{{ emprestimo.date }}</span>
          </dd>
          <dt>
            <span v-text="$t('emprestaiApp.emprestimo.username')">Username</span>
          </dt>
          <dd>
            <span>{{ emprestimo.username }}</span>
          </dd>
          <dt>
            <span v-text="$t('emprestaiApp.emprestimo.title')">Title</span>
          </dt>
          <dd>
            <span>{{ emprestimo.title }}</span>
          </dd>
          <dt>
            <span v-text="$t('emprestaiApp.emprestimo.receiver')">Receiver</span>
          </dt>
          <dd>
            <div v-if="emprestimo.receiver">
              <router-link :to="{ name: 'UsuarioView', params: { UsuarioId: emprestimo.receiver.id } }">{{
                emprestimo.receiver.username
              }}</router-link>
            </div>
          </dd>
          <dt>
            <span v-text="$t('emprestaiApp.emprestimo.book')">Book</span>
          </dt>
          <dd>
            <div v-if="emprestimo.book">
              <router-link :to="{ name: 'BookView', params: { BookId: emprestimo.book.id } }">{{ emprestimo.book.id }}</router-link>
            </div>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.back')"> Back</span>
        </button>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./emprestimo-details.component.ts"></script>
