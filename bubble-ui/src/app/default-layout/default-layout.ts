import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { Header } from '../header/header';
import { Footer } from '../footer/footer';

@Component({
  selector: 'default-layout',
  imports: [RouterOutlet, Header, Footer],
  templateUrl: './default-layout.html',
  styleUrl: './default-layout.css',
})
export class DefaultLayout {}
