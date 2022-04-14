import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ArtService } from 'src/app/core/art.service';
import { UserService } from 'src/app/core/user.service';
import { IUser } from 'src/app/interfaces';
import { IArt } from 'src/app/interfaces/art';
import { SERVICE_UNAVAILABLE_ERROR } from 'src/app/utils';

@Component({
  selector: 'app-artist-catalog-page',
  templateUrl: './artist-catalog-page.component.html',
  styleUrls: ['./artist-catalog-page.component.css']
})
export class ArtistCatalogPageComponent implements OnInit {


  artList: IArt[];
  errorMessage:string;
  currentUser!: IUser;

  constructor(private artService: ArtService, 
    private userService: UserService,
    private router: Router) { }

  ngOnInit(): void {
    this.userService.getProfile$().subscribe({
      next: user => {
        this.currentUser = user;
        console.log(user);

        this.handleArtistCatalogEvent();
      },
      error: error => {
        console.log(error);
        this.errorMessage = SERVICE_UNAVAILABLE_ERROR;
        this.router.navigate(['/login']);
      }
    })
  }

  handleArtistCatalogEvent() {
    const userId = this.currentUser._id;
    this.artService.loadArtsByUserId(userId).subscribe({
      next: (arts) => {
      console.log(arts);
      this.artList= arts;
      },
      error: (err) => {
        console.error(err);
        this.errorMessage = SERVICE_UNAVAILABLE_ERROR;
      }
    })
  }

}
