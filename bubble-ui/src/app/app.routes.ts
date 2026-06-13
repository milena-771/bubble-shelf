import { Routes } from '@angular/router';
import { Home } from './home/home';
import { Question } from './question/question';
import { TimesUp } from './times-up/times-up';
import { Results } from './results/results';

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
    }

];
