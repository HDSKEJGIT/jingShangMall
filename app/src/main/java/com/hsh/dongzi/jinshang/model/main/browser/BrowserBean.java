package com.hsh.dongzi.jinshang.model.main.browser;

import com.hsh.dongzi.jinshang.ui.main.browser.BrowserView;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/12/15 0015.
 */

public class BrowserBean implements Serializable {
    protected String url;
    protected int mode = BrowserView.MODE_DEFAULT;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getMode() {
        return mode;
    }

    public void setMode(int mode) {
        this.mode = mode;
    }
}
