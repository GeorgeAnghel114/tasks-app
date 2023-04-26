export interface Client {
  email?: string;
  username?: string;
  password?: string;

}

export interface LoggedUser{
  token:string;
  username:string;
  userId:number;
}
