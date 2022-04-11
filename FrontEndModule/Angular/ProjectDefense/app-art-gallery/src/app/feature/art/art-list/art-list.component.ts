import { Component, OnInit } from '@angular/core';
import { ArtService } from 'src/app/core/art.service';
import { IArt } from 'src/app/interfaces/art';

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
        this.errorMessage = err.message;
      }
    })
  }

}
