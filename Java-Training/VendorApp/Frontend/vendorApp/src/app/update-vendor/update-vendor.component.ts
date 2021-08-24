import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Vendor } from '../vendor';
import { VendorService } from '../vendor.service';

@Component({
  selector: 'app-update-vendor',
  templateUrl: './update-vendor.component.html',
  styleUrls: ['./update-vendor.component.css']
})
export class UpdateVendorComponent implements OnInit {
  vendorId!: number;
  vendor: Vendor = new Vendor();
  constructor(private vendorService: VendorService,
    private route: ActivatedRoute,
    private router: Router) { }

  ngOnInit(): void {
    this.vendorId = this.route.snapshot.params['id'];

    this.vendorService.getVendorById(this.vendorId).subscribe(data => {
      this.vendor = data;
    }, error => console.log(error));
  }

  onSubmit(){
    this.vendorService.updateVendor(this.vendorId, this.vendor).subscribe( data =>{
      this.goToVendorList();
    }
    , error => console.log(error));
  }

  goToVendorList(){
    this.router.navigate(['/vendors']);
  }

}
