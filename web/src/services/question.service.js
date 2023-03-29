import { ApiService } from '@/services/api.service';

export const QuestionService = {
  async getNextQuestionPage(responseId) {
    const page = (
      await ApiService.query('questions', { responseId: responseId })
    ).data;

    if (page) {
      this.sortQuestions(page);
    }

    return page;
  },

  sortQuestions(page) {
    this.sortByOrder(page.questions);
    for (const question of page.questions) {
      this.sortByOrder(question.questionItems);
    }
  },

  sortByOrder(array) {
    array.sort((a, b) => a.order - b.order);
  },
};
