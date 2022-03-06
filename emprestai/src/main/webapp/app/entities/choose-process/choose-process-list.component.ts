import { Component, Vue, Inject } from 'vue-property-decorator';
import { IChooseProcess } from '@/shared/model/choose-process.model';

import { ProcessDefinition, ProcessDefinitionService } from 'akip-vue-community';

import ChooseProcessService from './choose-process.service';

@Component
export default class ChooseProcessListComponent extends Vue {
  @Inject('chooseProcessService') private chooseProcessService: () => ChooseProcessService;

  private processDefinitionService: ProcessDefinitionService = new ProcessDefinitionService();

  public bpmnProcessDefinitionId: string = 'ChooseProcess';
  public processDefinition: ProcessDefinition = new ProcessDefinition();
  public chooseProcessList: IChooseProcess[] = [];

  public isFetchingProcessDefinition = false;
  public isFetchingProcessInstances = false;

  public mounted(): void {
    this.init();
  }

  public init(): void {
    this.retrieveProcessDefinition();
    this.retrieveProcessInstances();
  }

  public retrieveProcessDefinition() {
    this.isFetchingProcessDefinition = true;
    this.processDefinitionService.find(this.bpmnProcessDefinitionId).then(
      res => {
        this.processDefinition = res;
        this.isFetchingProcessDefinition = false;
      },
      err => {
        this.isFetchingProcessDefinition = false;
      }
    );
  }

  public retrieveProcessInstances(): void {
    this.isFetchingProcessInstances = true;
    this.chooseProcessService()
      .retrieve()
      .then(
        res => {
          this.chooseProcessList = res.data;
          this.isFetchingProcessInstances = false;
        },
        err => {
          this.isFetchingProcessInstances = false;
        }
      );
  }

  get isFetching(): boolean {
    return this.isFetchingProcessDefinition && this.isFetchingProcessInstances;
  }

  public handleSyncList(): void {
    this.retrieveProcessInstances();
  }

  public previousState(): void {
    this.$router.go(-1);
  }
}
