import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { environment } from '../../environments/environment'; 
import { ResultModel } from '../models/result-model';

@Injectable({
    providedIn: 'root',
})
export class ResultsService {
    private baseUrl = environment.apiUrl;
    
    constructor(private http: HttpClient) {}

    fetchResults() {
        return this.http.get<ResultModel>(this.baseUrl.concat(`/results`)); 
    }
}