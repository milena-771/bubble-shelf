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

  selectedAnswerId = signal<number | null>(null);

  constructor(private questionService: QuestionService) {}

  ngOnInit() {
    this.loading.set(true);
    this.questionService.fetchFirstQuestion(++this.questionNum).subscribe((response: QuestionResponse) => {
      console.log("response: ", response);
      this.dataQuiz.set(response);
      this.loading.set(false);
    });
  }

  onAnswerSelected(answerId: number) {
    this.selectedAnswerId.set(answerId);
  }

  nextQuestion() {
   const answerId = this.selectedAnswerId();
   const questionOrder = this.dataQuiz()?.question.order;
   if (!questionOrder) return;
   if(!answerId) return;
   
   const payload: SubmitAnswer = {
    questionOrder,
    answerId
  };
   console.log("Submitting answer: ", payload);
  }
}
