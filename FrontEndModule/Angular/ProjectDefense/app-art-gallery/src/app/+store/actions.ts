import { createAction, props } from "@ngrx/store";
import { IUser } from "../interfaces";



const myCounterDomain = '[MyCounterComponent]'
export const increment = createAction(`${myCounterDomain} Increment`);
export const decrement = createAction(`${myCounterDomain} Decrement`);
export const reset = createAction(`${myCounterDomain} Reset`);


const currentUserDomain = '[CurrentUserComponent]'

export const login = createAction(`${currentUserDomain} Login`, props<{user: IUser}>());
export const logout = createAction(`${currentUserDomain} Logout`);