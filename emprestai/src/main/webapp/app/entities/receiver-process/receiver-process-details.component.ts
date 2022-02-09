import { Component, Vue, Inject } from 'vue-property-decorator';

import { IReceiverProcess } from '@/shared/model/receiver-process.model';
import ReceiverProcessService from './receiver-process.service';

@Component
export default class ReceiverProcessDetailsComponent extends Vue {
  @Inject('receiverProcessService') private receiverProcessService: () => ReceiverProcessService;
  public receiverProcess: IReceiverProcess = {};

  public isFetching: boolean = false;

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.processInstanceId) {
        vm.retrieveReceiverProcess(to.params.processInstanceId);
      }
    });
  }

  public retrieveReceiverProcess(receiverProcessId) {
    this.isFetching = true;
    this.receiverProcessService()
      .find(receiverProcessId)
      .then(
        res => {
          this.receiverProcess = res;
          this.isFetching = false;
        },
        err => {
          this.isFetching = false;
        }
      );
  }

  public previousState() {
    this.$router.go(-1);
  }
}
