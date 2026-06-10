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
  private apiKeyName = environment.keyName;
  private apiKeyValue = environment.keyValue;

  constructor(private http: HttpClient) {}


  fetchFirstQuestion(questionNum:number) {
    let headers = new HttpHeaders({
        [this.apiKeyName]: this.apiKeyValue
    });
    return this.http.get<QuestionResponse>(this.baseUrl.concat(`/questions/${questionNum}`), {headers: headers});
  }

  fetchNextQuestion(sessionId: string, answer: SubmitAnswer) {
    let headers = new HttpHeaders({
      [this.apiKeyName]: this.apiKeyValue,
      'X-Test-Session-Id': sessionId
    });
    return this.http.post<QuestionResponse>(this.baseUrl.concat(`/answer`), answer, {headers: headers});    
  }

  fetchPreviousQuestion(sessionId: string,questionNum:number) {
    let headers = new HttpHeaders({
      [this.apiKeyName]: this.apiKeyValue,
      'X-Test-Session-Id': sessionId
    });
    return this.http.get<QuestionResponse>(this.baseUrl.concat(`/questions/${questionNum}`), {headers: headers}); 
  }
}
