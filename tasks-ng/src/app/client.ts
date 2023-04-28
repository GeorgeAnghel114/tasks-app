export interface Client {
  email?: string;
  username?: string;
  password?: string;

}

export interface LoggedUser {
  token: string;
  username: string;
  userId: number;
}

export interface RegisterUser {
  username: string;
  email: string;
  password: string;

}
