import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Constants } from '../asset/constants';
import { Asset } from '../asset/asset';
import { Observable } from 'rxjs';
import { BookAsset } from '../models/BookAssetModel';

@Injectable({
  providedIn: 'root'
})
export class HttpService {

  constructor(private http: HttpClient) {}

  addEmployee(asset : Asset){
    return this.http.post(Constants.ASSET_API+'/add',asset,Constants.HTTP_OPTIONS);
  }

  getAllEmployees(){
    return this.http.get(Constants.ASSET_API+'/all',Constants.HTTP_OPTIONS);
  }

  getAllAssets(): Observable<any>{
    return this.http.get(Constants.ASSET_API+'/all')
  }

  //Book Asset

  bookAsset(bookasset : BookAsset){
    return this.http.post(Constants.BASE_URL+'/assign/request/add',bookasset,Constants.HTTP_OPTIONS);
  }

}
