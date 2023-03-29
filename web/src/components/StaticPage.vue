<template>
  <Intro :title="page.title"></Intro>

  <div class="content">
    <component v-bind:is="getComponent(page.name)" />
  </div>

  <Button icon="arrow" @click="onContinueClick">Continue</Button>

  <BottomButtons :showSkip="false" :onExit="onExit"></BottomButtons>
</template>

<script>
import { defineAsyncComponent } from 'vue';
import { StaticPageService } from '@/services/static.page.service';
import { AnswerService } from '@/services/answer.service';
import { IdentityService } from '@/services/identity.service';
import Intro from '@/components/Intro';
import Button from '@/components/Button';
import BottomButtons from '@/components/BottomButtons';

export default {
  name: 'StaticPage',
  components: {
    Intro,
    Button,
    BottomButtons,
  },
  props: ['page', 'buttonClick'],
  data() {
    return {
      answerPage: null,
    };
  },
  created() {
    this.answerPage = AnswerService.createAnswerPage(this.page.id);
  },
  watch: {
    page: function () {
      this.answerPage = AnswerService.createAnswerPage(this.page.id);
    },
  },
  methods: {
    getComponent(pageName) {
      const componentName = StaticPageService.getComponentName(pageName);
      return defineAsyncComponent(() => import('@/content/' + componentName));
    },
    onContinueClick() {
      this.answerPage.skipChosen = false;
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

<style>
.content {
  max-width: 900px;
}

h3 {
  margin: 30px 0 10px 0;
  font-size: 22px;
  padding: 0;
  color: #222;
  font-weight: bold;
}

p {
  margin: 0 0 1.5em 0;
  padding: 0;
}

p,
li {
  font-size: 18px;
  color: #555;
}

li {
  margin: 0 0 1em 0;
  padding: 0;
}

figcaption {
  font-size: 16px;
  color: #555;
}

a {
  color: #3d5c8a;
  text-decoration: none;
  font-weight: bold;
}
</style>
