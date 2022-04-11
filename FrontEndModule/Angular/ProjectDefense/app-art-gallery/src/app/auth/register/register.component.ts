import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/core/auth.service';
import { CreateUserDto, UserService } from 'src/app/core/user.service';
import { emailValidator, passwordMatch } from 'src/app/utils';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {


  errorMessage: string;

  get passwordGroup() : FormGroup {
    return this.registerFormGroup.controls['passwords'] as FormGroup;
  }

  passwordControl = new FormControl(null, [Validators.required, Validators.minLength(5)]);

  registerFormGroup: FormGroup = this.formBuilder.group({
    'username': new FormControl(null, [Validators.required, Validators.minLength(5)]),
    'email': new FormControl(null, [Validators.required, emailValidator]),
    'passwords': new FormGroup({
        'password': this.passwordControl,
        "rePassword": new FormControl('',[passwordMatch(this.passwordControl)]),
    }),
    'tel': new FormControl(''),
    'telRegion': new FormControl('')
  })

  constructor(private formBuilder: FormBuilder, 
    private authService: AuthService,
    private router: Router) { }

  ngOnInit(): void {
  }

  handleRegister() {
      console.log(this.registerFormGroup.value);

      const {username, email, passwords, tel, telRegion} = this.registerFormGroup.value;

      const body: CreateUserDto = {
        username: username,
        email: email,
        password: passwords.password,
        // ...(!!tel && {tel: telRegion+tel})

      }

      if (tel) {
        body['tel'] = telRegion + tel;
      }

      console.log(body);

      this.authService.register$(body).subscribe({

        next: (data) => this.router.navigate(['/home']),
        error: (err) => {
          this.errorMessage = err.message;
        }
      })
  }

}
