import { HttpEvent, HttpRequest, HttpHandlerFn } from "@angular/common/http";
import { Observable, catchError, throwError} from "rxjs"; 
import { Router } from "@angular/router";
import { inject } from '@angular/core';
import { SessionService } from "../services/session-service";

export function errorsInterceptor(
    req: HttpRequest<unknown>,
    next: HttpHandlerFn,
) : Observable<HttpEvent<unknown>> {
    const router = inject(Router);
    const sessionService = inject(SessionService);
    return next(req).pipe(
        catchError((error) => {
            console.error("HTTP error intercepted: ", error);
            if(error.status === 410 && !sessionService.getSessionExpired()) {
                sessionService.setSessionExpired(true);
                router.navigate(['/times-up']);
            }
            if(error.status === 500) {
                sessionService.setSessionExpired(true);
                router.navigate(['/internal-error']);
            }
            if(error.status === 404) {
                sessionService.setSessionExpired(true);
                router.navigate(['/not-found']);
            }
            return throwError(() => error);
        })
    );
}