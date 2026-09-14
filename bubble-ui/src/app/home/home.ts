import { Component, inject} from '@angular/core';
import { RouterLink } from '@angular/router';
import { HeaderService } from '../services/header-service';
import { FooterService } from '../services/footer-service';
import {TranslatePipe } from "@ngx-translate/core";

@Component({
  selector: 'home',
  imports: [RouterLink, TranslatePipe],
  templateUrl: './home.html',
  styleUrl: './home.css',
})
export class Home {

  header = inject(HeaderService);
  footer = inject(FooterService);

  ngOnInit() {
    this.header.setConfig({
      showBorderBottom: true,
      showRestartBtn: false,
      showLanguageSelector: true
    });
    this.footer.setConfig({
      isResultsPage: false
    });
  }

}
