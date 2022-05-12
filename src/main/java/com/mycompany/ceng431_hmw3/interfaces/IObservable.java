package com.mycompany.ceng431_hmw3.interfaces;

public interface IObservable<T> {


    public void registerObserver(T observer);

    public void removeObserver(T observer);
    public void notifyAllObserver();
}
