import { Component, Vue, Inject } from 'vue-property-decorator';

import { IChooseProcess } from '@/shared/model/choose-process.model';
import ChooseProcessService from './choose-process.service';

@Component
export default class ChooseProcessDetailsComponent extends Vue {
  @Inject('chooseProcessService') private chooseProcessService: () => ChooseProcessService;
  public chooseProcess: IChooseProcess = {};

  public isFetching: boolean = false;

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.processInstanceId) {
        vm.retrieveChooseProcess(to.params.processInstanceId);
      }
    });
  }

  public retrieveChooseProcess(chooseProcessId) {
    this.isFetching = true;
    this.chooseProcessService()
      .find(chooseProcessId)
      .then(
        res => {
          this.chooseProcess = res;
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
