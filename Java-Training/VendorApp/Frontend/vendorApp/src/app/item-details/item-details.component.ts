import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Item } from '../item';
import { ItemService } from '../item.service';

@Component({
  selector: 'app-item-details',
  templateUrl: './item-details.component.html',
  styleUrls: ['./item-details.component.css']
})
export class ItemDetailsComponent implements OnInit {
  itemId!: number;
  item!: Item;
  constructor(private route: ActivatedRoute, private itemService: ItemService) { }

  ngOnInit(): void {
    this.itemId = this.route.snapshot.params['id'];

    this.item = new Item();
    this.itemService.getItemById(this.itemId).subscribe(data => {
      this.item = data;
    });
  }

}
