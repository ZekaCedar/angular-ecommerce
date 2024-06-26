import { Injectable } from '@angular/core';
import { CartItem } from '../common/cart-item';
import { BehaviorSubject, ReplaySubject, Subject } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class CartService {
  cartItems: CartItem[] = [];

  // Subject
  // Does not keep a buffer of previous events
  // Subscriber only receives new events after they are subscribed
  // totalPrice: Subject<number> = new Subject<number>();
  // totalQuantity: Subject<number> = new Subject<number>();

  // ReplaySubject
  // keep buffer of all previous events
  // Once subscribed, subscriber receives a replay of all previous events
  // send previous events to new subscribers
  // totalPrice: Subject<number> = new ReplaySubject<number>();
  // totalQuantity: Subject<number> = new ReplaySubject<number>();

  // BehaviourSubject
  // Has a buffer of the last events 
  // Once subscribed, subscriber receives the latest event sent prior to subscribing
  // useful for representing "values over time"
  // Subject is the event stream of birthdays
  // BehaviourSubject is the stream of a person's age
  // Buffer of most recent value
  totalPrice: Subject<number> = new BehaviorSubject<number>(0);
  totalQuantity: Subject<number> = new BehaviorSubject<number>(0);

  // storage: Storage = sessionStorage;
  storage: Storage = localStorage;

  constructor() {

    // read data from storage
    let data = JSON.parse(this.storage.getItem('cartItems')!);

    if (data != null) {
      this.cartItems = data;

      // compute totals based on data that is read from storage
      this.computeCartTotals();

    }

  }

  addToCart(theCartItem: CartItem) {
    // check if we already have item in the cart
    let alreadyExistInCart: boolean = false;
    let existingCartItem: CartItem = undefined!;

    if (this.cartItems.length > 0) {
      // find the item in the cart based on item id
      // for (let tempCartItem of this.cartItems) {
      //   if (tempCartItem.id === theCartItem.id) {
      //     existingCartItem = tempCartItem;
      //     break;
      //   }
      // }
      existingCartItem = this.cartItems.find(
        (tempCartItem) => tempCartItem.id === theCartItem.id
      )!;
      // check if we found it
      alreadyExistInCart = existingCartItem != undefined;
    }

    if (alreadyExistInCart) {
      // increment the quantity
      existingCartItem.quantity++;
    } else {
      // just add the item to the array
      this.cartItems.push(theCartItem);
    }

    // compute cart total price and total quantity
    this.computeCartTotals();
  }

  computeCartTotals() {
    let totalPriceValue: number = 0;
    let totalQuantityValue: number = 0;

    for (let currentCartItem of this.cartItems) {
      console.log(currentCartItem);
      totalPriceValue += currentCartItem.quantity * currentCartItem.unitPrice;
      totalQuantityValue += currentCartItem.quantity;
    }

    // publish the new values...all sunscribers will receive the new data
    // .next(...) publish/send event
    // one event for total price
    // one event for totalQuantity
    this.totalPrice.next(totalPriceValue);
    this.totalQuantity.next(totalQuantityValue);

    // log cart data for debugging
    this.logCartData(totalPriceValue, totalQuantityValue);

    // persist cart data
    this.persistCartItems();

  }

  persistCartItems() {
    this.storage.setItem('cartItems', JSON.stringify(this.cartItems));

  }

  logCartData(totalPriceValue: number, totalQuantityValue: number) {
    console.log(`Contents of the cart`);
    for (let tempCartItem of this.cartItems) {
      const subTotalPrice = tempCartItem.quantity * tempCartItem.unitPrice;
      console.log(
        `name: ${tempCartItem.name}, quantity=${tempCartItem.quantity}, unitPrice=${tempCartItem.unitPrice}, subTotalPrice=${subTotalPrice}`
      );
    }
    console.log(
      `totalPrice:${totalPriceValue.toFixed(
        2
      )}, totalQuantity=${totalQuantityValue}`
    );
    console.log(`----`);
  }

  decrementQuantity(theCartItem: CartItem) {
    theCartItem.quantity--;
    if (theCartItem.quantity === 0) {
      this.remove(theCartItem);
    } else {
      this.computeCartTotals();
    }
  }

  remove(theCartItem: CartItem) {
    // get index of item in the array
    const itemIndex = this.cartItems.findIndex(
      (tempCartItem) => tempCartItem.id === theCartItem.id
    );
    // if found , remove the item from the array at the given index
    if (itemIndex > -1) {
      this.cartItems.splice(itemIndex, 1);
      this.computeCartTotals();
    }
  }
}
