import { Routes } from '@angular/router';
import { Home } from './home/home';
import { Question } from './question/question';
import { TimesUp } from './times-up/times-up';
import { Results } from './results/results';
import { TermsOfUse } from './terms-of-use/terms-of-use';

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
        path:'times-up',
        component:TimesUp
    },
    {
        path:'results',
        component:Results
    },
    {
        path:'terms-of-use',
        component:TermsOfUse    
    }

];
