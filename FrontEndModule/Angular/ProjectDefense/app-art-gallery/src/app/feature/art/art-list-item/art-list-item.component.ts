import { Component, Input, OnInit } from '@angular/core';
import { ArtService } from 'src/app/core/art.service';
import { IArt } from 'src/app/interfaces/art';

@Component({
  selector: 'app-art-list-item',
  templateUrl: './art-list-item.component.html',
  styleUrls: ['./art-list-item.component.css']
})
export class ArtListItemComponent implements OnInit {

  @Input('art') art: IArt;

  isLoggedIn: boolean = false;
  canSubscribe: boolean = false;

  constructor(private artService: ArtService) { }

  ngOnInit(): void {
  
  }

  handleSubscriber() {

  }

}
