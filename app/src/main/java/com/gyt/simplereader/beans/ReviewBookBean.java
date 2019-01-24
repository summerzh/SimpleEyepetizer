package com.gyt.simplereader.beans;

/**
 * Created by newbiechen on 17-4-26.
 * 书评区的书籍
 */

public class ReviewBookBean {
    /**
     * _id : 530f3912651881e60d04deb3
     * cover : /agent/http://img.17k.com/images/bookcover/2014/3769/18/753884-1399818238000.jpg
     * title : 我的26岁女房客
     * site : zhuishuvip
     * type : dsyn
     */
    private String _id;

    private String cover;
    private String title;
    private String site;
    private String type;

    public ReviewBookBean(String _id, String cover, String title, String site, String type) {
        this._id = _id;
        this.cover = cover;
        this.title = title;
        this.site = site;
        this.type = type;
    }

    public ReviewBookBean() {
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}