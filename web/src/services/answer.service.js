import { ApiService } from '@/services/api.service';
import { QuestionService } from './question.service';
import { StoreService } from '@/services/store.service';

export const AnswerService = {
  async submitAnswerPage(answerPage) {
    let page = (await ApiService.post('answers', answerPage)).data;
    if (page) {
      QuestionService.sortQuestions(page);
    }
    return page;
  },

  createAnswerPage(questionPageId, questions = []) {
    const responseId = StoreService.get('responseId');
    const answers = this.createAnswers(questions);

    const answerPage = {
      questionPageId,
      responseId,
      answers,
    };

    return answerPage;
  },

  createAnswers(questions) {
    const answers = [];
    for (const question of questions) {
      const answer = this.createAnswer(question);
      question.answer = answer;
      answers.push(answer);
    }
    return answers;
  },

  createAnswer(question) {
    let answerItems = this.createAnswerItems(question.questionItems);

    let answer = {
      questionId: question.id,
      answerItems,
    };
    return answer;
  },

  createAnswerItems(questionItems) {
    return questionItems.map((questionItem) => {
      questionItem.answerItem = {
        questionItemId: questionItem.id,
        selected: false,
      };
      return questionItem.answerItem;
    });
  },

  getMandatoryAnswerItems(questions) {
    const mandatoryItems = [];
    for (const question of questions) {
      for (const questionItem of question.questionItems) {
        if (questionItem.mandatory) {
          mandatoryItems.push(questionItem.answerItem);
        }
      }
    }
    return mandatoryItems;
  },
};
