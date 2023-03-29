<template>
  <p>
    Should you wish to, you can download a copy of your consent form using the
    button below.
  </p>
  <Button @click="downloadConsentPdf" icon="download"
    >Download consent form</Button
  >
  <HorizontalLine />
</template>
<script>
import { StoreService } from '@/services/store.service';
import { ConsentPdfService } from '@/services/consent.pdf.service';
import Button from '@/components/Button';
import HorizontalLine from '@/components/HorizontalLine';

export default {
  name: 'StaticDownloadConsent',
  components: {
    Button,
    HorizontalLine,
  },
  methods: {
    async downloadConsentPdf() {
      const responseId = StoreService.get('responseId');
      const pdfData = await ConsentPdfService.getConsentPdf(responseId);
      this.downloadFile(pdfData, 'consent.pdf');
    },

    downloadFile(fileData, filename) {
      const downloadUrl = window.URL.createObjectURL(
        new Blob([fileData], { type: 'application/pdf' })
      );
      const link = document.createElement('a');
      link.href = downloadUrl;
      link.setAttribute('download', filename);
      document.body.appendChild(link);
      link.click();
      link.remove();
    },
  },
};
</script>
