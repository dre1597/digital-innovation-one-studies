import { Component, Input } from '@angular/core';
import { AbstractControl, FormGroup } from '@angular/forms';

import { FieldsValidationService } from '../../fields-validation.service';

@Component({
  selector: 'app-input-number',
  templateUrl: './input-number.component.html',
  styleUrls: []
})
export class InputNumberComponent {

  @Input() placeholder: string;
  @Input() formGroup: FormGroup;
  @Input() controlName: string;
  @Input() minimum = 0;
  @Input() maximum = 10;
  @Input() step = 1;

  constructor(public validation: FieldsValidationService) {
  }

  get formControl(): AbstractControl {
    return this.formGroup.controls[this.controlName];
  }
}
