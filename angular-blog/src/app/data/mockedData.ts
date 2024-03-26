export const mockedData: Item[] = [
  {
    id: '1',
    title: 'Angular Blog',
    description: 'Angular Blog Description',
    photoCover: 'https://angular.io/assets/images/logos/angular/angular.svg'
  },
  {
    id: '2',
    title: 'Angular Tutorial',
    description: 'Angular Tutorial Description',
    photoCover: 'https://angular.io/assets/images/logos/angular/angular.svg'
  }
];

interface Item {
  id: string;
  title: string;
  description: string;
  photoCover: string;
}
