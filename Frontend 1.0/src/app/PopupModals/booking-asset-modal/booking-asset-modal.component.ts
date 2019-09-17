import { Component, OnInit, Input, EventEmitter } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { FormGroup, FormBuilder, FormControl, Validators, NgForm } from '@angular/forms';
import { BookAsset } from '../../models/BookAssetModel';
import { HttpService } from '../../service2/http.service';
import { Employee } from '../../firebase/model';
import { NotifiService } from '../../firebase/notifi.service';
import { AngularFirestore } from '@angular/fire/firestore';
import { formatDate } from '@angular/common';


@Component({
  selector: 'app-booking-asset-modal',
  templateUrl: './booking-asset-modal.component.html',
  styleUrls: ['./booking-asset-modal.component.scss']
})
export class BookingAssetModalComponent implements OnInit {
  @Input() assetCategory: number;
  @Input() assetId: number;

  
  myForm: FormGroup;

  bookasset: BookAsset;
  datePipe: any;
  employee:Employee;
  today= new Date();
  jstoday = '';
  constructor(public activeModal: NgbActiveModal,  private formBuilder: FormBuilder,private bookservices:HttpService,private ser : NotifiService,private firestore :AngularFirestore) {
    this.createForm();
    this.bookasset=new BookAsset();
    this.employee=new Employee();
    this.jstoday = formatDate(this.today, 'dd-MM-yyyy hh:mm:ss a', 'en-US', '+0530');


    //booking to assetId and enmployee Nic
    this.bookasset.assetId="AA87";
    this.bookasset.nic="961341175v";
   }

  ngOnInit() {
    
  }

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// create the form with given data
private createForm() {
  this.myForm = this.formBuilder.group({
    assetId:'',
    username: '',
    beginDate:'',
    dueDate:'',
    massege:'',
    date:''

   
  });
}
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// close the modal
  closeModal() {
    this.activeModal.close('Modal Closed');
  }
// ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

// submit the form



//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

resetForm(form ? : NgForm){
  if(form != null)
  form.resetForm();
  this.ser.FormData = {
    id : null,
   
    AssetCategory: '' ,
    BrandName:'',
    Discription:'',
    ReturnDate:'',
    OrderDate:''

   
  }
}
onSubmit(form:NgForm){

  

  console.log();

  
 
  
  let now = new Date();
  console.log(this.employee);

  let data = Object.assign({}, form.value);
  delete data.id;
  data.assetCategory=this.assetCategory;
  data.assetId=this.assetId;
  data.Discription=this.jstoday;

  if(form.value.id == null){
   
    this.firestore.collection('BookAssetNotification').add(data);
    
    
   
}
  else {
    this.firestore.doc('BookAssetNotification/'+form.value.id).update(data);
     
      alert('Do you want Book this One');
  }

  this.resetForm(form);
 



}



}




  