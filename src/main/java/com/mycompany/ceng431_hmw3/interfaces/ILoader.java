package com.mycompany.ceng431_hmw3.interfaces;

import java.util.List;

public interface ILoader<T> {

    public void load();
    public List<T> getElements();


}
