import { Component, inject } from '@angular/core';
import { RouterLink } from '@angular/router';
import {TranslatePipe } from "@ngx-translate/core";
import { HeaderService } from '../services/header-service';
import { FooterService } from '../services/footer-service';

@Component({
  selector: 'app-times-up',
  imports: [RouterLink, TranslatePipe],
  templateUrl: './times-up.html',
  styleUrl: './times-up.css',
})
export class TimesUp {

  header = inject(HeaderService);
  footer = inject(FooterService);

  ngOnInit() {
    this.header.setConfig({
      showBorderBottom: true,
      showRestartBtn: false, 
      showLanguageSelector: false
    });
    this.footer.setConfig({
      isResultsPage: false
    });
  }
}
