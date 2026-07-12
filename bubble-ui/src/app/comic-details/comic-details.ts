import { Component } from '@angular/core';
import { WritableSignal, signal } from '@angular/core';
import { ComicModel } from '../models/comic-model';

@Component({
  selector: 'app-comic-details',
  imports: [],
  templateUrl: './comic-details.html',
  styleUrl: './comic-details.css',
})
export class ComicDetails {

    selectedComic: WritableSignal<ComicModel | null> = signal(null);
}
