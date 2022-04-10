import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './feature/pages/home/home.component';
import { PageNotFoundComponent } from './feature/pages/page-not-found/page-not-found.component';

const routes: Routes = [{
  path:"",
  pathMatch: "full",
  redirectTo: "home"
},
{
  path:"home",
  pathMatch: "full",
  component: HomeComponent
},
{
  path: "user",
  loadChildren: () => import("../app/auth/auth.module").then(m => m.AuthModule)
},
{
  path:'**',
  component: PageNotFoundComponent
}];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
