import { Component, signal } from '@angular/core';
import { DefaultLayout } from './default-layout/default-layout';

@Component({
  selector: 'app-root',
  imports: [DefaultLayout],
  templateUrl: './app.html',
  styleUrl: './app.css'
})
export class App {
  protected readonly title = signal('bubble-ui');
}
