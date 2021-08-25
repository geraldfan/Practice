import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Book } from './book';

@Injectable({
  providedIn: 'root'
})
export class BookService {
  private baseURL = "http://localhost:8080/book";


  constructor(private httpClient: HttpClient) { }

  getBookList(): Observable<Book[]> {
    return this.httpClient.get<Book[]>(`${this.baseURL}/fetch`);
  }

  createBook(book: Book): Observable<Object> {
    return this.httpClient.post(`${this.baseURL}/insert`, book);
  }

  getBookByIsbn(isbn: number): Observable<Book>{
    return this.httpClient.get<Book>(`${this.baseURL}/find/${isbn}`);
  }

  getBooksByTitle(title: string): Observable<Book[]>{
    return this.httpClient.get<Book[]>(`${this.baseURL}/search/title/${title}`);
  }

  getBooksByAuthor(author: string): Observable<Book[]>{
    return this.httpClient.get<Book[]>(`${this.baseURL}/search/author/${author}`);
  }

  deleteBook(isbn: number): Observable<Object> {
    return this.httpClient.delete(`${this.baseURL}/delete/${isbn}`);
  }

  updateBook(isbn: number, book: Book): Observable<Object>{
    return this.httpClient.put(`${this.baseURL}/update/${isbn}`, book);
  }
}
