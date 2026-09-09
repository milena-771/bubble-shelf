import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { environment } from '../../environments/environment'; 
import { QuestionResponse } from '../models/question-response';
import { SubmitAnswer } from '../models/submit-answer';

@Injectable({
  providedIn: 'root',
})
export class QuestionService {
  private baseUrl = environment.apiUrl;

  constructor(private http: HttpClient) {}

  fetchFirstQuestion(questionNum:number) {
    return this.http.get<QuestionResponse>(this.baseUrl.concat(`/questions/${questionNum}`));
  }

  fetchNextQuestion( answer: SubmitAnswer) {
    return this.http.post<QuestionResponse>(this.baseUrl.concat(`/answer`), answer);    
  }

  fetchPreviousQuestion(questionNum:number) {
    return this.http.get<QuestionResponse>(this.baseUrl.concat(`/questions/${questionNum}`)); 
  }
}
