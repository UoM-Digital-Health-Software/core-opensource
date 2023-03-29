<template>
  <div @click="onPageClick">
    <MissingIds v-if="missingIds"></MissingIds>
    <Loading v-else-if="isLoading"></Loading>
    <div v-else-if="questionPage">
      <StaticPage
        v-if="isStaticPage(questionPage.name)"
        :page="questionPage"
        :buttonClick="nextPage"
      ></StaticPage>
      <QuestionPage
        v-else
        :page="questionPage"
        :buttonClick="nextPage"
      ></QuestionPage>
    </div>

    <Completed v-else></Completed>
  </div>

  <ConfirmationDialog ref="exitPageModal" />
</template>

<script>
import { ApiService } from '@/services/api.service';
import { IdentityService } from '@/services/identity.service';
import { QuestionService } from '@/services/question.service';
import { StaticPageService } from '@/services/static.page.service';

import MissingIds from './MissingIds.vue';
import Loading from './Loading.vue';
import Completed from './Completed.vue';
import StaticPage from '../components/StaticPage.vue';
import QuestionPage from '../components/QuestionPage.vue';

import ConfirmationDialog from '../components/ConfirmationDialog';

export default {
  name: 'Main',
  components: {
    MissingIds,
    Loading,
    StaticPage,
    QuestionPage,
    Completed,
    ConfirmationDialog,
  },
  data() {
    return {
      missingIds: false,
      questionPage: null,
      isCompleted: false,
    };
  },
  mounted() {
    this.handleBackButtonClick();
  },
  async created() {
    await this.fetchCSRFToken();

    const query = this.$route.query;
    const responseId = await IdentityService.generate(query);

    if (!responseId) {
      this.missingIds = true;
    } else {
      this.questionPage = await QuestionService.getNextQuestionPage(responseId);
    }
  },
  computed: {
    isLoading: function () {
      return this.questionPage == null && !this.isCompleted;
    },
  },
  methods: {
    fetchCSRFToken() {
      return ApiService.get('csrf');
    },
    isStaticPage: function (pageName) {
      return StaticPageService.exists(pageName);
    },
    nextPage(nextPage) {
      this.questionPage = nextPage;
      this.isCompleted = nextPage === null;
    },
    onPageClick() {
      if (!this.$route.hash) {
        window.location.hash = 'active';
      }
    },
    handleBackButtonClick() {
      window.addEventListener('popstate', async () => {
        if (!window.location.hash && this.$refs.exitPageModal) {
          const confirm = await this.$refs.exitPageModal.show({
            title: 'Do you want to leave this questionnaire?',
            message:
              'In this questionnaire you cannot view your previous answers. By going back you will leave this page.',
            confirmButton: 'Leave the page',
            cancelButton: "Don't leave the page",
          });
          if (confirm) {
            window.history.go(-1);
          } else {
            window.location.hash = 'active';
          }
        }
      });
    },
  },
};
</script>
