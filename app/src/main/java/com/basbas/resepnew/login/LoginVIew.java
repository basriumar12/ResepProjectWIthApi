package com.basbas.resepnew.login;

import com.basbas.resepnew.base.View;

public interface LoginVIew  extends View {
    void showLoading();
    void hideLoading();
    void showSukses(String email);
    void showMessage(String message);


}
