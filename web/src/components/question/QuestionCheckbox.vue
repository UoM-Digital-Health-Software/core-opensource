<template>
  <ul class="checkbox-items">
    <li v-for="questionItem in questionItems" :key="questionItem.id">
      <label>
        <input
          type="checkbox"
          @change="updateAnswerItem(questionItem, $event.target.checked)"
        />
        <div class="label">
          {{ questionItem.label }}
        </div>
      </label>
    </li>
  </ul>
</template>

<script>
export default {
  name: 'QuestionCheckbox',
  props: ['questionItem', 'answerItemsArray', 'questionItems'],
  data() {
    return {
      answerItems: [],
    };
  },
  created() {
    this.answerItems = this.answerItemsArray;
  },
  watch: {
    answerItemsArray: function (answerItemsArray) {
      this.answerItems = answerItemsArray;
    },
  },
  methods: {
    updateAnswerItem(questionItem, selected) {
      questionItem.answerItem.selected = selected;
    },
  },
};
</script>

<style scoped>
.question {
  border-bottom: 1px solid #b1b1b1;
  padding: 20px;
}

.checkbox-items {
  padding-top: 20px;
}

label {
  display: table;
}

input[type='checkbox'] {
  display: table-cell;
  vertical-align: middle;
  border-spacing: 0;
  width: 25px;
  height: 25px;
  background: white;
  border: 2px solid #555;
  padding-right: 10px;
  transform: scale(1.2);
}

.label {
  display: table-cell;
  vertical-align: middle;
  border-spacing: 0;
  padding-left: 20px;
  font-weight: 500;
  font-size: 1em;
  text-align: left;
}

@media only screen and (max-width: 1000px) {
  input[type='checkbox'] {
    float: left;
  }
}

.required {
  font-size: 0.8em;
}

.required strong {
  color: #1b3f75;
}
</style>
