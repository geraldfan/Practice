import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Item } from '../item';
import { ItemService } from '../item.service';

@Component({
  selector: 'app-create-item',
  templateUrl: './create-item.component.html',
  styleUrls: ['./create-item.component.css']
})
export class CreateItemComponent implements OnInit {
  item: Item = new Item();
  constructor(private itemService: ItemService,
    private router: Router) { }

  ngOnInit(): void {
  }

  saveItem(){
    this.itemService.createItem(this.item).subscribe( data =>{
      console.log(data);
      this.goToItemList();
    },
      (    error: any) => console.log(error));
  }

  goToItemList(){
    this.router.navigate(['/items']);
  }

  onSubmit(){
    console.log(this.item);
    this.saveItem();
  }
}
