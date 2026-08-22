import { Component, inject } from '@angular/core';
import { RouterLink } from '@angular/router';
import { FooterService } from '../services/footer-service';

@Component({
  selector: 'app-footer',
  imports: [RouterLink],
  templateUrl: './footer.html',
  styleUrl: './footer.css',
})
export class Footer {

  footer = inject(FooterService);
  
}
