import { Component, inject } from '@angular/core';
import { RouterLink } from '@angular/router';
import {TranslatePipe } from "@ngx-translate/core";
import { HeaderService } from '../../services/header-service';
import { FooterService } from '../../services/footer-service';

@Component({
  selector: 'app-not-found',
  imports: [RouterLink, TranslatePipe],
  templateUrl: './not-found.html',
  styleUrl: './not-found.css',
})
export class NotFound {

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
