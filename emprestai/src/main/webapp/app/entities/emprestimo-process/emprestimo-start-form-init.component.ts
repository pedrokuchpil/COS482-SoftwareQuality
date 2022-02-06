import { Component, Vue, Inject } from 'vue-property-decorator';

import { IEmprestimoProcess, EmprestimoProcess } from '@/shared/model/emprestimo-process.model';

import { ProcessInstance, ProcessDefinitionService } from 'akip-vue-community';

import { Emprestimo } from '@/shared/model/emprestimo.model';
import EmprestimoProcessService from './emprestimo-process.service';

const validations: any = {
  emprestimoProcess: {
    emprestimo: {
      date: {},
    },
  },
};

@Component({
  validations,
})
export default class EmprestimoStartFormInitComponent extends Vue {
  @Inject('emprestimoProcessService') private emprestimoProcessService: () => EmprestimoProcessService;

  private processDefinitionService: ProcessDefinitionService = new ProcessDefinitionService();

  public bpmnProcessDefinitionId: string = 'EmprestimoProcess';
  public emprestimoProcess: IEmprestimoProcess = new EmprestimoProcess();

  public isSaving = false;
  public currentLanguage = '';

  beforeRouteEnter(to, from, next) {
    next(vm => {
      vm.initEmprestimoStartForm();
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

    this.emprestimoProcessService()
      .create(this.emprestimoProcess)
      .then(param => {
        this.isSaving = false;
        this.$router.go(-1);
        const message = this.$t('emprestaiApp.emprestimoStartForm.created', { param: param.id });
        this.$root.$bvToast.toast(message.toString(), {
          toaster: 'b-toaster-top-center',
          title: 'Success',
          variant: 'success',
          solid: true,
          autoHideDelay: 5000,
        });
      });
  }

  public initEmprestimoStartForm(): void {
    this.emprestimoProcess.emprestimo = new Emprestimo();
  }

  public previousState(): void {
    this.$router.go(-1);
  }

  public initRelationships(): void {
    this.processDefinitionService.find(this.bpmnProcessDefinitionId).then(res => {
      this.emprestimoProcess.processInstance = new ProcessInstance();
      this.emprestimoProcess.processInstance.processDefinition = res;
    });
  }
}
