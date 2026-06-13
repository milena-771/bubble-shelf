import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { environment } from '../../environments/environment'; 
import { ResultModel } from '../models/result-model';

@Injectable({
    providedIn: 'root',
})
export class ResultsService {
    private baseUrl = environment.apiUrl;
    private apiKeyName = environment.keyName;
    private apiKeyValue = environment.keyValue;
    
    constructor(private http: HttpClient) {}

    fetchResults(sessionId: string) {
        let headers = new HttpHeaders({
            [this.apiKeyName]: this.apiKeyValue,
            'X-Test-Session-Id': sessionId
        });
        return this.http.get<ResultModel>(this.baseUrl.concat(`/results`), {headers: headers});
    }
}