import { Injectable, signal } from '@angular/core';
import { HeaderConfig } from '../models/header-config';

@Injectable({
  providedIn: 'root',
})
export class HeaderService {

  config = signal<HeaderConfig>({
    showBubbleLogo: true,
    showRestartBtn: false,
    showBorderBottom: true,
    showLanguageSelector: true
  });

  setConfig(config: Partial<HeaderConfig>) {
    this.config.update(current => ({
      ...current,
      ...config
    }));
  }

  getConfig() {
    return this.config();
  }
}