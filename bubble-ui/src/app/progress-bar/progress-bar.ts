import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-progress-bar',
  imports: [],
  templateUrl: './progress-bar.html',
  styleUrl: './progress-bar.css',
})
export class ProgressBar {

  @Input() progressPercentage: number = 0;
  @Input() currentQuestionNumber: number = 0;
  @Input() totalQuestionNumber: number = 0;
  @Input() backgroundColor: string = 'progress-bar-pink'; // Default color

}
