import { Component, inject, signal, WritableSignal } from '@angular/core';
import {FormsModule} from "@angular/forms";
import { RouterLink } from '@angular/router';
import { HeaderService } from '../services/header-service';
import {TranslatePipe, TranslateService } from "@ngx-translate/core";

@Component({
  selector: 'app-header',
  imports: [RouterLink, TranslatePipe, FormsModule],
  templateUrl: './header.html',
  styleUrl: './header.css',
})
export class Header {

  currentLang: WritableSignal<string> = signal('en');
  readonly selectedLanguage = signal<string | null>(null);

  header = inject(HeaderService);
  translate = inject(TranslateService);

  changeLanguage(event: string) {
    const lang = event;
    this.translate.use(lang);
    this.currentLang.set(lang);
  }
}
