import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Book } from '../book';

@Component({
  selector: 'app-search-author',
  templateUrl: './search-author.component.html',
  styleUrls: ['./search-author.component.css']
})
export class SearchAuthorComponent implements OnInit {
  book: Book = new Book();

  constructor(private router: Router) { }

  ngOnInit(): void {
  }

  onSubmit(){
    console.log(this.book);
    this.goToBook();
  }
  
  goToBook() {
    this.router.navigate([`/book-search-author/${this.book.authors}`]);
  }
}
