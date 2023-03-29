<template>
  <Intro :title="page.title" :context="page.text" />

  <HorizontalLine v-if="page.questions.length > 0" />

  <Questions
    v-if="answerPage != null"
    :questionsObject="page.questions"
    ref="questions"
  />

  <Button icon="arrow" @click="onContinueClick">Continue</Button>

  <BottomButtons
    :showSkip="page.showSkip"
    :skipClick="onSkipClick"
    :onExit="onExit"
  ></BottomButtons>
</template>

<script>
import Intro from '@/components/Intro';
import Questions from '@/components/question/Questions.vue';
import Button from '@/components/Button';
import HorizontalLine from '@/components/HorizontalLine';
import BottomButtons from '@/components/BottomButtons';
import { AnswerService } from '@/services/answer.service';
import { IdentityService } from '@/services/identity.service';

export default {
  name: 'QuestionPage',
  components: {
    Intro,
    Questions,
    Button,
    HorizontalLine,
    BottomButtons,
  },
  props: ['page', 'buttonClick'],
  data() {
    return {
      answerPage: null,
    };
  },
  created() {
    this.answerPage = AnswerService.createAnswerPage(
      this.page.id,
      this.page.questions
    );
  },
  watch: {
    page: function () {
      this.answerPage = AnswerService.createAnswerPage(
        this.page.id,
        this.page.questions
      );
    },
  },
  methods: {
    onContinueClick() {
      const unanswered = this.$refs.questions.checkUnanswered();
      if (!unanswered) {
        this.answerPage.skipChosen = false;
        this.saveAndContinue();
      }
    },
    onSkipClick() {
      this.answerPage.skipChosen = true;
      this.saveAndContinue();
      this.scrollToTop();
    },
    async saveAndContinue() {
      const nextQuestionPage = await AnswerService.submitAnswerPage(
        this.answerPage
      );
      this.buttonClick(nextQuestionPage);
    },
    scrollToTop() {
      window.scrollTo(0, 0);
    },
    async onExit() {
      await IdentityService.exitQuestionnaire();
      this.buttonClick(null);
    },
  },
};
</script>
