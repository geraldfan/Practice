import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { BookListComponent } from './book-list/book-list.component';
import { BookSearchAuthorComponent } from './book-search-author/book-search-author.component';
import { BookSearchComponent } from './book-search/book-search.component';
import { CreateBookComponent } from './create-book/create-book.component';
import { SearchAuthorComponent } from './search-author/search-author.component';
import { SearchTitleComponent } from './search-title/search-title.component';
import { UpdateBookComponent } from './update-book/update-book.component';

const routes: Routes = [
  {path: 'books', component: BookListComponent},
  {path: '', redirectTo: 'books', pathMatch: 'full'},
  {path: 'create-book', component: CreateBookComponent},
  {path: 'search-title', component: SearchTitleComponent},
  {path: 'book-search/:title', component: BookSearchComponent},
  {path: 'search-author', component: SearchAuthorComponent},
  {path: 'book-search-author/:author', component: BookSearchAuthorComponent},
  {path: 'update-book/:isbn', component: UpdateBookComponent},

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
