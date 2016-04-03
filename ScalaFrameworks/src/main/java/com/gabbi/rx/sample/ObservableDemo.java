package com.gabbi.rx.sample;

import rx.Observable;

import java.util.Random;

/**
 * Created by gabbi on 29/12/14.
 */
public class ObservableDemo {
    public static void main(String[] args) {
        RandomObservable randomObservable = new RandomObservable();
        randomObservable.take(5).subscribe(System.out::println);
    }
}

class RandomObservable extends Observable<Integer>{

    /**
     * Creates an Observable with a Function to execute when it is subscribed to.
     * <p>
     * <em>Note:</em> Use {@link #create(rx.Observable.OnSubscribe)} to create an Observable, instead of this constructor,
     * unless you specifically have a need for inheritance.
     *
     */
    protected RandomObservable() {
        super(subscriber -> {
            Random random = new Random();
            while(!subscriber.isUnsubscribed()){
                subscriber.onNext(random.nextInt());
            }
        });
    }
}