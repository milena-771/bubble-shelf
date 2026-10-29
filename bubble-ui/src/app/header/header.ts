import { Component, inject, signal, WritableSignal } from '@angular/core';
import {FormsModule} from "@angular/forms";
import { RouterLink } from '@angular/router';
import { HeaderService } from '../services/header-service';
import { TranslatePipe } from "@ngx-translate/core";
import { LanguageService } from '../services/language-service';

@Component({
  selector: 'app-header',
  imports: [RouterLink, TranslatePipe, FormsModule],
  templateUrl: './header.html',
  styleUrl: './header.css',
})
export class Header {

  header = inject(HeaderService);
  languageService = inject(LanguageService);

  ngOnInit() {
    this.languageService.initLanguage();
  }

  changeLanguage(lang: string) {
    this.languageService.changeLanguage(lang);
  }
}
