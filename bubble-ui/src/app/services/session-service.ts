import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class SessionService {

    private isSessionExpired: boolean = false;
    
    private isQuizCompleted: boolean = false;

    constructor() {}

    resetSession(): void {
        this.isSessionExpired = false;
        this.isQuizCompleted = false;
    }

    setQuizCompleted(completed: boolean): void {
        this.isQuizCompleted = completed;
    }

    getQuizCompleted(): boolean {
        return this.isQuizCompleted;
    }   

    setSessionExpired(expired: boolean): void {
        this.isSessionExpired = expired;
    }

    getSessionExpired(): boolean {
        return this.isSessionExpired;
    }



}