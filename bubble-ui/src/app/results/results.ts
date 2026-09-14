import { Component, OnInit, WritableSignal, signal, ViewChild, inject} from '@angular/core';
import { Spinner } from '../spinner/spinner';
import { ComicDetails } from '../comic-details/comic-details';
import { ResultsService } from '../services/results-service';
import { ResultModel } from '../models/result-model';
import { SessionService } from '../services/session-service';
import { BaseChartDirective } from 'ng2-charts';
import { ChartConfiguration } from 'chart.js/auto';
import { READING_PROFILE_LABELS, READING_PROFILE_COLORS } from '../constants/reading-profiles.const';
import { ComicModel } from '../models/comic-model';
import { HeaderService } from '../services/header-service';
import { FooterService } from '../services/footer-service';
import { Router } from '@angular/router';
import {TranslatePipe } from "@ngx-translate/core";
declare var bootstrap: any;

@Component({
  selector: 'app-results',
  imports: [Spinner, BaseChartDirective, ComicDetails, TranslatePipe],
  templateUrl: './results.html',
  styleUrl: './results.css',
})
export class Results implements OnInit{

  results: WritableSignal<ResultModel | null> = signal(null);
  loading: WritableSignal<boolean> = signal(false);
  profileLabels = READING_PROFILE_LABELS;
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

  @ViewChild(ComicDetails)
  comicDetails!: ComicDetails;

  constructor(private resultsService: ResultsService, private sessionService: SessionService, private router: Router) { }

  ngOnInit(): void {
    this.header.setConfig({showBorderBottom: false, showRestartBtn: true, showLanguageSelector: false});
    this.footer.setConfig({isResultsPage: true});
    const sessionId = this.sessionService.getSessionId() !== null ? this.sessionService.getSessionId() : null;
    if(!sessionId) {
      console.log("Session ID is null. Cannot fetch results.");
      this.router.navigate(['/']);
    }
    this.loading.set(true);

    this.resultsService.fetchResults().subscribe({ 
      next: (response: ResultModel) => {
        this.results.set(response);

        if(response.recommendedComics.length >= 5) {
          this.comicsToShow.set(response.recommendedComics.slice(0, 5));
        } else {
          this.comicsToShow.set(response.recommendedComics);
        }

        this.pieChartData = {
          labels: Object.keys(response.statistics).map(key => READING_PROFILE_LABELS[key] || key.toString()),
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
        console.log("Error fetching results: ", error);
        this.loading.set(false);
        if(error.statusText === "Unknown Error") {
          this.sessionService.setSessionExpired(true);
          
        }
      }
    });
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
