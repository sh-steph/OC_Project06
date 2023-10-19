import { Post } from "./post.interface";

export interface Comment {
    id: number;
    user: {
        id: number;
        name?: string;
    };
    post: Post
    comment: string;
    createdAt: string;
    updatedAt: string;
}
