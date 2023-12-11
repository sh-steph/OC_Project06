import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { PostList, Post } from 'src/app/interfaces/post.interface';
import { PostRequest, PostResponse } from 'src/app/interfaces/postRequest.interfaces';

@Injectable({
  providedIn: 'root'
})
export class PostsService {

  private pathService = '/api/post';

  constructor(private httpClient: HttpClient) { }

  public getAllPost(): Observable<PostList> {
    return this.httpClient.get<PostList>(this.pathService);
  }

  public getPostById(idPost: string, idTheme: string ): Observable<Post> {
    return this.httpClient.get<Post>(`${this.pathService}/${idPost}/theme/${idTheme}`);
  }

  public createPost(idTheme: string, postRequest: PostRequest): Observable<PostResponse> {
    return this.httpClient.post<PostResponse>(`${this.pathService}/theme/${idTheme}`, postRequest);
  }
}
