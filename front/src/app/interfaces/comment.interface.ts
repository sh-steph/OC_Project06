import { Post } from './post.interface';

export interface Comment {
  id: number;
  user: {
    id: number;
    username?: string;
  };
  post: Post;
  comment: string;
  createdAt: string;
  updatedAt: string;
}

export interface CommentList {
  comments: Comment[];
}

export interface CommentRequest {
  comment: string;
}

export interface CommentResponse {
  message: string;
}
