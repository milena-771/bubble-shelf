import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class SessionService {

    private isSessionExpired: boolean = false;
    
    private isQuizCompleted: boolean = false;

    private sessionId: string | null = null;

    constructor() {}

    resetSession(): void {
        this.isSessionExpired = false;
        this.isQuizCompleted = false;
        this.sessionId = null;
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

    setSessionId(sessionId: string): void {
        this.sessionId = sessionId;
    }
    
    getSessionId(): string | null {
        return this.sessionId;
    }

}