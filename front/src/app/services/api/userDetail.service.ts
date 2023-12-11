import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { UserDetail, UserDetailResponse } from 'src/app/interfaces/userDetail.interface';

@Injectable({
  providedIn: 'root'
})
export class UserDetailService {

  private pathService = '/api/user';

  constructor(private httpClient: HttpClient) { }

  public updateUsernameAndEmail(userDetail: UserDetail ): Observable<UserDetailResponse> {
    return this.httpClient.put<UserDetailResponse>(`${this.pathService}`, userDetail);
  }
}
