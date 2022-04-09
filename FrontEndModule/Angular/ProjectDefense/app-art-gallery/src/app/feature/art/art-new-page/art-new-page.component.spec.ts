import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ArtNewPageComponent } from './art-new-page.component';

describe('ArtNewPageComponent', () => {
  let component: ArtNewPageComponent;
  let fixture: ComponentFixture<ArtNewPageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ArtNewPageComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ArtNewPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
