import { Component, OnInit, Input, EventEmitter } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { FormGroup, FormBuilder, FormControl, Validators } from '@angular/forms';


@Component({
  selector: 'app-booking-asset-modal',
  templateUrl: './booking-asset-modal.component.html',
  styleUrls: ['./booking-asset-modal.component.scss']
})
export class BookingAssetModalComponent implements OnInit {
  @Input() assetId: number;
  myForm: FormGroup;

  constructor(public activeModal: NgbActiveModal,  private formBuilder: FormBuilder) {
    this.createForm();
   }

  ngOnInit() {
  }

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// create the form with given data
private createForm() {
  this.myForm = this.formBuilder.group({
    username: '',
    password: ''
  });
}
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// close the modal
  closeModal() {
    this.activeModal.close('Modal Closed');
  }
// ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

// submit the form

private submitForm() {
  this.activeModal.close(this.myForm.value);
}

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////



}