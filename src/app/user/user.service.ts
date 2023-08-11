import {HttpClient} from "@angular/common/http";
import {Injectable} from "@angular/core";
import {Observable} from "rxjs";
import {user} from "./user.model";


@Injectable()
export class UserService {
  constructor(private http: HttpClient) { }

  private baseURL = 'api/'

  public getAllUser() : Observable<[user]>{
    return this.http.get<[user]>(`${this.baseURL}user/all`)
  }
}
