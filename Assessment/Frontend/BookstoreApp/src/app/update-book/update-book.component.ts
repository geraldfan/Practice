import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Book } from '../book';
import { BookService } from '../book.service';

@Component({
  selector: 'app-update-book',
  templateUrl: './update-book.component.html',
  styleUrls: ['./update-book.component.css']
})
export class UpdateBookComponent implements OnInit {
  isbn!: number;
  book: Book = new Book();
  oldBook: Book = new Book();

  constructor(private bookService: BookService,
    private route: ActivatedRoute,
    private router: Router) { }

  ngOnInit(): void {
    this.isbn = this.route.snapshot.params['isbn'];
    // Finds and instantiate the book before updating occurs
    this.bookService.getBookByIsbn(this.isbn).subscribe(data => {
      this.book = data;
      this.oldBook.isbn = this.book.isbn;
      this.oldBook.title = this.book.title;
      this.oldBook.authors = this.book.authors;
      this.oldBook.year = this.book.year;
      this.oldBook.price = this.book.price;
    }, error => console.log(error));
    console.log(this.book.isbn);
  }

  onSubmit() {
    // If the authors column has been changed, convert it from string to array
    if (this.oldBook.authors != this.book.authors) {
      let authorsArray = this.book.authors.split(",");
      this.book.authors = authorsArray;
    }

    this.bookService.updateBook(this.isbn, this.book).subscribe(data => {
      this.goToBookList();
    }
      , error => console.log(error));
  }

  goToBookList() {
    this.router.navigate(['/books']);
  }

}
