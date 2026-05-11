import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../environments/environment'; 
import { QuestionResponse } from '../models/question-response';

@Injectable({
  providedIn: 'root',
})
export class QuestionService {
  private baseUrl = environment.apiUrl;
  private apiKeyName = environment.keyName;
  private apiKeyValue = environment.keyValue;

  constructor(private http: HttpClient) {}

  fetchData(questionNum:number) {
    return this.http.get<QuestionResponse>(this.baseUrl.concat(`/questions/${questionNum}`), {headers: {[this.apiKeyName]: this.apiKeyValue}});
  }
}
