import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Item } from '../item';
import { ItemService } from '../item.service';
import { Vendor } from '../vendor';
import { VendorService } from '../vendor.service';

@Component({
  selector: 'app-vendor-details',
  templateUrl: './vendor-details.component.html',
  styleUrls: ['./vendor-details.component.css']
})
export class VendorDetailsComponent implements OnInit {
  vendorId!: number;
  vendor!: Vendor;
  items!: Item[];
  constructor(private route: ActivatedRoute, private vendorService: VendorService, private itemService: ItemService, private router: Router) { }

  ngOnInit(): void {
    this.vendorId = this.route.snapshot.params['id'];

    this.vendor = new Vendor();
    this.vendorService.getVendorById(this.vendorId).subscribe(data => {
      this.vendor = data;
    });

    this.getItems(this.vendorId);
  }

  private getItems(vendorId: number) {
    this.itemService.getItemListByVendorId(this.vendorId).subscribe(data => {
      this.items = data;
    });
  }

  itemDetails(id: number){
    this.router.navigate(['item-details', id]);
  }

  deleteItem(id: number){
    this.itemService.deleteItem(id).subscribe( data => {
      console.log(data);
      this.getItems(this.vendorId);
    })
  }
}
