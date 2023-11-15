import { Component, OnInit, OnDestroy } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Observable, Subject, of, takeUntil } from 'rxjs';
import {
  CommentList,
  CommentRequest,
  CommentResponse,
} from 'src/app/interfaces/comment.interface';
import { Post } from 'src/app/interfaces/post.interface';
import { CommentsService } from 'src/app/services/api/comments.service';
import { PostsService } from 'src/app/services/api/posts.service';

@Component({
  selector: 'app-post-detail',
  templateUrl: './post-detail.component.html',
  styleUrls: ['./post-detail.component.scss'],
})
export class PostDetailComponent implements OnInit, OnDestroy {
  getPost: Observable<Post | null> = of(null);
  getCommentList: Observable<CommentList | null> = of(null);
  private destroy = new Subject<void>();
  sortOrder: 'asc' | 'desc' = 'asc';
  maxChars = 500;

  postObject: Post[] = [];
  commentList: CommentList = { comments: [] };

  commentForm = this.fb.group({
    comment: [
      '',
      [Validators.required, Validators.min(3), Validators.max(500)],
    ],
  });

  constructor(
    private router: Router,
    private fb: FormBuilder,
    private activatedRoute: ActivatedRoute,
    private postsService: PostsService,
    private commentsService: CommentsService
  ) {}

  ngOnInit(): void {
    this.activatedRoute.params.subscribe((params) => {
      const themeId = params['themeId'];
      const postId = params['postId'];
      this.getPostDetail(themeId, postId);
      this.getAllComments(postId);
    });
  }

  ngOnDestroy() {
    this.destroy.next();
    this.destroy.unsubscribe;
  }

  getPostDetail(themeId: string, postId: string) {
    this.getPost = this.postsService.getPostById(postId, themeId);
    this.getPost.pipe(takeUntil(this.destroy)).subscribe((postsData) => {
      // wait to get data from subscribe
      if (postsData) {
        this.postObject.push(postsData);
      }
    });
    console.log('post obj ', this.postObject);
  }

  getAllComments(postId: string) {
    console.log(postId);
    this.getCommentList = this.commentsService.getAllComments(postId);
    this.getCommentList
      .pipe(takeUntil(this.destroy))
      .subscribe((commentListData) => {
        // wait to get data from subscribe
        if (
          commentListData &&
          commentListData.comments &&
          commentListData.comments.length > 0
        ) {
          this.commentList = commentListData;
        }
      });
  }

  buttonPreviousTab(): void {
    this.router.navigate(['/postList']);
  }

  sortCommentsByDate(comments: any): any {
    return comments.sort((a: any, b: any) => {
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

  public submitComment(postId: number, themeId: number): void {
    let commentRequest: CommentRequest = {
      comment: this.commentForm!.get('comment')?.value || '',
    };
    const getpostId = String(postId);
    const getThemeId = String(themeId);
    this.commentsService
      .createComment(getpostId, commentRequest)
      .subscribe((commentResponse: CommentResponse) =>
        this.refreshPage(getThemeId, getpostId)
      );
  }

  refreshPage(themeId: string, postId: string): void {
    this.router.navigate(['/theme/' + themeId + '/postDetail/' + postId]);
  }
}
