import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {Router} from "@angular/router";
import {AuthenticationService} from "../../security/authentication.service";
import {first} from "rxjs/operators";

@Component({
  selector: 'app-profilchange',
  templateUrl: './profilchange.component.html',
  styleUrls: ['./profilchange.component.scss']
})
export class ProfilchangeComponent implements OnInit {
  registerForm: FormGroup;
  loading = false;
  submitted = false;
  error = '';
  localUssername="";
  error2='';
  error3='';
  constructor(private formBuilder: FormBuilder,
              private router: Router,
              private authenticationService: AuthenticationService) { }

  ngOnInit() {
    this.registerForm = this.formBuilder.group({
      username: ['', Validators.required],
      password1: ['', Validators.required],
      password2: ['', Validators.required]
    });
    this.localUssername=JSON.parse(localStorage.getItem('currentUser')).username;
    console.log(this.registerForm['value']['username']);
  }
  get f() {
    return this.registerForm.controls;

  }

  changePassword() {
    if (this.localUssername === this.registerForm['value']['username']) {
      if(this.registerForm['value']['password1']===this.registerForm['value']['password2']) {

        this.submitted = true;
        if (this.registerForm.invalid) {
          return;
        }
        this.loading = true;
        console.log(this.registerForm);
        this.authenticationService.changepassword(this.registerForm.value)
          .pipe(first())
          .subscribe(
            data => {
              this.router.navigate(['/dashboard']);
            },
            error => {
              this.error = error;
              this.loading = false;
            });
      }else {
        this.error3='Password 1 is Password 2 doesnt match';
        console.log(this.error3);
      }

    }else {
      this.error2='Username is doesnt match';
      console.log(this.error2);
    }
  }


}
