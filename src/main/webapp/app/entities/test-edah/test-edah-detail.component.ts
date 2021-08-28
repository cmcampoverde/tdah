import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ITestEdah } from 'app/shared/model/test-edah.model';

@Component({
  selector: 'jhi-test-edah-detail',
  templateUrl: './test-edah-detail.component.html',
})
export class TestEdahDetailComponent implements OnInit {
  testEdah: ITestEdah | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ testEdah }) => (this.testEdah = testEdah));
  }

  previousState(): void {
    window.history.back();
  }
}
