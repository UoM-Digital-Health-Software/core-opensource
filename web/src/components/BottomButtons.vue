<template>
  <HorizontalLine />
  <Button v-if="showSkip" @click="skipClick" icon="arrow"
    >Skip this questionnaire</Button
  >
  <Button @click="onExitClick" icon="exit" color="grey">Exit</Button>

  <ConfirmationDialog ref="exitPageModal" />
</template>

<script>
import Button from '@/components/Button';
import HorizontalLine from '@/components/HorizontalLine';
import ConfirmationDialog from '../components/ConfirmationDialog';

export default {
  name: 'BottomButtons',
  components: {
    Button,
    HorizontalLine,
    ConfirmationDialog,
  },
  props: ['showSkip', 'skipClick', 'onExit'],
  methods: {
    async onExitClick() {
      const confirm = await this.$refs.exitPageModal.show({
        title: 'Do you want to end this questionnaire?',
        message:
          'Once you exit the questionnaire, you will be unable to answer any further questions.',
        confirmButton: 'End questionnaire',
        cancelButton: 'Continue with questionnaire',
      });
      if (confirm) {
        this.onExit();
      }
    },
  },
};
</script>
