import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { WelcomeComponent } from './welcome/welcome.component';
import { RoutingModule } from '../auth/routing/routing.module';



@NgModule({
  declarations: [
    WelcomeComponent
  ],
  imports: [
    CommonModule,
    RoutingModule
  ],
  exports:[WelcomeComponent]
})
export class SharedModule { }
