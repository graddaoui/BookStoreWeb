import { Book } from './book';

export class Order {
    id: number;
    orderDate: Date;
    address : string ;
    books : Book[];
}