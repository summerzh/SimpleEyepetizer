package com.gyt.simplereader.beans.packages;


import com.gyt.simplereader.beans.BaseBean;

import java.util.List;

/**
 * Created by newbiechen on 17-6-2.
 */

public class KeyWordPackage extends BaseBean {

    private List<String> keywords;

    public List<String> getKeywords() {
        return keywords;
    }

    public void setKeywords(List<String> keywords) {
        this.keywords = keywords;
    }
}
