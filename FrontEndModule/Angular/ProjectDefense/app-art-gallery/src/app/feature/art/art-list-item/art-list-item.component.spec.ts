import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ArtListItemComponent } from './art-list-item.component';

describe('ArtListItemComponent', () => {
  let component: ArtListItemComponent;
  let fixture: ComponentFixture<ArtListItemComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ArtListItemComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ArtListItemComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
