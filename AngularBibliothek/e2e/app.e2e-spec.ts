import { AngularBibliothekPage } from './app.po';

describe('angular-bibliothek App', () => {
  let page: AngularBibliothekPage;

  beforeEach(() => {
    page = new AngularBibliothekPage();
  });

  it('should display message saying app works', () => {
    page.navigateTo();
    expect(page.getParagraphText()).toEqual('app works!');
  });
});
