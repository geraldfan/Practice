import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http'
import { FormsModule } from '@angular/forms';


import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BookListComponent } from './book-list/book-list.component';
import { CreateBookComponent } from './create-book/create-book.component';
import { SearchTitleComponent } from './search-title/search-title.component';
import { BookSearchComponent } from './book-search/book-search.component';
import { SearchAuthorComponent } from './search-author/search-author.component';
import { BookSearchAuthorComponent } from './book-search-author/book-search-author.component';
import { UpdateBookComponent } from './update-book/update-book.component';

@NgModule({
  declarations: [
    AppComponent,
    BookListComponent,
    CreateBookComponent,
    SearchTitleComponent,
    BookSearchComponent,
    SearchAuthorComponent,
    BookSearchAuthorComponent,
    UpdateBookComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
