import { Component, inject} from '@angular/core';
import { HeaderService } from '../services/header-service';
import { FooterService } from '../services/footer-service';
import { RouterLink } from '@angular/router';
import {TranslatePipe } from "@ngx-translate/core";

@Component({
  selector: 'app-terms-of-use',
  imports: [RouterLink, TranslatePipe],
  templateUrl: './terms-of-use.html',
  styleUrl: './terms-of-use.css',
})
export class TermsOfUse {

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
