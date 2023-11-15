import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Theme, ThemeList } from 'src/app/interfaces/theme.interface';

@Injectable({
  providedIn: 'root'
})
export class ThemesService {

  private pathService = '/api/theme';

  constructor(private httpClient: HttpClient) { }

  public getAllTheme(): Observable<ThemeList> {
    return this.httpClient.get<ThemeList>(this.pathService);
  }

  public getThemeById(idPost: string, idTheme: string ): Observable<Theme> {
    return this.httpClient.get<Theme>(`${this.pathService}/${idPost}/theme/${idTheme}`);
  }
}
