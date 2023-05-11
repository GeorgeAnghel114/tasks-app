export interface Task {
  id: number;
  subject: string;
  status: string;
  duedate: string;

}
export interface AllTask{
  id?:number;
  subject?: string;
  status?: string;
  duedate?: string;
  clientId?:number;
  clientEmail?:string;
}

export interface SearchForm {
  clientId:number | null;
  subject:string | null;
  duedate:string | null;
}
