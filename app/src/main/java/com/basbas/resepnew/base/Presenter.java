package com.basbas.resepnew.base;

public interface Presenter <T extends View> {
    void onAttach(T view);
    void onDetach();
}