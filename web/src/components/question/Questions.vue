<template>
  <div v-for="question in questions" :key="question.id">
    <Question :question="question" :answerObject="question.answer" />
  </div>

  <div>
    <div class="mandatory" v-show="isMandatoryUnanswered()">
      Please complete all of the mandatory statements to continue.
    </div>
  </div>
</template>

<script>
import Question from '@/components/question/Question.vue';
import { AnswerService } from '@/services/answer.service';

export default {
  name: 'Questions',
  components: {
    Question,
  },
  props: ['questionsObject'],
  data() {
    return {
      questions: null,
      mandatoryAnswerItems: [],
    };
  },
  created() {
    this.questions = this.questionsObject;
  },
  watch: {
    questionsObject: function () {
      this.questions = this.questionsObject;
    },
  },
  methods: {
    checkUnanswered() {
      this.getMandatoryAnswerItems();
      return this.isMandatoryUnanswered();
    },
    getMandatoryAnswerItems() {
      this.mandatoryAnswerItems = AnswerService.getMandatoryAnswerItems(
        this.questions
      );
    },
    isMandatoryUnanswered() {
      return this.mandatoryAnswerItems.some((item) => !item.selected);
    },
  },
};
</script>

<style scoped>
.mandatory {
  background: #fff8e1;
  border-left: 8px solid #ffd54f;
  padding: 1em 2em;
  margin: 2em 0 1em 0;
  display: inline-block;
}
</style>
