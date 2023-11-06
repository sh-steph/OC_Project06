import { Theme } from "./theme.interface";

export interface Post {
    id: number;
    theme: Theme;
    user: {
        id: number;
        username: string;
    }
    title: string;
    description: string;
    createdAt: string;
    updatedAt?: string;
}

export interface PostList {
    posts: Post[];
}
