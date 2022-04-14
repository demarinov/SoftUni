import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ArtistCatalogPageComponent } from './artist-catalog-page.component';

describe('ArtistCatalogPageComponent', () => {
  let component: ArtistCatalogPageComponent;
  let fixture: ComponentFixture<ArtistCatalogPageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ArtistCatalogPageComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ArtistCatalogPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
