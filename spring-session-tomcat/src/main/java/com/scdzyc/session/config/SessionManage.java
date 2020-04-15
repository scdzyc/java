package com.scdzyc.session.config;

import javax.servlet.http.HttpSession;

public class SessionManage {

    private static ThreadLocal<HttpSession> threadLocal = new ThreadLocal();

    private void SessionManage(){

    }

    public static HttpSession getSession() {
        return threadLocal.get();
    }

    public static void setSession(HttpSession httpSession) {
        threadLocal.set(httpSession);
    }
}
