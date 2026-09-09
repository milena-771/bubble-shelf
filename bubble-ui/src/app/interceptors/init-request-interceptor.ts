import { Injectable } from '@angular/core';
import { inject } from '@angular/core';
import { HttpEvent, HttpRequest, HttpHandlerFn } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment'; 
import { SessionService } from '../services/session-service';

export function initRequestInterceptor(
    req: HttpRequest<unknown>,
    next: HttpHandlerFn,
): Observable<HttpEvent<unknown>> {
  const apiKeyName = environment.keyName;
  const apiKeyValue = environment.keyValue;
  const sessionHeaderName = environment.sessionHeaderName;
  const sessionService = inject(SessionService);
  const sessionId = sessionService.getSessionId() !== null ? sessionService.getSessionId() : null;
  if(sessionId) {
    req = req.clone({ 
        headers: req.headers.set(sessionHeaderName, sessionId).set(apiKeyName, apiKeyValue)
    });
   }
   return next(req);
}
