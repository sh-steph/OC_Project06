import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Observable, Subject, of, takeUntil } from 'rxjs';
import { Post } from 'src/app/interfaces/post.interface';
import { PostRequest, PostResponse } from 'src/app/interfaces/postRequest.interfaces';
import { ThemeList } from 'src/app/interfaces/theme.interface';
import { PostsService } from 'src/app/services/api/posts.service';
import { ThemesService } from 'src/app/services/api/themes.service';

@Component({
  selector: 'app-post-create',
  templateUrl: './post-create.component.html',
  styleUrls: ['./post-create.component.scss'],
})
export class PostCreateComponent implements OnInit {
  public onError = false;
  messageError: String = '';
  getAllThemes: Observable<ThemeList | null> = of(null);
  private destroy = new Subject<void>();
  themeList: ThemeList = { themes: [] };
  postForm = this.fb.group({
    theme: ['', [Validators.required]],
    title: ['', [Validators.required, Validators.min(3), Validators.max(50)]],
    description: [
      '',
      [Validators.required, Validators.min(3), Validators.max(500)],
    ],
  });
  themeSelected: String = '';

  constructor(
    private router: Router,
    private fb: FormBuilder,
    private postsService: PostsService,
    private themesService: ThemesService
  ) {}

  ngOnInit(): void {
    this.getAllThemes = this.themesService.getAllTheme();
    this.getAllThemes.pipe(takeUntil(this.destroy)).subscribe((themesData) => {
      // wait to get data from subscribe
      if (themesData) {
        this.themeList = themesData;
      }
    });
  }

  ngOnDestroy() {
    this.destroy.next();
    this.destroy.unsubscribe;
  }

  public submit(): void {
    let postRequest :PostRequest = {
      title : this.postForm!.get('title')?.value || '',
      description: this.postForm!.get('description')?.value || ''
    };
    const themeId = String(this.getThemeId(this.postForm.get('theme')?.value || ''));
    this.postsService
      .createPost(themeId, postRequest)
      .subscribe((postResponse: PostResponse) => this.exitPage(postResponse));
  }

  getThemeId(themeSelected: string): number | undefined {
    const theme = this.themeList.themes.find((t) => t.title === themeSelected);
    return theme ? theme.id : undefined;
  }

  buttonPreviousTab(): void {
    this.router.navigate(['/postList']);
  }

  private exitPage(postResponse: PostResponse): void {
    this.router.navigate(['postList']);
  }
  
}
