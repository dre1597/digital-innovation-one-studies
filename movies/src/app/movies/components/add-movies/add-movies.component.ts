import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { AlertComponent } from '../../../shared/components/alert/alert.component';
import { Alert } from '../../../shared/models/alert';

import { Movie } from '../../../shared/models/movie';
import { MoviesService } from '../../movies.service';

@Component({
  selector: 'app-add-movies',
  templateUrl: './add-movies.component.html',
  styleUrls: []
})
export class AddMoviesComponent implements OnInit {

  addMoviesForm: FormGroup;
  genres: Array<string>;

  constructor(
    public dialog: MatDialog,
    private formBuilder: FormBuilder,
    private movieService: MoviesService,
    private router: Router
  ) {
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

  submitForm(): void {
    this.addMoviesForm.markAsTouched();
    if (this.addMoviesForm.invalid) {
      return;
    }

    const movie = this.addMoviesForm.getRawValue() as Movie;

    this.store(movie);
  }

  resetForm(): void {
    this.addMoviesForm.reset();
  }

  private store(movie: Movie): void {
    this.movieService.store(movie).subscribe({
      next: (): void => {
        const config = {
          data: {
            btnSucesso: 'Go to the list',
            btnCancelar: 'Add a new movie',
            corBtnCancelar: 'primary',
            possuirBtnFechar: true
          } as Alert
        };

        const dialogRef = this.dialog.open(AlertComponent, config);

        dialogRef.afterClosed().subscribe((chooseToNavigate: boolean) => {
          if (chooseToNavigate) {
            this.router.navigateByUrl('movies');
          } else {
            this.resetForm();
          }
        });
      },
      error: (): void => {
        const config = {
          data: {
            title: 'Error!',
            description: 'Error',
            successBtnColor: 'warn',
            successBtnLabel: 'Close'
          } as Alert
        };
        this.dialog.open(AlertComponent, config);
      }
    });
  }

}
