import { Component, Vue, Inject } from 'vue-property-decorator';
import { IReceiverProcess } from '@/shared/model/receiver-process.model';

import { ProcessDefinition, ProcessDefinitionService } from 'akip-vue-community';

import ReceiverProcessService from './receiver-process.service';

@Component
export default class ReceiverProcessListComponent extends Vue {
  @Inject('receiverProcessService') private receiverProcessService: () => ReceiverProcessService;

  private processDefinitionService: ProcessDefinitionService = new ProcessDefinitionService();

  public bpmnProcessDefinitionId: string = 'ReceiverProcess';
  public processDefinition: ProcessDefinition = new ProcessDefinition();
  public receiverProcessList: IReceiverProcess[] = [];

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
    this.receiverProcessService()
      .retrieve()
      .then(
        res => {
          this.receiverProcessList = res.data;
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
