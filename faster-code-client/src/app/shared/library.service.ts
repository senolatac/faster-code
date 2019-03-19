import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

let apiDeviceUrl = 'http://localhost:8080/api/';

@Injectable({
  providedIn: 'root'
})
export class LibraryService {

  constructor(private http: HttpClient) { }

  getAll(): Observable<any> {
    return this.http.get(apiDeviceUrl+'library');
  }

  filter(reportFilter): Observable<any> {
    return this.http.post(apiDeviceUrl+'filter', JSON.stringify(reportFilter) , {headers: { "Content-Type": "application/json; charset=UTF-8" }});
  }

  filterIds(reportFilter): Observable<any> {
    return this.http.post(apiDeviceUrl+'filter-ids', JSON.stringify(reportFilter) , {headers: { "Content-Type": "application/json; charset=UTF-8" }});
  }
}
