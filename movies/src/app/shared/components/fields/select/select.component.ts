import { Component, Input } from '@angular/core';
import { AbstractControl, FormGroup } from '@angular/forms';

import { FieldsValidationService } from '../../fields-validation.service';

@Component({
  selector: 'app-select',
  templateUrl: './select.component.html',
  styleUrls: []
})
export class SelectComponent {

  @Input() placeholder: string;
  @Input() formGroup: FormGroup;
  @Input() controlName: string;
  @Input() options: Array<string>;

  constructor(public validation: FieldsValidationService) {
  }

  get formControl(): AbstractControl {
    return this.formGroup.controls[this.controlName];
  }

}
