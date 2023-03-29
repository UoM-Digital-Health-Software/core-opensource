<template>
  <div class="question">

    <fieldset v-if="question.type === 'RADIO_LINE'">
      <legend class="question-text">
        {{ question.text }}
      </legend>
      <p>
        <em>{{ question.context }}</em>
      </p>

      <QuestionRadio
        :questionItems="question.questionItems"
        :name="question.name"
        :answerItemsArray="answer.answerItems"
      />
    </fieldset>

    <div  v-else-if="question.type === 'CHECKBOXES'">   
      <p class="question-text">
        {{ question.text }}
      </p>
      <p>
        <em>{{ question.context }}</em>
    </p>

    <QuestionCheckbox
      :questionItems="question.questionItems"
      :answerItemsArray="answer.answerItems"
    /></div>

  </div>
</template>

<script>
import QuestionCheckbox from '@/components/question/QuestionCheckbox.vue';
import QuestionRadio from '@/components/question/QuestionRadio.vue';

export default {
  name: 'Question',
  components: {
    QuestionCheckbox,
    QuestionRadio,
  },
  props: ['question', 'answerObject'],
  data() {
    return {
      answer: null,
    };
  },
  created() {
    this.answer = this.answerObject;
  },
  watch: {
    answerObject: function (answerObject) {
      this.answer = answerObject;
    },
  },
};
</script>

<style scoped>
fieldset { 
  border: 0;
 }
.question {
  border-bottom: 1px solid #b1b1b1;
  padding: 20px 1%;
}

.question-text {
  font-size: 22px;
  font-weight: 500;
  color: #3a3c3e;
}

@media only screen and (min-width: 1000px) {
  :deep(ul) {
    list-style-type: none;
    margin: 0;
    padding: 0;
  }

  :deep(li) {
    padding-bottom: 10px;
  }
}

@media only screen and (max-width: 1000px) {
  :deep(ul) {
    list-style-type: none;
    margin: 0;
    padding: 0;
  }

  :deep(li) {
    flex: 0 1 10%;
    list-style: none;
    position: relative;
    padding-bottom: 10px;
  }
}
</style>
