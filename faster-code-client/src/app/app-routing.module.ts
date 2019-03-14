import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { FasterComponent } from './faster/faster.component';
import { LibraryListComponent } from './library-list/library-list.component';

const routes: Routes = [
  { path: '', redirectTo: 'library', pathMatch: 'full' },
  { path: 'library', component: LibraryListComponent },
  { path: 'faster', component: FasterComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
