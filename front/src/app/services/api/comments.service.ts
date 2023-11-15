import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { CommentList, CommentRequest, CommentResponse } from 'src/app/interfaces/comment.interface';

@Injectable({
  providedIn: 'root'
})
export class CommentsService {

    private pathService = '/api/comment/post';

    constructor(private httpClient: HttpClient) { }
  
    public getAllComments(postId: string): Observable<CommentList> {
      return this.httpClient.get<CommentList>(`${this.pathService}/${postId}`);
    }
  
    public createComment(postId: string, commentRequest: CommentRequest): Observable<CommentResponse> {
      return this.httpClient.post<CommentResponse>(`${this.pathService}/${postId}`, commentRequest);
    }
}
