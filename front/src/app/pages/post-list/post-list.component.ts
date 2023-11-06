import { DatePipe } from '@angular/common';
import { Component, OnDestroy, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Observable, Subject, Subscription, of, takeUntil } from 'rxjs';
import { Post, PostList } from 'src/app/interfaces/post.interface';
import { PostsService } from 'src/app/services/api/posts.service';

@Component({
  selector: 'app-post-list',
  templateUrl: './post-list.component.html',
  styleUrls: ['./post-list.component.scss'],
  providers: [DatePipe],
})
export class PostListComponent implements OnInit, OnDestroy {
  public getAllPosts: Observable<PostList | null> = of(null);
  private destroy = new Subject<void>();
  sortOrder: 'asc' | 'desc' = 'asc';
  arrowBool: boolean = true;
  postList: PostList = { posts: [] };
  getPost: PostList = { posts: [] };

  constructor(private postsService: PostsService, private router: Router) {}

  ngOnInit(): void {
    this.getAllPosts = this.postsService.getAllPost();
    this.getAllPosts.pipe(takeUntil(this.destroy)).subscribe((postsData) => {
      // wait to get data from subscribe
      if (postsData) {
        this.postList = postsData;
      }
    });
  }

  ngOnDestroy() {
    this.destroy.next();
    this.destroy.unsubscribe;
  }

  public buttonCreatePost(): void {
    this.router.navigate(['/postCreate']); // temporaire
  }

  toggleArrow() {
    this.arrowBool = !this.arrowBool;
    this.sortOrder = this.sortOrder === 'asc' ? 'desc' : 'asc';
    this.postList.posts = this.sortPostsByDate(this.postList.posts);
  }

  sortPostsByDate(posts: Post[]): Post[] {
    return posts.sort((a, b) => {
      if (this.sortOrder === 'asc') {
        return (
          new Date(a.createdAt).getTime() - new Date(b.createdAt).getTime()
        );
      } else {
        return (
          new Date(b.createdAt).getTime() - new Date(a.createdAt).getTime()
        );
      }
    });
  }

  postDetail(post: Post): void {
    let getPostId = '';
    let getThemeId = '';
    this.getPost = { posts: [] };
    this.getPost.posts.push(post);
    getPostId = String(this.getPost.posts.find(p => p.id));
    console.log(getPostId);
    console.log(this.getPost);
    // this.router.navigate(['/postDetail']);
  }
}
