import { Injectable, signal } from '@angular/core';
import { TranslateService } from "@ngx-translate/core";
import { Subject } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class LanguageService {

  currentLang = signal('en');

  // Émet un événement lorsqu'une nouvelle langue est sélectionnée
  private languageChanged = new Subject<string>();

  // Observable accessible aux composants
  languageChangedObservable = this.languageChanged.asObservable();

  constructor(private translate: TranslateService) {}

  initLanguage():void{
    let browserLang = this.translate.getBrowserLang() || 'en';
    if(browserLang !== 'en' && browserLang !== 'fr') browserLang = 'en';
    document.getElementById('htmlTag')?.setAttribute('lang', browserLang);    

    this.changeLanguage(browserLang);
  }

  changeLanguage(lang: string): void {

    this.translate.use(lang).subscribe(() => {

      // Met à jour le signal partagé
      this.currentLang.set(lang);

      // Met à jour la langue de la balise HTML
      document.documentElement.setAttribute('lang', lang);

      // Informe les composants du changement de langue
      this.languageChanged.next(lang);

      // Met à jour le titre de l'onglet
      this.translate.get('app.title').subscribe((title: string) => {
        document.title = title;
      });

    });
  }
}
