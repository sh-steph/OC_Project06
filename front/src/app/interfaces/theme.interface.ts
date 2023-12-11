export interface Theme {
  id: number;
  title: string;
  description: string;
  createdAt: string;
  updatedAt?: string;
}

export interface ThemeList {
  themes: Theme[];
}

export interface ThemeRequest {
  message: String;
}

export interface ThemeResponse {
  message: String;
}
