import { Component, Vue, Inject } from 'vue-property-decorator';
import { ITakeProcess } from '@/shared/model/take-process.model';

import { ProcessDefinition, ProcessDefinitionService } from 'akip-vue-community';

import TakeProcessService from './take-process.service';

@Component
export default class TakeProcessListComponent extends Vue {
  @Inject('takeProcessService') private takeProcessService: () => TakeProcessService;

  private processDefinitionService: ProcessDefinitionService = new ProcessDefinitionService();

  public bpmnProcessDefinitionId: string = 'TakeProcess';
  public processDefinition: ProcessDefinition = new ProcessDefinition();
  public takeProcessList: ITakeProcess[] = [];

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
    this.takeProcessService()
      .retrieve()
      .then(
        res => {
          this.takeProcessList = res.data;
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
