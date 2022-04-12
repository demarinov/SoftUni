
import { RouterModule, Routes } from "@angular/router";
import { AuthGuard } from "src/app/core/guards/auth.guard";
import { ArtDetailPageComponent } from "../art-detail-page/art-detail-page.component";
import { ArtNewPageComponent } from "../art-new-page/art-new-page.component";
import { ArtworkPageComponent } from "../artwork-page/artwork-page.component";


const routes:Routes = [
    {
        path: "artworks",
        pathMatch: "full",
        component: ArtworkPageComponent
    },
    {
        path: "artworks/new",
        canActivate: [AuthGuard],
        component: ArtNewPageComponent
    },
    {
        path: "artworks/:artId",
        component: ArtDetailPageComponent
    },
    
]

export const ArtRoutingModule = RouterModule.forChild(routes);
