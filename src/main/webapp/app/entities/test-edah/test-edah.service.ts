import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ITestEdah } from 'app/shared/model/test-edah.model';

type EntityResponseType = HttpResponse<ITestEdah>;
type EntityArrayResponseType = HttpResponse<ITestEdah[]>;

@Injectable({ providedIn: 'root' })
export class TestEdahService {
  public resourceUrl = SERVER_API_URL + 'api/test-edahs';

  constructor(protected http: HttpClient) {}

  create(testEdah: ITestEdah): Observable<EntityResponseType> {
    return this.http.post<ITestEdah>(this.resourceUrl, testEdah, { observe: 'response' });
  }

  update(testEdah: ITestEdah): Observable<EntityResponseType> {
    return this.http.put<ITestEdah>(this.resourceUrl, testEdah, { observe: 'response' });
  }

  updatePublic(testEdah: ITestEdah): Observable<EntityResponseType> {
    return this.http.put<ITestEdah>(SERVER_API_URL + 'api/update/test-edahs', testEdah, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<ITestEdah>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ITestEdah[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  sendEmailEdah(testEdah: ITestEdah): Observable<EntityResponseType> {
    return this.http.post<ITestEdah>(`${this.resourceUrl}/send-edah`, testEdah, { observe: 'response' });
  }

  readTestEdahs(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ITestEdah[]>(SERVER_API_URL + 'api/read/test-edahs', { params: options, observe: 'response' });
  }
}
