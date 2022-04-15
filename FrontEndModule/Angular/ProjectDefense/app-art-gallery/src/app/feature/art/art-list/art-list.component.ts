import { Component, OnInit } from '@angular/core';
import { ArtService } from 'src/app/core/art.service';
import { IArt } from 'src/app/interfaces/art';
import { SERVICE_ERROR, SERVICE_UNAVAILABLE_ERROR } from 'src/app/utils';

@Component({
  selector: 'app-art-list',
  templateUrl: './art-list.component.html',
  styleUrls: ['./art-list.component.css']
})
export class ArtListComponent implements OnInit {

  artList: IArt[];
  errorMessage:string;

  constructor(private artService: ArtService) { }

  ngOnInit(): void {
    this.artService.loadArtList().subscribe({
      next: (arts) => {
      console.log(arts);
      this.artList= arts;
      },
      error: (err) => {
        console.error(err);
        this.errorMessage = SERVICE_ERROR;
      }
    })
  }

}
