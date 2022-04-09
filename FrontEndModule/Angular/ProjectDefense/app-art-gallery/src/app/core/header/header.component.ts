import { Component, OnInit } from '@angular/core';
import { IUser } from 'src/app/interfaces';
import { UserService } from '../user.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  constructor(public userService: UserService) { }

  get currentUser() : IUser{
    return this.userService.currentUser;
  }

  ngOnInit(): void {
  }

  logoutHandler() {
    this.userService.logout();
  }
}
