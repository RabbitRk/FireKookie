package com.kogo.firekookie;


import com.google.firebase.database.DataSnapshot;

public interface OnKookieSnap {
    void onComplete(DataSnapshot result);
    void onError(String result);
}
