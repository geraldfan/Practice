import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Vendor } from '../vendor';
import { VendorService } from '../vendor.service';

@Component({
  selector: 'app-vendor-list',
  templateUrl: './vendor-list.component.html',
  styleUrls: ['./vendor-list.component.css']
})
export class VendorListComponent implements OnInit {
  vendors!: Vendor[];

  constructor(private vendorService: VendorService, private router: Router) { }

  ngOnInit(): void {
    this.getVendors();
  }

  private getVendors() {
    this.vendorService.getVendorList().subscribe(data => {
      this.vendors = data;
    });
  }

  vendorDetails(id: number){
    this.router.navigate(['vendor-details', id]);
  }

  deleteVendor(id: number){
    this.vendorService.deleteVendor(id).subscribe( data => {
      console.log(data);
      this.getVendors();
    })
  }

}
