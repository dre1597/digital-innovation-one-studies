import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-add-movies',
  templateUrl: './add-movies.component.html',
  styleUrls: []
})
export class AddMoviesComponent implements OnInit {

  addMoviesForm: FormGroup;
  genres: Array<string>;

  constructor(private formBuilder: FormBuilder) {
  }

  ngOnInit(): void {
    this.addMoviesForm = this.formBuilder.group({
      title: ['', [Validators.required, Validators.minLength(2), Validators.maxLength(256)]],
      imageUrl: ['', Validators.minLength(10)],
      releaseDate: ['', Validators.required],
      description: [''],
      score: [0, [Validators.required, Validators.minLength(0), Validators.maxLength(10)]],
      IMDBurl: ['', [Validators.minLength(10)]],
      genre: ['', Validators.required]
    });

    this.genres = ['Action', 'Romance', 'Adventure', 'Horror', 'Sci-fi', 'Comedy', 'Drama'];

  }

  add(): void {
    this.addMoviesForm.markAsTouched();
    if (this.addMoviesForm.invalid) {
      return;
    }

    alert('Success!\n\n' + JSON.stringify(this.addMoviesForm.value, null, 2));
  }

  resetForm(): void {
    this.addMoviesForm.reset();
  }

}
