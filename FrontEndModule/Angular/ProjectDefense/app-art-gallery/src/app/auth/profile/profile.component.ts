import { Component, OnInit, ViewChild } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { UserService } from 'src/app/core/user.service';
import { IUser } from 'src/app/interfaces';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {

  inEditMode: boolean = false;

  currentUser!: IUser;

  @ViewChild('editProfileForm') editProfileForm!:NgForm

  constructor(private userService: UserService, private router:Router) { }

  ngOnInit(): void {

    this.userService.getProfile$().subscribe({
      next: user => {
        this.currentUser = user;
        console.log(user);
      },
      error: error => {
        console.log(error);
        this.router.navigate(['/home']);
      }
    })
  }

  enterEditMode() : void {
    this.inEditMode = true;

    setTimeout(() => {

      this.editProfileForm.form.patchValue({
        email: this.currentUser.email,
        username: this.currentUser.username,
        'select-tel': this.currentUser.tel && this.currentUser.tel.length > 6 
        ? this.currentUser.tel.substring(0,4) : '',
        tel: this.currentUser.tel && this.currentUser.tel.length > 6 
        ? this.currentUser.tel.substring(4) : this.currentUser.tel
      })
    })
  }

  updateProfile() : void {
    console.log(this.editProfileForm.value);
    this.inEditMode = false;
    // make put request

    this.editProfileForm.value.tel = this.editProfileForm.value['select-tel'] + this.editProfileForm.value.tel;
    this.userService.editProfile$(this.editProfileForm.value).subscribe({
      next: user => {
        this.currentUser = user;
        console.log(user);
      },
      error: error => {
        console.log(error);
        this.router.navigate(['/home']);
      }
    });

  }
}
