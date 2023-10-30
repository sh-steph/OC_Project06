import { Component, Input, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent implements OnInit {

  userConnected: boolean = true;
  constructor(
    private router: Router,
  ) { }

  ngOnInit(): void {
    this.userConnected = true;
    console.log(this.userConnected)
  }

  public buttonMDD(): void {
    if (this.userConnected) {
      this.router.navigate(['/register']);
    } else {
      this.router.navigate(['/home']);
    }
  }

}
