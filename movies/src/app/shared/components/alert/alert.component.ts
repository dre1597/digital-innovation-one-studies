import { Component, Inject, OnInit } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';

import { Alert } from '../../models/alert';

@Component({
  selector: 'app-alert',
  templateUrl: './alert.component.html',
  styleUrls: []
})
export class AlertComponent implements OnInit {

  alert = {
    title: 'Sucesso!',
    description: 'Data saved!',
    successBtnLabel: 'OK',
    cancelBtnLabel: 'Cancel',
    successBtnColor: 'accent',
    cancelBtnColor: 'warn',
    hasCLoseBtn: false
  } as Alert;

  constructor(
    public dialogRef: MatDialogRef<AlertComponent>,
    @Inject(MAT_DIALOG_DATA) public data: Alert
  ) {
  }

  ngOnInit(): void {
    if (this.data) {
      this.alert.title = this.data.title || this.alert.title;
      this.alert.description = this.data.description || this.alert.description;
      this.alert.successBtnLabel = this.data.successBtnLabel || this.alert.successBtnLabel;
      this.alert.cancelBtnLabel = this.data.cancelBtnLabel || this.alert.cancelBtnLabel;
      this.alert.successBtnColor = this.data.successBtnColor || this.alert.successBtnColor;
      this.alert.cancelBtnColor = this.data.cancelBtnColor || this.alert.cancelBtnColor;
      this.alert.hasCLoseBtn = this.data.hasCLoseBtn || this.alert.hasCLoseBtn;
    }
  }

}
