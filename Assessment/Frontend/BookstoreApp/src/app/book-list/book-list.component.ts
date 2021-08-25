import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Book } from '../book';
import { BookService } from '../book.service';

@Component({
  selector: 'app-book-list',
  templateUrl: './book-list.component.html',
  styleUrls: ['./book-list.component.css']
})
export class BookListComponent implements OnInit {
  books!: Book[];
  
  constructor(private bookService: BookService, private router: Router) { }

  ngOnInit(): void {
    this.getBooks();
  }

  private getBooks() {
    this.bookService.getBookList().subscribe(data => {
      this.books = data;
    });
  }

  deleteBook(isbn: number){
    this.bookService.deleteBook(isbn).subscribe( data => {
      console.log(data);
      this.getBooks();
    })
  }

  updateBook(isbn: number){
    this.router.navigate(['update-book', isbn]);
  }

}
