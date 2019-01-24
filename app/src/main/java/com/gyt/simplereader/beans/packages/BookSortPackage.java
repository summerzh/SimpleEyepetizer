package com.gyt.simplereader.beans.packages;


import com.gyt.simplereader.beans.BaseBean;
import com.gyt.simplereader.beans.BookSortBean;

import java.util.List;

/**
 * Created by newbiechen on 17-4-23.
 */

public class BookSortPackage extends BaseBean {

    private List<BookSortBean> male;
    private List<BookSortBean> female;

    public List<BookSortBean> getMale() {
        return male;
    }

    public void setMale(List<BookSortBean> male) {
        this.male = male;
    }

    public List<BookSortBean> getFemale() {
        return female;
    }

    public void setFemale(List<BookSortBean> female) {
        this.female = female;
    }
}
