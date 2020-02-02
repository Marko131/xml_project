import { Injectable } from "@angular/core";
import {
  HttpInterceptor,
  HttpHandler,
  HttpEvent,
  HttpRequest
} from "@angular/common/http";
import { AuthService } from "../_services/auth.service";
import { Observable } from "rxjs";

//Add class for intercepting http request to add jwt token in header

@Injectable()
export class JwtInterceptor implements HttpInterceptor {
  constructor(private authenticationService: AuthService) {}

  intercept(
    request: HttpRequest<any>,
    next: HttpHandler
  ): Observable<HttpEvent<any>> {
    let token = localStorage.getItem("token");
    if (token) {
      request = request.clone({
        setHeaders: {
          "X-Auth-Token": token
        }
      });
    }
    return next.handle(request);
  }
}
