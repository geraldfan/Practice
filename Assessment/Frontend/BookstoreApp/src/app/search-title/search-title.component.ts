import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Book } from '../book';
import { BookService } from '../book.service';

@Component({
  selector: 'app-search-title',
  templateUrl: './search-title.component.html',
  styleUrls: ['./search-title.component.css']
})
export class SearchTitleComponent implements OnInit {
  book: Book = new Book();
  constructor(private bookService: BookService,
    private router: Router) { }

  ngOnInit(): void {
  }

  onSubmit(){
    console.log(this.book);
    this.goToBook();
  }
  
  goToBook() {
    this.router.navigate([`/book-search/${this.book.title}`]);
  }

  

  
}
