import { Component, signal } from '@angular/core';
import { DefaultLayout } from './default-layout/default-layout';
import { TranslateService } from "@ngx-translate/core";
import { LanguageService } from './services/language-service';

@Component({
  selector: 'app-root',
  imports: [DefaultLayout],
  templateUrl: './app.html',
  styleUrl: './app.css'
})
export class App {
  protected readonly title = signal('bubble-ui');

  constructor(private translate: TranslateService, private languageService: LanguageService) {}

  ngOnInit() {
    this.languageService.initLanguage();
  }
}
