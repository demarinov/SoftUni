import { AbstractControl, FormGroup, ValidationErrors } from "@angular/forms";

export function emailValidator(control: AbstractControl): ValidationErrors | null {

    const value = control.value;

    // if (control.errors && Object.keys(control.errors).
    // filter(error => error !== 'email').length > 1) {
    //     return null;
    // }

    if (!value) {
        return null;
    }
 
    if (!/.{6,}@gmail\.(bg|com)/.test(value)) {
        return {
            email: true,
        }
    }

    return null;
}

export function passwordMatch(password: AbstractControl) {

    return  (rePassword : AbstractControl) => {

        if (password.value !== rePassword.value) {
            return {
                passwordMatch: true,
            };
        }

        return null;
    }
}

export function passwordMatch2(passwordControl: AbstractControl) : ValidationErrors | null {

    const passwordGroup = passwordControl.parent as FormGroup;
    
    if (!passwordGroup) {
        return null;
    }

    const {password, rePassword} = passwordGroup.controls;
    
    if (password.value !== rePassword.value) {
        return {
            passwordMatch2: true
        }
    }

    return null;
    
}

export const INTERNAL_SERVER_ERROR = "Internal Server Error";
export const SERVICE_UNAVAILABLE_ERROR = "Service Unavailable";