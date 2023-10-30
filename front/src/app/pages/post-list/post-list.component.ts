import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-post-list',
  templateUrl: './post-list.component.html',
  styleUrls: ['./post-list.component.scss']
})
export class PostListComponent implements OnInit {

  arrowBool: boolean = true;

  constructor() { }

  ngOnInit(): void {
  }

  toggleArrow() {
    this.arrowBool = !this.arrowBool;
    console.log(this.arrowBool);
  }

}
