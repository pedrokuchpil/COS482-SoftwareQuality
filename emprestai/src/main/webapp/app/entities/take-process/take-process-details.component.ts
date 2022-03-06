import { Component, Vue, Inject } from 'vue-property-decorator';

import { ITakeProcess } from '@/shared/model/take-process.model';
import TakeProcessService from './take-process.service';

@Component
export default class TakeProcessDetailsComponent extends Vue {
  @Inject('takeProcessService') private takeProcessService: () => TakeProcessService;
  public takeProcess: ITakeProcess = {};

  public isFetching: boolean = false;

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.processInstanceId) {
        vm.retrieveTakeProcess(to.params.processInstanceId);
      }
    });
  }

  public retrieveTakeProcess(takeProcessId) {
    this.isFetching = true;
    this.takeProcessService()
      .find(takeProcessId)
      .then(
        res => {
          this.takeProcess = res;
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
