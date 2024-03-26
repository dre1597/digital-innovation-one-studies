import { Routes } from '@angular/router';

export const routes: Routes = [
  {
    path: '',
    loadComponent: () => import('./pages/home/home.component').then(c => c.HomeComponent)
  },
  {
    path: 'content/:id',
    loadComponent: () => import('./pages/content/content.component').then(c => c.ContentComponent)
  },
];
