import { IBase } from "./base";
import { IUser } from "./user";

export interface IArt extends IBase{
    name: string,
    userId: IUser,
    imageUrl: string,
    price: string,
}