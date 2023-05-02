import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Client} from "../client";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class ClientService {
  private readonly clientUrl: string;
  private readonly allClients:string;

  constructor(private http: HttpClient) {
    this.clientUrl = 'http://localhost:8080/api/client/register';
    this.allClients = 'http://localhost:8080/api/client/all-clients';
  }

  public save(client: Client | undefined) {
    return this.http.post<Client>(this.clientUrl, client);
  }

  public getAllClients(){
    return this.http.get<Client[]>(this.allClients);

  }


}
