import { Component, Vue, Inject } from 'vue-property-decorator';

import { IChooseProcess, ChooseProcess } from '@/shared/model/choose-process.model';

import { ProcessInstance, ProcessDefinitionService } from 'akip-vue-community';

import { Emprestimo } from '@/shared/model/emprestimo.model';
import ChooseProcessService from './choose-process.service';

const validations: any = {
  chooseProcess: {
    emprestimo: {
      date: {},
      username: {},
    },
  },
};

@Component({
  validations,
})
export default class ChooseStartFormInitComponent extends Vue {
  @Inject('chooseProcessService') private chooseProcessService: () => ChooseProcessService;

  private processDefinitionService: ProcessDefinitionService = new ProcessDefinitionService();

  public bpmnProcessDefinitionId: string = 'ChooseProcess';
  public chooseProcess: IChooseProcess = new ChooseProcess();

  public isSaving = false;
  public currentLanguage = '';

  beforeRouteEnter(to, from, next) {
    next(vm => {
      vm.initChooseStartForm();
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

    this.chooseProcessService()
      .create(this.chooseProcess)
      .then(param => {
        this.isSaving = false;
        this.$router.go(-1);
        const message = this.$t('emprestaiApp.chooseStartForm.created', { param: param.id });
        this.$root.$bvToast.toast(message.toString(), {
          toaster: 'b-toaster-top-center',
          title: 'Success',
          variant: 'success',
          solid: true,
          autoHideDelay: 5000,
        });
      });
  }

  public initChooseStartForm(): void {
    this.chooseProcess.emprestimo = new Emprestimo();
  }

  public previousState(): void {
    this.$router.go(-1);
  }

  public initRelationships(): void {
    this.processDefinitionService.find(this.bpmnProcessDefinitionId).then(res => {
      this.chooseProcess.processInstance = new ProcessInstance();
      this.chooseProcess.processInstance.processDefinition = res;
    });
  }
}
