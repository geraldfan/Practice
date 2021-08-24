import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http'
import { Observable } from 'rxjs';
import { Item } from './item';
@Injectable({
  providedIn: 'root'
})
export class ItemService {
  private baseURL = "http://localhost:8080/item";

  constructor(private httpClient: HttpClient) { }

  getItemList(): Observable<Item[]> {
    return this.httpClient.get<Item[]>(`${this.baseURL}/fetch`);
  }

  getItemListByVendorId(vendorId: number): Observable<Item[]> {
    return this.httpClient.get<Item[]>(`${this.baseURL}/fetch/${vendorId}`);
  }

  createItem(item: Item): Observable<Object> {
    return this.httpClient.post(`${this.baseURL}/insert`, item);
  }

  getItemById(id: number): Observable<Item> {
    return this.httpClient.get<Item>(`${this.baseURL}/find/${id}`);
  }

  deleteItem(id: number): Observable<Object> {
    return this.httpClient.delete(`${this.baseURL}/delete/${id}`);
  }
}
