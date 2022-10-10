import { Component, Input } from '@angular/core';
import { AbstractControl, FormGroup } from '@angular/forms';

import { FieldsValidationService } from '../../fields-validation.service';

@Component({
  selector: 'app-input-date',
  templateUrl: './input-date.component.html',
  styleUrls: []
})
export class InputDateComponent {

  @Input() placeholder: string;
  @Input() formGroup: FormGroup;
  @Input() controlName: string;

  constructor(public validation: FieldsValidationService) {
  }

  get formControl(): AbstractControl {
    return this.formGroup.controls[this.controlName];
  }

}
