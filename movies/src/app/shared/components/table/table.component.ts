import { Component, OnInit, ViewChild } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';

@Component({
  selector: 'app-table',
  templateUrl: './table.component.html',
  styleUrls: []
})
export class TableComponent implements OnInit {
  @ViewChild(MatPaginator, {static: true}) paginator: MatPaginator;
  @ViewChild(MatSort, {static: true}) sort: MatSort;
  dataSource: any[] = [
    {id: 1, name: 'test'},
    {id: 2, name: 'test 2'}
  ];

  displayedColumns: string[] = ['id', 'name'];

  ngOnInit(): void {
    console.log(this.paginator);
  }

  changePage(page: any): void {
    console.log(page);
  }

}
