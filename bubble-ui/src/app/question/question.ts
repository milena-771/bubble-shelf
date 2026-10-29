import { Component, OnInit, signal, WritableSignal, inject} from '@angular/core';
import { QuestionService } from '../services/question-service';
import { SessionService } from '../services/session-service';
import { QuestionResponse } from '../models/question-response';
import { SubmitAnswer } from '../models/submit-answer';
import { Spinner } from '../spinner/spinner';
import { ProgressBar } from '../progress-bar/progress-bar';
import { Router } from '@angular/router';
import { HeaderService } from '../services/header-service';
import { FooterService } from '../services/footer-service';
import { ProgressModel } from '../models/progress-model';
import { TranslatePipe } from "@ngx-translate/core";
import { LanguageService } from '../services/language-service';


@Component({
  selector: 'app-question',
  imports: [Spinner, ProgressBar, TranslatePipe],
  templateUrl: './question.html',
  styleUrl: './question.css',
})
export class Question implements OnInit {

  private questionNum: number = 0;
  dataQuiz: WritableSignal<QuestionResponse | null> = signal(null);
  progressBar: WritableSignal<ProgressModel | null> = signal(null);
  loading: WritableSignal<boolean> = signal(false);
  selectedAnswerId = signal<number | 0>(0);
  currentPercentage: WritableSignal<number> = signal(0);
  progressColorPink: string = 'progress-bar-pink';

  footer = inject(FooterService);
  header = inject(HeaderService);
  languageService = inject(LanguageService);

  constructor(private questionService: QuestionService, private sessionService: SessionService, private router: Router) {}

  ngOnInit() {
    this.header.setConfig({
      showBorderBottom: true,
      showRestartBtn: false,
      showLanguageSelector: true
    });
    this.footer.setConfig({
      isResultsPage: false
    });
    this.loading.set(true);
    this.sessionService.resetSession();
    this.questionService.fetchFirstQuestion(++this.questionNum).subscribe({
      next: (response: QuestionResponse) => {
        console.log("First question response: ", response);
        this.dataQuiz.set(response);
        this.progressBar.set(response.progress);
        this.sessionService.setSessionId(response.sessionId);
        if(response.language != this.languageService.currentLang()) this.languageService.changeLanguage(response.language);
        this.loading.set(false);
      },
      error: (error) => {
        console.log("Error fetching first question: ", error);
        this.loading.set(false);
      }
    });

    // Écoute les changements de langue provenant du Header
    this.languageService.languageChangedObservable.subscribe(lang => {

      // Récupère la question actuellement affichée
      const questionOrder = this.dataQuiz()?.question.order;
      
      if (!questionOrder) return;

      this.loading.set(true);

      // Demande au backend la même question dans la nouvelle langue
      this.fetchQuestionByOrder(questionOrder);
    });
  }

  onAnswerSelected(answerId: number) {
    this.selectedAnswerId.set(answerId);
  }

  nextQuestion() {
    this.loading.set(true);
    const answerId = this.selectedAnswerId();
    const questionOrder = this.dataQuiz()?.question.order;
    if (!questionOrder) return;
    if(!answerId) return;   
    const payload: SubmitAnswer = {
      questionOrder,
      answerId
    };
    console.log("Submitting answer: ", payload);
    this.questionService.fetchNextQuestion(payload).subscribe({
      next: (response: QuestionResponse) => {
        this.sessionService.setQuizCompleted(response.progress.completed);
        this.progressBar.set(response.progress);
         if(response.language != this.languageService.currentLang()) this.languageService.changeLanguage(response.language);
        this.currentPercentage.set((response.progress.currentQuestionNumber / response.progress.totalQuestionNumber) * 100);
        if(!response.progress.completed) { 
          this.dataQuiz.set(response);
          this.selectedAnswerId.set(response.selectedAnswer);
        } else {
          this.router.navigate(['/results']);
        }
        this.loading.set(false);
      },
      error: (error) => {
        console.error(error);
        this.loading.set(false);
      }
    });
  }

  fetchQuestionByOrder(questionOrder: number) {
      this.questionService.fetchRefreshQuestion(questionOrder).subscribe({
      next: (response: QuestionResponse) => {
        this.sessionService.setQuizCompleted(response.progress.completed);
        this.progressBar.set(response.progress);
        if(response.language != this.languageService.currentLang()) this.languageService.changeLanguage(response.language);
        this.currentPercentage.set((response.progress.currentQuestionNumber / response.progress.totalQuestionNumber) * 100);
        if(!response.progress.completed) { 
          this.dataQuiz.set(response);
          //this.selectedAnswerId.set(response.selectedAnswer);
        } else {
          this.router.navigate(['/results']);
        }
        this.loading.set(false);
      },
      error: (error) => {
        console.error(error);
        this.loading.set(false);
      }
    });
  }

  previousQuestion() {
    this.loading.set(true);
    let questionOrder = this.dataQuiz()?.question.order;
    this.questionService.fetchPreviousQuestion(--questionOrder!).subscribe({
      next: (response: QuestionResponse) => {
        this.dataQuiz.set(response);
        this.progressBar.set(response.progress);
        if(response.language != this.languageService.currentLang())  this.languageService.changeLanguage(response.language);
        this.currentPercentage.set((response.progress.currentQuestionNumber / response.progress.totalQuestionNumber) * 100);
        this.selectedAnswerId.set(response.selectedAnswer);
        this.loading.set(false);
      },
      error: (error) => {
        console.log("Error fetching previous question: ", error);
        this.loading.set(false);
      }
    });
  }

  restart(){
    //window.location.reload();
    this.sessionService.resetSession();
    this.questionService.fetchFirstQuestion(1).subscribe({
      next: (response: QuestionResponse) => {
        console.log("First question response: ", response);
        this.dataQuiz.set(response);
        this.languageService.changeLanguage(response.language);
        this.progressBar.set(response.progress);
        this.sessionService.setSessionId(response.sessionId);
        this.loading.set(false);
      },
      error: (error) => {
        console.log("Error fetching first question: ", error);
        this.loading.set(false);
      }
    });
  }
}
