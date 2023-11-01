package com.zss.common.http.cookie;


import android.util.Log;


import com.zss.common.http.cookie.store.CookieStore;

import java.util.List;

import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;

/**
 * Created by goldze on 2017/5/13.
 */
public class CookieJarImpl implements CookieJar {
    private static final String TAG = "CookieJarImpl";
    private CookieStore cookieStore;

    public CookieJarImpl(CookieStore cookieStore) {
        if (cookieStore == null) {
            throw new IllegalArgumentException("cookieStore can not be null!");
        }
        this.cookieStore = cookieStore;
        Log.d(TAG, "CookieJarImpl: ");
    }

    @Override
    public synchronized void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
        cookieStore.saveCookie(url, cookies);
        Log.d(TAG, "saveFromResponse: ");
    }

    @Override
    public synchronized List<Cookie> loadForRequest(HttpUrl url) {
        return cookieStore.loadCookie(url);
    }

    public CookieStore getCookieStore() {
        return cookieStore;
    }

    public void removeAllCookies() {
        cookieStore.removeAllCookie();
    }
}