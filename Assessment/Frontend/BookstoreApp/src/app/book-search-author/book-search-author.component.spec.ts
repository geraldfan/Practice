import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BookSearchAuthorComponent } from './book-search-author.component';

describe('BookSearchAuthorComponent', () => {
  let component: BookSearchAuthorComponent;
  let fixture: ComponentFixture<BookSearchAuthorComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ BookSearchAuthorComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(BookSearchAuthorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
