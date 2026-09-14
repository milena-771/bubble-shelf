import { Component } from '@angular/core';
import { WritableSignal, signal } from '@angular/core';
import { ComicModel } from '../models/comic-model';
import { READING_PROFILE_COLORS } from '../constants/reading-profiles.const';
import { TranslatePipe } from "@ngx-translate/core";

@Component({
  selector: 'app-comic-details',
  imports: [TranslatePipe],
  templateUrl: './comic-details.html',
  styleUrl: './comic-details.css',
})
export class ComicDetails {

    selectedComic: WritableSignal<ComicModel | null> = signal(null);
    profileColors = READING_PROFILE_COLORS;
}
