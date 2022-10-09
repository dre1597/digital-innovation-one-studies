import { Component, ViewChild } from '@angular/core';
import { MatSidenav } from '@angular/material/sidenav';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.sass']
})
export class HomeComponent {
  @ViewChild('sidenav', {static: false}) sidenav: MatSidenav;

  openSideNav(): void {
    this.sidenav.open();
  }

  closeSideNav(): void {
    this.sidenav.close();
  }

}
