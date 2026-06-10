import { Component, OnInit, signal, WritableSignal} from '@angular/core';
import { QuestionService } from '../services/question-service';
import { QuestionResponse } from '../models/question-response';
import { SubmitAnswer } from '../models/submit-answer';
import { Spinner } from '../spinner/spinner';

@Component({
  selector: 'app-question',
  imports: [Spinner],
  templateUrl: './question.html',
  styleUrl: './question.css',
})
export class Question implements OnInit {

  private questionNum: number = 0;
  dataQuiz: WritableSignal<QuestionResponse | null> = signal(null);
  loading: WritableSignal<boolean> = signal(false);
  sessionId: WritableSignal<string | null> = signal(null);
  selectedAnswerId = signal<number | 0>(0);

  constructor(private questionService: QuestionService) {}

  ngOnInit() {
    this.loading.set(true);
    this.questionService.fetchFirstQuestion(++this.questionNum).subscribe({
      next: (response: QuestionResponse) => {
        console.log("response: ", response);
        this.dataQuiz.set(response);
        this.sessionId.set(response.sessionId);
        this.loading.set(false);
      },
      error: (error) => {
        console.log("Error fetching first question: ", error);
        this.loading.set(false);
      }
    });
  }

  onAnswerSelected(answerId: number) {
    this.selectedAnswerId.set(answerId);
  }

  nextQuestion() {
    this.loading.set(true);
    const answerId = this.selectedAnswerId();
    const questionOrder = this.dataQuiz()?.question.order;
    if (!questionOrder) return;
    if(!answerId) return;   
    const payload: SubmitAnswer = {
      questionOrder,
      answerId
    };
    console.log("Submitting answer: ", payload);
    this.questionService.fetchNextQuestion(this.sessionId()!, payload).subscribe({
      next: (response: QuestionResponse) => {
        console.log("response: ", response);
        this.dataQuiz.set(response);
        this.selectedAnswerId.set(response.selectedAnswer);
        this.loading.set(false);
      },
      error: (error) => {
        console.error(error);
        if (error.status === 410) {
          
        }
        this.loading.set(false);
      }
    });
  }

  previousQuestion() {
    this.loading.set(true);
    let questionOrder = this.dataQuiz()?.question.order;
    this.questionService.fetchPreviousQuestion(this.sessionId()!, --questionOrder!).subscribe({
      next: (response: QuestionResponse) => {
        console.log("response: ", response);
        this.dataQuiz.set(response);
        this.selectedAnswerId.set(response.selectedAnswer);
        this.loading.set(false);
      },
      error: (error) => {
        console.log("Error fetching previous question: ", error);
        this.loading.set(false);
      }
    });
  }
}
