import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import './vendor';
import { TdahSharedModule } from 'app/shared/shared.module';
import { TdahCoreModule } from 'app/core/core.module';
import { TdahAppRoutingModule } from './app-routing.module';
import { TdahHomeModule } from './home/home.module';
import { TdahEntityModule } from './entities/entity.module';
// jhipster-needle-angular-add-module-import JHipster will add new module here
import { MainComponent } from './layouts/main/main.component';
import { NavbarComponent } from './layouts/navbar/navbar.component';
import { FooterComponent } from './layouts/footer/footer.component';
import { PageRibbonComponent } from './layouts/profiles/page-ribbon.component';
import { ActiveMenuDirective } from './layouts/navbar/active-menu.directive';
import { ErrorComponent } from './layouts/error/error.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

@NgModule({
  imports: [
    BrowserModule,
    TdahSharedModule,
    TdahCoreModule,
    TdahHomeModule,
    // jhipster-needle-angular-add-module JHipster will add new module here
    TdahEntityModule,
    TdahAppRoutingModule,
    BrowserAnimationsModule,
  ],
  declarations: [MainComponent, NavbarComponent, ErrorComponent, PageRibbonComponent, ActiveMenuDirective, FooterComponent],
  bootstrap: [MainComponent],
})
export class TdahAppModule {}
