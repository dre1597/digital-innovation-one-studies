import { ThemePalette } from '@angular/material/core';

export interface Alert {
  title?: string;
  description?: string;
  successBtnLabel?: string;
  cancelBtnLabel?: string;
  successBtnColor?: ThemePalette;
  cancelBtnColor?: ThemePalette;
  hasCLoseBtn?: boolean;
}
