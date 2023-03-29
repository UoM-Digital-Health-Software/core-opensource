<template>
  <fieldset>
  <ul class="radio-line">
    <li v-for="item in questionItems" :key="item.id" class="radio-line-item">
      <input
        type="radio"
        :id="generateInputId(name, item.name)"
        :name="name"
        @click="handleUpdateAnswerItems(item)"
        class="radio-line-input"
      />

      <label :for="generateInputId(name, item.name)" class="radio-line-label">
        {{ item.label }}
      </label>
    </li>
  </ul>
  </fieldset>
</template>

<script>
export default {
  name: 'QuestionRadio',
  props: ['name', 'answerItemsArray', 'questionItems'],
  emits: ['selected'],
  data() {
    return {
      answerItems: null,
      currentlySelectedItem: null,
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
    handleUpdateAnswerItems(item) {
      if (this.currentlySelectedItem) {
        this.currentlySelectedItem.selected = false;
      }

      const selectedItem = this.answerItems.find(
        (answerItem) => answerItem.questionItemId === item.id
      );

      this.currentlySelectedItem = selectedItem;
      this.currentlySelectedItem.selected = true;
    },
    generateInputId(questionName, itemName) {
      return questionName + '-' + itemName;
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
  padding: 20px;
}

.simple-radio-input {
  margin: -3px 3px 0px 0px;
  vertical-align: middle;
  background: white;
}
.simple-radio-item {
  padding-bottom: 20px !important;
}

label {
  font-weight: 500;
  font-size: 0.75em;
}

@media only screen and (min-width: 1000px) {
  input {
    transform: scale(2.3);
  }
  .radio-line {
    display: flex;
    padding-left: 50px;
    padding-top: 10px;
    width: 100%;
    flex-wrap: wrap;
  }
  .radio-line-item {
    flex: 0 1 15%;
    list-style: none;
    position: relative;
  }

  .radio-line-item:not(:last-child):after {
    content: '';
    position: absolute;
    height: 4px;
    width: 145%;
    background: #91b7ff;
    top: 9px;
    left: -20%;
    z-index: -1;
  }
  .radio-line-label {
    width: 90px;
    display: block;
    text-align: center;
    margin-left: -38px;
    margin-top: 10px;
    font-size: 0.9em;
  }

  .radio-line-input {
    margin-left: 0px;
    margin-right: -1px;
  }
}

@media only screen and (max-width: 1000px) {
  label {
    padding-left: 10px;
  }

  input {
    transform: scale(2);
  }
}
</style>
