import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { ArtService } from 'src/app/core/art.service';
import { AuthService } from 'src/app/core/auth.service';
import { IArt } from 'src/app/interfaces/art';
import { SERVICE_ERROR } from 'src/app/utils';

@Component({
  selector: 'app-art-detail-page',
  templateUrl: './art-detail-page.component.html',
  styleUrls: ['./art-detail-page.component.css']
})
export class ArtDetailPageComponent implements OnInit {

  art: IArt;

  isLoggedIn$: Observable<boolean> = this.authService.isLoggedIn$;
  errorMessage:string;

  constructor(private artService: ArtService, private activatedRoute: ActivatedRoute,
    private authService: AuthService) { }

  ngOnInit(): void {

    const artId = this.activatedRoute.snapshot.params["artId"];
    console.log(artId);
    this.artService.loadArtById(artId).subscribe({
      next: (art) => {
        this.art = art;
        console.log(this.art);
      },
      error: (err) => {
        console.log(err);
        this.errorMessage = SERVICE_ERROR;
      }
    });
  }

}
