import { Component, Vue, Inject } from 'vue-property-decorator';

import { IAcceptProcess } from '@/shared/model/accept-process.model';
import AcceptProcessService from './accept-process.service';

@Component
export default class AcceptProcessDetailsComponent extends Vue {
  @Inject('acceptProcessService') private acceptProcessService: () => AcceptProcessService;
  public acceptProcess: IAcceptProcess = {};

  public isFetching: boolean = false;

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.processInstanceId) {
        vm.retrieveAcceptProcess(to.params.processInstanceId);
      }
    });
  }

  public retrieveAcceptProcess(acceptProcessId) {
    this.isFetching = true;
    this.acceptProcessService()
      .find(acceptProcessId)
      .then(
        res => {
          this.acceptProcess = res;
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
