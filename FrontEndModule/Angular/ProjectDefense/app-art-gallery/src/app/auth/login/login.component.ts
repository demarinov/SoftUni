import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/core/auth.service';
import { emailValidator, SERVICE_ERROR } from 'src/app/utils';

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
    private authService: AuthService,
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
    this.authService.login$(this.loginFormGroup.value).subscribe({
      next: user => {
        console.log(user);
        this.router.navigate(['/home']);
      },
      complete: () => {
        console.log('complete');
      },
      error: (error) => {
        console.error(error);
        if (error.status >= 400 && error.status <= 499) {
          this.errorMessage = error.error.message;
        } else {
          this.errorMessage = SERVICE_ERROR;
        }
      } 
    })
  }

}
