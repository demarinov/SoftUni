import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { UserService } from 'src/app/core/user.service';
import { emailValidator } from 'src/app/utils';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  loginFormGroup: FormGroup = this.formBuilder.group({
      'email': new FormControl('', [Validators.required, emailValidator]),
      'password' : new FormControl('', [Validators.required, Validators.minLength(5)])
  }); 

  errorMessage!: string;

  constructor(
    private formBuilder: FormBuilder,
    private userService: UserService,
    private router:Router) { }

  ngOnInit(): void {
    this.loginHandler();
  }

  loginHandler() {
    // this.userService.login();
    // this.router.navigate(['/home']);

  }

  handleLogin() {
    console.log('form is submitted');

    this.errorMessage = '';
    this.userService.login$(this.loginFormGroup.value).subscribe({
      next: user => {
        console.log(user);
        this.router.navigate(['/home']);
      },
      complete: () => {
        console.log('complete');
      },
      error: (error) => {
        console.log(error);
        this.errorMessage = error.message;
      } 
    })
  }

}
