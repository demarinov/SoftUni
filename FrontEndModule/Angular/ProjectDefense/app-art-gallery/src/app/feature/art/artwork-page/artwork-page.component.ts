import { Component, OnInit } from '@angular/core';
import { UserService } from 'src/app/core/user.service';

@Component({
  selector: 'app-artwork-page',
  templateUrl: './artwork-page.component.html',
  styleUrls: ['./artwork-page.component.css']
})
export class ArtworkPageComponent implements OnInit {

  isLoggedIn: boolean = this.userService.isUserLogged;
  constructor(private userService: UserService) { }

  ngOnInit(): void {
  }

}
