import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ITestAnswer } from 'app/shared/model/test-answer.model';

type EntityResponseType = HttpResponse<ITestAnswer>;
type EntityArrayResponseType = HttpResponse<ITestAnswer[]>;

@Injectable({ providedIn: 'root' })
export class TestAnswerService {
  public resourceUrl = SERVER_API_URL + 'api/test-answers';

  constructor(protected http: HttpClient) {}

  create(testAnswer: ITestAnswer): Observable<EntityResponseType> {
    return this.http.post<ITestAnswer>(this.resourceUrl, testAnswer, { observe: 'response' });
  }

  update(testAnswer: ITestAnswer): Observable<EntityResponseType> {
    return this.http.put<ITestAnswer>(this.resourceUrl, testAnswer, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<ITestAnswer>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ITestAnswer[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
