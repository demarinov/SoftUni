import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ArtListItemComponent } from './art-list-item/art-list-item.component';
import { ArtListComponent } from './art-list/art-list.component';
import { FormsModule } from '@angular/forms';
import { SharedModule } from 'src/app/shared/shared.module';
import { ArtworkPageComponent } from './artwork-page/artwork-page.component';
import { ArtRoutingModule } from './art-routing/art-routing.module';
import { ArtNewPageComponent } from './art-new-page/art-new-page.component';
import {MatProgressSpinnerModule} from '@angular/material/progress-spinner';
import { ArtDetailPageComponent } from './art-detail-page/art-detail-page.component';


@NgModule({
  declarations: [
    ArtListItemComponent,
    ArtListComponent,
    ArtworkPageComponent,
    ArtNewPageComponent,
    ArtDetailPageComponent
  ],
  imports: [
    CommonModule,
    ArtRoutingModule,
    SharedModule,
    FormsModule,
    MatProgressSpinnerModule,
  ],
  exports: [
    ArtListComponent,
    ArtListItemComponent,
    ArtworkPageComponent,
    ArtDetailPageComponent
  ]
})
export class ArtModule { }
