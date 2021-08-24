import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Vendor } from '../vendor';
import { VendorService } from '../vendor.service';

@Component({
  selector: 'app-create-vendor',
  templateUrl: './create-vendor.component.html',
  styleUrls: ['./create-vendor.component.css']
})
export class CreateVendorComponent implements OnInit {
  vendor: Vendor = new Vendor();

  constructor(private vendorService: VendorService,
    private router: Router) { }

  ngOnInit(): void {
  }

  saveVendor(){
    this.vendorService.createVendor(this.vendor).subscribe( data =>{
      console.log(data);
      this.goToVendorList();
    },
      (    error: any) => console.log(error));
  }

  goToVendorList(){
    this.router.navigate(['/vendors']);
  }

  onSubmit(){
    console.log(this.vendor);
    this.saveVendor();
  }
}
