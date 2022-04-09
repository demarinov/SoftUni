import { IUser } from "./user";
import {IBase} from "./base";

export interface ITheme extends IBase{

    subscribers : string[];
    posts: any[];
    themeName : string;
    userId: IUser;
}