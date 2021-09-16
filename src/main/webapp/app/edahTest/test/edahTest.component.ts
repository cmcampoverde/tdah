import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { Observable } from 'rxjs';

import { AccountService } from 'app/core/auth/account.service';
import { Account } from 'app/core/user/account.model';
import { IQuestion } from '../../shared/model/question.model';
import { QuestionService } from '../../entities/question/question.service';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'jhi-password',
  templateUrl: './edahTest.component.html',
})
export class EdahTestComponent implements OnInit {
  doNotMatch = false;
  error = false;
  success = false;
  account$?: Observable<Account | null>;

  displayedColumns: string[] = ['question', 'options', 'type'];

  dataSource?: IQuestion[];
  key?: string;

  constructor(
    private accountService: AccountService,
    private fb: FormBuilder,
    private questionService: QuestionService,
    private route: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.account$ = this.accountService.identity();
    this.route.queryParams.subscribe(params => {
      if (params['key']) {
        this.key = params['key'];
        this.loadEdah();
      }
    });
  }

  loadEdah = () => {
    this.questionService.query().subscribe(response => {
      // eslint-disable-next-line no-console
      console.log(response);
      this.dataSource = response.body as IQuestion[];
    });
  };
}
