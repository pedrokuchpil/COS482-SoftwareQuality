import { Component, Vue, Inject } from 'vue-property-decorator';

import { IOwnerProcess } from '@/shared/model/owner-process.model';
import OwnerProcessService from './owner-process.service';

@Component
export default class OwnerProcessDetailsComponent extends Vue {
  @Inject('ownerProcessService') private ownerProcessService: () => OwnerProcessService;
  public ownerProcess: IOwnerProcess = {};

  public isFetching: boolean = false;

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.processInstanceId) {
        vm.retrieveOwnerProcess(to.params.processInstanceId);
      }
    });
  }

  public retrieveOwnerProcess(ownerProcessId) {
    this.isFetching = true;
    this.ownerProcessService()
      .find(ownerProcessId)
      .then(
        res => {
          this.ownerProcess = res;
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
