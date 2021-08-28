import { Component, OnInit, AfterViewInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { LoginModalService } from 'app/core/login/login-modal.service';
import { PasswordResetFinishChildrenService } from './password-reset-finish-children.service';

@Component({
  selector: 'jhi-password-reset-finish-children',
  templateUrl: './password-reset-finish-children.component.html',
})
export class PasswordResetFinishChildrenComponent implements OnInit, AfterViewInit {
  initialized = false;
  doNotMatch = false;
  error = false;
  success = false;
  key = '';

  constructor(
    private passwordResetFinishService: PasswordResetFinishChildrenService,
    private loginModalService: LoginModalService,
    private route: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.route.queryParams.subscribe(params => {
      if (params['key']) {
        this.key = params['key'];
      }
      this.initialized = true;
    });
  }

  ngAfterViewInit(): void {}

  finishReset(): void {
    this.doNotMatch = false;
    this.error = false;

    const newPassword = 'PASSWORD_FOR_CHILDREN';

    this.passwordResetFinishService.save(this.key, newPassword).subscribe(
      () => (this.success = true),
      () => (this.error = true)
    );
  }

  login(): void {
    this.loginModalService.open();
  }

  saveAndActivate(): void {
    this.finishReset();
  }
}
