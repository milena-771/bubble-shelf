import { Component, OnInit, WritableSignal, signal } from '@angular/core';
import { Spinner } from '../spinner/spinner';
import { ResultsService } from '../services/results-service';
import { ResultModel } from '../models/result-model';
import { SessionService } from '../services/session-service';
import { BaseChartDirective } from 'ng2-charts';
import { ChartConfiguration, ChartType } from 'chart.js/auto';
import { READING_PROFILE_LABELS, READING_PROFILE_COLORS } from '../constants/reading-profiles.const';

@Component({
  selector: 'app-results',
  imports: [Spinner, BaseChartDirective],
  templateUrl: './results.html',
  styleUrl: './results.css',
})
export class Results implements OnInit{

  results: WritableSignal<ResultModel | null> = signal(null);
  loading: WritableSignal<boolean> = signal(false);
  profileLabels = READING_PROFILE_LABELS;
  profileColors = READING_PROFILE_COLORS;
  
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

  constructor(private resultsService: ResultsService, private sessionService: SessionService) { }

  ngOnInit(): void {
    const sessionId = this.sessionService.getSessionId();
    this.loading.set(true);
    this.resultsService.fetchResults(sessionId!).subscribe({
      next: (response: ResultModel) => {
        console.log("response: ", response);
        this.results.set(response);
        //this.pieChartData.labels = Array.from(response.statistics.keys()).map(key => key.toString());
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
      }
    });
  }
}
