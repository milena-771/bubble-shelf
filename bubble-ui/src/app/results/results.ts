import { Component, OnInit, WritableSignal, signal, ViewChild, inject} from '@angular/core';
import { Spinner } from '../spinner/spinner';
import { ComicDetails } from '../comic-details/comic-details';
import { ResultsService } from '../services/results-service';
import { ResultModel } from '../models/result-model';
import { SessionService } from '../services/session-service';
import { BaseChartDirective } from 'ng2-charts';
import { ChartConfiguration } from 'chart.js/auto';
import { READING_PROFILE_COLORS } from '../constants/reading-profiles.const';
import { ComicModel } from '../models/comic-model';
import { HeaderService } from '../services/header-service';
import { FooterService } from '../services/footer-service';
import { Router } from '@angular/router';
import { TranslatePipe, TranslateService } from "@ngx-translate/core";
import { LanguageService } from '../services/language-service';
declare var bootstrap: any;

@Component({
  selector: 'app-results',
  imports: [Spinner, BaseChartDirective, ComicDetails, TranslatePipe],
  templateUrl: './results.html',
  styleUrl: './results.css',
})
export class Results implements OnInit {

  results: WritableSignal<ResultModel | null> = signal(null);
  loading: WritableSignal<boolean> = signal(false);
  profileColors = READING_PROFILE_COLORS;
  comicsToShow: WritableSignal<ComicModel[]> = signal([]);
  
  public readonly pieChartType = 'pie' as const;

  public pieChartData: ChartConfiguration<'pie'>['data'] = {
    labels: [],
    datasets: [
      {
        data: []
      }
    ]
  };

  public pieChartOptions: ChartConfiguration<'pie'>['options'] = {
    plugins: {
      legend: {
        position: 'bottom',
        labels: {
          color: '#2F3640'
        }
      }
    }
  }

  footer = inject(FooterService);
  header = inject(HeaderService);
  languageService = inject(LanguageService);
  translate = inject(TranslateService);

  @ViewChild(ComicDetails)
  comicDetails!: ComicDetails;

  constructor(private resultsService: ResultsService, private sessionService: SessionService, private router: Router) { }

  ngOnInit(): void {
    this.header.setConfig({showBorderBottom: false, showRestartBtn: true, showLanguageSelector: true});
    this.footer.setConfig({isResultsPage: true});
    const sessionId = this.sessionService.getSessionId() !== null ? this.sessionService.getSessionId() : null;
    if(!sessionId) {
      console.log("Session ID is null. Cannot fetch results.");
      this.router.navigate(['/']);
    }
    this.loading.set(true);

    this.fetchResults();
    
    // Écoute les changements de langue provenant du Header
    this.languageService.languageChangedObservable.subscribe(lang => {
      this.loading.set(true);
      this.fetchResults();
    });
  }

  fetchResults() {
    this.resultsService.fetchResults().subscribe({ 
      next: (response: ResultModel) => {
        this.results.set(response);
        if(response.language != this.languageService.currentLang()) this.languageService.changeLanguage(response.language);
        if(response.recommendedComics.length >= 5) {
          this.comicsToShow.set(response.recommendedComics.slice(0, 5));
        } else {
          this.comicsToShow.set(response.recommendedComics);
        }
        this.pieChartData = {
          labels: this.updatePieChartLabels(response.statistics),
          datasets: [
            {
              data: Object.values(response.statistics),
              backgroundColor: Object.keys(response.statistics).map(key => READING_PROFILE_COLORS[key] || '#000')
            }
          ]
        };
        this.loading.set(false);
      },
      error: (error) => {
        this.loading.set(false);
        if(error.statusText === "Unknown Error") {
          this.sessionService.setSessionExpired(true);
          
        }
      }
    });
  }

  private  updatePieChartLabels(statistics: Map<number, number>): string[] {
    return Object.keys(statistics).map(key =>
      this.translate.instant('profiles.' + key)
    );
  }

  openComicDetails(comic: ComicModel) {
    this.comicDetails.selectedComic.set(comic);
    const modalElement = document.getElementById('comicDetailsModal');
    if (modalElement) {
      const modal = new bootstrap.Modal(modalElement);
      modal.show();
    }
  }

  loadMoreComics() {
    const currentComics = this.results()?.recommendedComics || [];
    this.comicsToShow.set(currentComics);
  }
}
