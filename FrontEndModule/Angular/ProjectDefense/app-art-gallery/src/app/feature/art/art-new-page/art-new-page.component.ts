import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { ArtService } from 'src/app/core/art.service';

@Component({
  selector: 'app-art-new-page',
  templateUrl: './art-new-page.component.html',
  styleUrls: ['./art-new-page.component.css']
})
export class ArtNewPageComponent implements OnInit {

  constructor(private artService: ArtService, private router: Router) { }
  ngOnInit(): void {
  }

  submitNewArt(newArtForm: NgForm): void {
    console.log(newArtForm.value);
    this.artService.addArt$(newArtForm.value).subscribe({

      next: theme => {
        console.log(theme);
        this.router.navigate(['/artworks']);
      },
      error: error => {
        console.log(error);
      }
    })
  }

  navigateToHome() {
    this.router.navigate(['/home']);
  }

}
