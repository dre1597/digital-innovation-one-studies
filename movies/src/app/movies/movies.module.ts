import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { FieldsModule } from '../shared/components/fields/fields.module';
import { MaterialModule } from '../shared/material/material.module';
import { AddMoviesComponent } from './components/add-movies/add-movies.component';
import { ListMoviesComponent } from './components/list-movies/list-movies.component';


@NgModule({
  declarations: [
    AddMoviesComponent,
    ListMoviesComponent
  ],
  imports: [
    CommonModule,
    MaterialModule,
    ReactiveFormsModule,
    FormsModule,
    FieldsModule
  ]
})
export class MoviesModule {
}
