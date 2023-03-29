import { StoreService } from '@/services/store.service';
import { ApiService } from '@/services/api.service';
import { v4 as uuidv4 } from 'uuid';

export const IdentityService = {
  async generate(query) {
    let responseId = StoreService.get('responseId');
    const queryParameters = this.getQueryParameters(query);
    const machineId = this.getMachineId();

    if (responseId && queryParameters) {
      responseId = await this.getResponseId({
        responseId,
        ...queryParameters,
        machineId,
      });
    } else if (responseId) {
      responseId = await this.getResponseId({
        responseId,
        machineId,
      });
    } else if (queryParameters) {
      responseId = await this.getResponseId({
        ...queryParameters,
        machineId,
      });
    } else {
      return new Promise((resolve) => {
        resolve(null);
      });
    }

    StoreService.set('responseId', responseId);
    return new Promise((resolve) => {
      resolve(responseId);
    });
  },

  getQueryParameters(query) {
    let studyId, participantId;

    if ('study' in query) {
      studyId = query['study'];
    } else {
      return false;
    }

    if ('id' in query) {
      participantId = query['id'];
    } else {
      return false;
    }

    return { studyId, participantId };
  },

  getMachineId() {
    let machineId;

    if (StoreService.exists('machineId')) {
      machineId = StoreService.get('machineId');
    } else {
      machineId = uuidv4();
      StoreService.set('machineId', machineId);
    }

    return machineId;
  },

  async getResponseId(parameters) {
    return (await ApiService.post('identity', parameters)).data;
  },

  async exitQuestionnaire() {
    const responseId = StoreService.get('responseId');
    await ApiService.post('exit', { responseId });
  },
};
