import { Component, Vue, Inject } from 'vue-property-decorator';
import { IReadProcess } from '@/shared/model/read-process.model';

import { ProcessDefinition, ProcessDefinitionService } from 'akip-vue-community';

import ReadProcessService from './read-process.service';

@Component
export default class ReadProcessListComponent extends Vue {
  @Inject('readProcessService') private readProcessService: () => ReadProcessService;

  private processDefinitionService: ProcessDefinitionService = new ProcessDefinitionService();

  public bpmnProcessDefinitionId: string = 'ReadProcess';
  public processDefinition: ProcessDefinition = new ProcessDefinition();
  public readProcessList: IReadProcess[] = [];

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
    this.readProcessService()
      .retrieve()
      .then(
        res => {
          this.readProcessList = res.data;
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
