import { Routes } from '@angular/router';
import { Home } from './home/home';
import { Question } from './question/question';
import { TimesUp } from './errors/times-up/times-up';
import { Results } from './results/results';
import { TermsOfUse } from './terms-of-use/terms-of-use';
import { InternalError } from './errors/internal-error/internal-error';
import { NotFound } from './errors/not-found/not-found';

export const routes: Routes = [
    {
        path:'',
        component:Home
    },
    {
        path:'question',
        component:Question
    },
    {
        path:'results',
        component:Results
    },
    {
        path:'terms-of-use',
        component:TermsOfUse    
    },
    {
        path:'times-up',
        component:TimesUp
    },
    {
        path:'internal-error',
        component:InternalError
    },
    {
        path:'not-found',
        component:NotFound
    }
];
