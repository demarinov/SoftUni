import { IUser } from '../interfaces';

export * from './reducer';
export * from './actions';

export interface IRootState {
    currentUser: IUser;
}