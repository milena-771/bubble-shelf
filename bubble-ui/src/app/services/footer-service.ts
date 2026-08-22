import { Injectable, signal} from '@angular/core';
import { FooterConfig } from '../models/footer-config';

@Injectable({
  providedIn: 'root',
})
export class FooterService {
  
  config = signal<FooterConfig>({
    isResultsPage: false,
  });

  setConfig(config: Partial<FooterConfig>) {
    this.config.update(current => ({
      ...current,
      ...config
    }));
  }

  getConfig() {
    return this.config();
  }
}

