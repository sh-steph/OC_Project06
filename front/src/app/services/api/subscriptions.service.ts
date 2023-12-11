import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { SubscriptionList, SubscriptionResponse } from 'src/app/interfaces/subscription.interface';

@Injectable({
  providedIn: 'root'
})
export class SubscriptionsService {

  private pathService = '/api/subscription';

  constructor(private httpClient: HttpClient) { }

  public getAllSubscriptionFromUser(): Observable<SubscriptionList> {
    return this.httpClient.get<SubscriptionList>(this.pathService + '/user');
  }

  public addSubscription(themeId: string): Observable<SubscriptionResponse> {
    return this.httpClient.post<SubscriptionResponse>(`${this.pathService}/theme/${themeId}`, null);
  }

  public removeSubscription(themeId: string): Observable<SubscriptionResponse> {
    return this.httpClient.delete<SubscriptionResponse>(`${this.pathService}/theme/${themeId}`);
  }
}
