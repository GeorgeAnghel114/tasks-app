import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Client} from "../client";

@Injectable({
  providedIn: 'root'
})
export class ClientService {
  private readonly clientUrl: string;
  private readonly allClients: string;

  constructor(private http: HttpClient) {
    this.clientUrl = 'http://localhost:8080/api/client/register';
    this.allClients = 'http://localhost:8080/api/client/all-clients';
  }

  public getAllClients() {
    return this.http.get<Client[]>(this.allClients);

  }


}
