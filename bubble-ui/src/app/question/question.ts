import { Component, OnInit, signal, WritableSignal} from '@angular/core';
import { QuestionService } from '../services/question-service';
import { QuestionResponse } from '../models/question-response';

@Component({
  selector: 'app-question',
  imports: [],
  templateUrl: './question.html',
  styleUrl: './question.css',
})
export class Question implements OnInit {

  private questionNum: number = 0;
  label:WritableSignal<string> = signal("");

  constructor(private questionService: QuestionService) {}

  ngOnInit() {
    this.questionService.fetchData(++this.questionNum).subscribe((response: QuestionResponse) => {
      console.log("response: ", response);
      this.label.set(response.question.label);
    });
  }
}
