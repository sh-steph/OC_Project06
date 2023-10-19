export interface User {
  id: number;
  email: string;
  username: string;
  admin: boolean;
  password: string;
  createdAt: string;
  updatedAt?: string;
}
