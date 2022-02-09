import { Component, Vue, Inject } from 'vue-property-decorator';

import { IReceiverProcess, ReceiverProcess } from '@/shared/model/receiver-process.model';

import { ProcessInstance, ProcessDefinitionService } from 'akip-vue-community';

import { Emprestimo } from '@/shared/model/emprestimo.model';
import ReceiverProcessService from './receiver-process.service';

const validations: any = {
  receiverProcess: {
    emprestimo: {
      date: {},
      username: {},
    },
  },
};

@Component({
  validations,
})
export default class ReceiverStartFormInitComponent extends Vue {
  @Inject('receiverProcessService') private receiverProcessService: () => ReceiverProcessService;

  private processDefinitionService: ProcessDefinitionService = new ProcessDefinitionService();

  public bpmnProcessDefinitionId: string = 'ReceiverProcess';
  public receiverProcess: IReceiverProcess = new ReceiverProcess();

  public isSaving = false;
  public currentLanguage = '';

  beforeRouteEnter(to, from, next) {
    next(vm => {
      vm.initReceiverStartForm();
      vm.initRelationships();
    });
  }

  created(): void {
    this.currentLanguage = this.$store.getters.currentLanguage;
    this.$store.watch(
      () => this.$store.getters.currentLanguage,
      () => {
        this.currentLanguage = this.$store.getters.currentLanguage;
      }
    );
  }

  public save(): void {
    this.isSaving = true;

    this.receiverProcessService()
      .create(this.receiverProcess)
      .then(param => {
        this.isSaving = false;
        this.$router.go(-1);
        const message = this.$t('emprestaiApp.receiverStartForm.created', { param: param.id });
        this.$root.$bvToast.toast(message.toString(), {
          toaster: 'b-toaster-top-center',
          title: 'Success',
          variant: 'success',
          solid: true,
          autoHideDelay: 5000,
        });
      });
  }

  public initReceiverStartForm(): void {
    this.receiverProcess.emprestimo = new Emprestimo();
  }

  public previousState(): void {
    this.$router.go(-1);
  }

  public initRelationships(): void {
    this.processDefinitionService.find(this.bpmnProcessDefinitionId).then(res => {
      this.receiverProcess.processInstance = new ProcessInstance();
      this.receiverProcess.processInstance.processDefinition = res;
    });
  }
}
