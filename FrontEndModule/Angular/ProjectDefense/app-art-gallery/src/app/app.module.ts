import { APP_INITIALIZER, NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import {PagesModule} from './feature/pages/pages.module';
import { AuthModule } from './auth/auth.module';
import { HttpClientModule } from '@angular/common/http';
import { HeaderComponent } from './core/header/header.component';
import { FooterComponent } from './core/footer/footer.component';
import { CoreModule } from './core/core.module';
import { ArtModule } from './feature/art/art.module';
import { AuthService } from './core/auth.service';
import { StoreModule } from '@ngrx/store';
import { IRootState, currentUserReducer } from './+store';

@NgModule({
  declarations: [
    AppComponent,
  ],
  imports: [
    BrowserModule,
    PagesModule,
    ArtModule,
    AppRoutingModule,
    HttpClientModule,
    CoreModule,
    StoreModule.forRoot<IRootState>({
      currentUser: currentUserReducer,
  })
  ],
  providers: [
    {
      provide: APP_INITIALIZER,
      useFactory: (authService: AuthService) => {
        return () => authService.authenticate$();
      },
      deps: [AuthService],
      multi: true
    }
  ],
  bootstrap: [AppComponent, HeaderComponent, FooterComponent]
})
export class AppModule { }
