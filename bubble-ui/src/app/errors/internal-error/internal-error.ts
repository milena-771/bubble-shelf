import { Component, inject } from '@angular/core';
import { RouterLink } from '@angular/router';
import {TranslatePipe } from "@ngx-translate/core";
import { HeaderService } from '../../services/header-service';
import { FooterService } from '../../services/footer-service';

@Component({
  selector: 'app-internal-error',
  imports: [RouterLink, TranslatePipe],
  templateUrl: './internal-error.html',
  styleUrl: './internal-error.css',
})
export class InternalError {

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
