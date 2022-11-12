import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {HomecomponentComponent} from "./Component/homecomponent/homecomponent.component";

const routes: Routes = [
  { path: '', redirectTo: '/home', pathMatch: 'full' },
  { path: 'home', component: HomecomponentComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
