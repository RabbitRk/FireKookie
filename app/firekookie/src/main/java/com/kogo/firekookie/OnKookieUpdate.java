package com.kogo.firekookie;

import com.firebase.client.DataSnapshot;

public interface OnKookieUpdate {
    void onUpdate(String result);
    void onError(String result);
}
