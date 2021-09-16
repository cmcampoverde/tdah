import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { TestEdah } from '../../shared/model/test-edah.model';

@Injectable({ providedIn: 'root' })
export class EdahTestService {
  constructor(private http: HttpClient) {}

  saveTest(test: TestEdah): Observable<{}> {
    return this.http.post(SERVER_API_URL + 'api/test-edah/', test);
  }
}
