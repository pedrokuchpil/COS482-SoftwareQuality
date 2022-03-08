import { Component, Vue, Inject } from 'vue-property-decorator';

import { IReadProcess } from '@/shared/model/read-process.model';
import ReadProcessService from './read-process.service';

@Component
export default class ReadProcessDetailsComponent extends Vue {
  @Inject('readProcessService') private readProcessService: () => ReadProcessService;
  public readProcess: IReadProcess = {};

  public isFetching: boolean = false;

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.processInstanceId) {
        vm.retrieveReadProcess(to.params.processInstanceId);
      }
    });
  }

  public retrieveReadProcess(readProcessId) {
    this.isFetching = true;
    this.readProcessService()
      .find(readProcessId)
      .then(
        res => {
          this.readProcess = res;
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
