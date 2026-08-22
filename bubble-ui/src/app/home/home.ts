import { Component, inject} from '@angular/core';
import { RouterLink } from '@angular/router';
import { HeaderService } from '../services/header-service';
import { FooterService } from '../services/footer-service';

@Component({
  selector: 'home',
  imports: [RouterLink],
  templateUrl: './home.html',
  styleUrl: './home.css',
})
export class Home {

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
