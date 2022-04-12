import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { ArtService } from 'src/app/core/art.service';
import { IArt } from 'src/app/interfaces/art';

@Component({
  selector: 'app-art-detail-page',
  templateUrl: './art-detail-page.component.html',
  styleUrls: ['./art-detail-page.component.css']
})
export class ArtDetailPageComponent implements OnInit {


  art: IArt;

  constructor(private artService: ArtService, private activatedRoute: ActivatedRoute) { }

  ngOnInit(): void {

    const artId = this.activatedRoute.snapshot.params["artId"];
    this.artService.loadArtById(artId).subscribe((art) => {
      this.art = art;
      console.log(this.art);
    });
  }

}
