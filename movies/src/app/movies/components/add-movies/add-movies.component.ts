import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';

@Component({
  selector: 'app-add-movies',
  templateUrl: './add-movies.component.html',
  styleUrls: []
})
export class AddMoviesComponent implements OnInit {

  options: FormGroup;

  constructor(private formBuilder: FormBuilder) {
  }

  ngOnInit(): void {
    this.options = this.formBuilder.group({
      hideRequired: false,
      floatLabel: 'auto',
    });
  }

}
