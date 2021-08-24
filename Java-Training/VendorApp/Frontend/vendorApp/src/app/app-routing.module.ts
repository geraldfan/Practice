import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { CreateItemComponent } from './create-item/create-item.component';
import { CreateVendorComponent } from './create-vendor/create-vendor.component';
import { ItemDetailsComponent } from './item-details/item-details.component';
import { ItemListComponent } from './item-list/item-list.component';
import { VendorDetailsComponent } from './vendor-details/vendor-details.component';
import { VendorListComponent } from './vendor-list/vendor-list.component';


const routes: Routes = [
  {path: 'vendors', component: VendorListComponent},
  {path: 'create-vendor', component: CreateVendorComponent},
  {path: '', redirectTo: 'vendors', pathMatch: 'full'},
  {path: 'vendor-details/:id', component: VendorDetailsComponent},
  {path: 'items', component: ItemListComponent},
  {path: 'create-item', component: CreateItemComponent},
  {path: 'item-details/:id', component: ItemDetailsComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],                                                                                                                                                                                                                                                                                                          
  exports: [RouterModule]
})
export class AppRoutingModule { }