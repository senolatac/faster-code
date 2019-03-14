import { Component,  ElementRef, OnInit, ViewChild } from '@angular/core';
import { LibraryService } from '../shared/library.service';
import {FormControl, FormGroup} from '@angular/forms';
import {MatPaginator, MatTableDataSource, MatSort} from '@angular/material';
import { saveAs } from 'file-saver';

@Component({
  selector: 'app-faster',
  templateUrl: './faster.component.html',
  styleUrls: ['./faster.component.css']
})
export class FasterComponent implements OnInit {

  selectedLibraries = new FormControl();
  selectedBrand = new FormControl();
  libraries: Array<any>;
  transactions: Array<any>;
  ELEMENT_DATA: Array<any>;
  fileUrl;
  reportFilter = {freeText:"",libraryList:null,localDateTime:null};
  displayedColumns: string[] = ['library', 'book', 'member'];
  dataSource;
  searching = false;
  searchAllExecutionTime= 0;
  searchSingleExecutionTime= 0;
  searchTextExecutionTime= 0;
  downloadExecutionTime= 0;
  executionStartTime:any;
  executionEndTime:any;

  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;

  constructor(private libraryService: LibraryService) {
  }

  ngOnInit() {
    this.libraryService.getAll().subscribe(data => {
      this.libraries = data;
    });
  }


  searchDetail(){
    this.searching = true;
    this.reportFilter.freeText = this.selectedBrand.value;
    this.reportFilter.libraryList = [];
    for(var i=0; i<this.selectedLibraries.value.length; i++){
      this.reportFilter.libraryList.push(this.selectedLibraries.value[i].id);
    }
    this.startExecution();
    this.libraryService.filter(this.reportFilter).subscribe((data) => {
        this.transactions = data;
        this.ELEMENT_DATA = [];
        for(var i=0; i<this.transactions.length; i++){
            var tableElement = {library:"",book:"",member:""};
            tableElement.library = this.transactions[i].book.library.name;
            tableElement.book = this.transactions[i].book.title;
            tableElement.member = this.transactions[i].member.name;
            this.ELEMENT_DATA.push(tableElement);
        }
        this.dataSource = new MatTableDataSource<PeriodicElement>(this.ELEMENT_DATA);
        setTimeout(() => this.dataSource.paginator = this.paginator);
        setTimeout(() => this.dataSource.sort = this.sort);
        this.searching = false;
        this.endExecution(true);
    }, (err) => {
      console.log(err);
    });
  }

  downloadIds(){
    this.startExecution();
    this.libraryService.filterIds(this.reportFilter).subscribe((data) => {
      const blob = new Blob([data.response], { type: 'text/plain' });
      saveAs(blob, "transaction-ids.txt");
      this.endExecution(false);
    }, (err) => {
      console.log(err);
    });
  }

  downloadFile(){
    const replacer = (key, value) => value === null ? '' : value;
    const header = Object.keys(this.ELEMENT_DATA[0]);
    let csv = this.ELEMENT_DATA.map(row => header.map(fieldName => JSON.stringify(row[fieldName], replacer)).join(','));
    csv.unshift(header.join(','));
    let csvArray = csv.join('\r\n');

    var blob = new Blob([csvArray], {type: 'text/csv' })
    saveAs(blob, "transactions.csv");
  }

  applyFilter(filterValue: string) {
    this.dataSource.filter = filterValue.trim().toLowerCase();
  }

  selectAll(ev){
    if(ev._selected){
      this.selectedLibraries.setValue(this.libraries);
      ev._selected=true;
    }
    if(ev._selected==false){
      this.selectedLibraries.setValue([]);
    }
  }

  startExecution(){
    this.executionStartTime = performance.now();
  }

  endExecution(isSearch){
    this.executionEndTime = performance.now();
    if(isSearch){
      if(this.reportFilter.freeText){
        this.searchTextExecutionTime = this.executionEndTime - this.executionStartTime;
      }else if(this.reportFilter.libraryList.length>1){
        this.searchAllExecutionTime = this.executionEndTime - this.executionStartTime;
      }else{
        this.searchSingleExecutionTime = this.executionEndTime - this.executionStartTime;
      }
    }else{
      this.downloadExecutionTime = this.executionEndTime - this.executionStartTime;
    }
  }

  }

  export interface PeriodicElement {
  library: string;
  book: string;
  member: string;
  }
