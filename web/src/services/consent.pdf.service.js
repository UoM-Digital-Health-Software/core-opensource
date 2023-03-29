import { ApiService } from '@/services/api.service';

export const ConsentPdfService = {
  async getConsentPdf(responseId) {
    return (
      await ApiService.query(
        'pdf',
        { responseId: responseId },
        { responseType: 'arraybuffer' }
      )
    ).data;
  },
};
