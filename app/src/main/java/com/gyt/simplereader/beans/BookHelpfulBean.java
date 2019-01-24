package com.gyt.simplereader.beans;


/**
 * Created by newbiechen on 17-4-26.
 */
public class BookHelpfulBean {
    /**
     * total : 1
     * no : 5
     * yes : 6
     */
    private String _id;

    private int total;
    private int no;
    private int yes;

    public BookHelpfulBean(String _id, int total, int no, int yes) {
        this._id = _id;
        this.total = total;
        this.no = no;
        this.yes = yes;
    }


    public BookHelpfulBean() {
    }

    public int getTotal() {
        return total;
    }


    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public int getYes() {
        return yes;
    }

    public void setYes(int yes) {
        this.yes = yes;
    }
}