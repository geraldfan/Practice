import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Book } from '../book';
import { BookService } from '../book.service';

@Component({
  selector: 'app-book-search-author',
  templateUrl: './book-search-author.component.html',
  styleUrls: ['./book-search-author.component.css']
})
export class BookSearchAuthorComponent implements OnInit {
  author!: string;
  books!: Book[];
  hasBooks: boolean = false;

  constructor(private route: ActivatedRoute, private bookService: BookService, private router: Router) { }

  ngOnInit(): void {
    this.author = this.route.snapshot.params['author'];

    this.bookService.getBooksByAuthor(this.author).subscribe(data => {
      this.books = data;
      // Check if there are books in the array
      if (Object.keys(data).length != 0) {
        this.hasBooks = true;
      }

    });

  }

  deleteBook(isbn: number) {
    this.bookService.deleteBook(isbn).subscribe(data => {
      console.log(data);
      this.goToBookList();
    })
  }

  goToBookList() {
    this.router.navigate(['/books']);
  }

  updateBook(isbn: number){
    this.router.navigate(['update-book', isbn]);
  }
}
