import { Component, inject } from '@angular/core';
import { RouterLink } from '@angular/router';
import { FooterService } from '../services/footer-service';
import {TranslatePipe } from "@ngx-translate/core";

@Component({
  selector: 'app-footer',
  imports: [RouterLink, TranslatePipe],
  templateUrl: './footer.html',
  styleUrl: './footer.css',
})
export class Footer {

  footer = inject(FooterService);
  
}
