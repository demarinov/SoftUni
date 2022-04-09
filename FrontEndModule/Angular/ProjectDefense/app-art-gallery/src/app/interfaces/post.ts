import {IUser, ITheme} from "../interfaces"; 
import {IBase} from "./base";

export interface IPost extends IBase{
        likes: string[];
        text: string;
        userId: IUser;
        themeId: ITheme;
        __v: 0
}