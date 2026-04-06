import { Routes } from '@angular/router';
import { Home } from './home/home';
import { Question } from './question/question';

export const routes: Routes = [
    {
        path:'',
        component:Home
    },
    {
        path:'question',
        component:Question
    }

];
