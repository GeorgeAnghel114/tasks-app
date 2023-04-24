import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Client} from "../client";

@Injectable({
  providedIn: 'root'
})
export class ClientService {
  private clientUrl:string;

  constructor(private http:HttpClient) {
    this.clientUrl='http://localhost:8080/api/client/register';
  }

  public save(client: Client | undefined){
    return this.http.post<Client>(this.clientUrl,client);
  }
}
