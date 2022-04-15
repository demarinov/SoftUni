import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ArtListItemComponent } from './art-list-item/art-list-item.component';
import { ArtListComponent } from './art-list/art-list.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { ArtworkPageComponent } from './artwork-page/artwork-page.component';
import { ArtRoutingModule } from './art-routing/art-routing.module';
import { ArtNewPageComponent } from './art-new-page/art-new-page.component';
import {MatProgressSpinnerModule} from '@angular/material/progress-spinner';
import { ArtDetailPageComponent } from './art-detail-page/art-detail-page.component';
import { ArtistCatalogPageComponent } from './artist-catalog-page/artist-catalog-page.component';


@NgModule({
  declarations: [
    ArtListItemComponent,
    ArtListComponent,
    ArtworkPageComponent,
    ArtNewPageComponent,
    ArtDetailPageComponent,
    ArtistCatalogPageComponent
  ],
  imports: [
    CommonModule,
    ArtRoutingModule,
    FormsModule,
    MatProgressSpinnerModule,
    ReactiveFormsModule
  ],
  exports: [
    ArtListComponent,
    ArtListItemComponent,
    ArtworkPageComponent,
    ArtDetailPageComponent,
    ArtistCatalogPageComponent
  ]
})
export class ArtModule { }
