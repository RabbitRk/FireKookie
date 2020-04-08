package com.kogo.firekookie;


import com.google.firebase.database.DataSnapshot;

public interface OnKookieListener {
    void onAdded(DataSnapshot result, String previousChild);
    void onChanged(DataSnapshot result, String previousChild);
    void onRemoved(DataSnapshot result);
    void onMoved(DataSnapshot result, String previousChild);
    void onCancelled(String error);
}
