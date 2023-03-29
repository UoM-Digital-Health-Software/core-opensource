<template>
  <Modal
    ref="confirmationDialog"
    hideCloseButton="true"
    disableBackdropEvent="true"
  >
    <template v-slot:header>
      <h1>{{ title }}</h1>
    </template>

    <template v-slot:body>
      {{ message }}
    </template>

    <template v-slot:footer>
      <div class="modal-buttons">
        <ButtonModal class="cancel" @click="cancel">{{
          cancelButton
        }}</ButtonModal>
        <ButtonModal class="confirm" @click="confirm">{{
          confirmButton
        }}</ButtonModal>
      </div>
    </template>
  </Modal>
</template>

<script>
import Modal from '@/components/Modal';
import ButtonModal from '@/components/ButtonModal';
export default {
  name: 'ConfirmationDialog',
  components: {
    Modal,
    ButtonModal,
  },
  data() {
    return {
      title: undefined,
      message: undefined,
      cancelButton: undefined,
      confirmButton: undefined,
      resolve: undefined,
      reject: undefined,
    };
  },
  methods: {
    cancel() {
      this.$refs.confirmationDialog.closeModal();
      this.resolve(false);
    },
    confirm() {
      this.$refs.confirmationDialog.closeModal();
      this.resolve(true);
    },
    show(options = {}) {
      this.title = options.title;
      this.message = options.message;
      this.confirmButton = options.confirmButton;
      this.cancelButton = options.cancelButton;

      this.$refs.confirmationDialog.openModal();

      return new Promise((resolve, reject) => {
        this.resolve = resolve;
        this.reject = reject;
      });
    },
  },
};
</script>

<style scoped>
input {
  font-size: 1.1em;
}
.modal-buttons > button:last-child {
  margin-left: 10px;
}
.confirm {
  background: #008561;
  border: 2px solid #008561;
  color: white;
}
</style>
