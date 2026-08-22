import { Component, inject} from '@angular/core';
import { HeaderService } from '../services/header-service';
import { FooterService } from '../services/footer-service';

@Component({
  selector: 'app-terms-of-use',
  imports: [],
  templateUrl: './terms-of-use.html',
  styleUrl: './terms-of-use.css',
})
export class TermsOfUse {

  header = inject(HeaderService);
  footer = inject(FooterService);

    ngOnInit() {
    this.header.setConfig({
      showBorderBottom: true
    });
    this.footer.setConfig({
      isResultsPage: false
    });
  }
}
