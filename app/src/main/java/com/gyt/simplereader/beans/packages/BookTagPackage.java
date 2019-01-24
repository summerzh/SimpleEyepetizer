package com.gyt.simplereader.beans.packages;


import com.gyt.simplereader.beans.BaseBean;
import com.gyt.simplereader.beans.BookTagBean;

import java.util.List;

/**
 * Created by newbiechen on 17-5-1.
 */

public class BookTagPackage extends BaseBean {

    private List<BookTagBean> data;

    public List<BookTagBean> getData() {
        return data;
    }

    public void setData(List<BookTagBean> data) {
        this.data = data;
    }


}
