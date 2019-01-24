package com.gyt.simplereader.beans.packages;


import com.gyt.simplereader.beans.BaseBean;

import java.util.List;

/**
 * Created by newbiechen on 17-6-2.
 */

public class HotWordPackage extends BaseBean {


    private List<String> hotWords;

    public List<String> getHotWords() {
        return hotWords;
    }

    public void setHotWords(List<String> hotWords) {
        this.hotWords = hotWords;
    }
}
