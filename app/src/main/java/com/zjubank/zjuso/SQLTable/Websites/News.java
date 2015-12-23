package com.zjubank.zjuso.SQLTable.Websites;

import java.util.List;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.datatype.BmobDate;

/**
 * Created by ZJUBank on 15/12/22.
 */
public class News extends BmobObject {
    private BmobDate date;
    private String link;
    private String title;
    private String content;
    private List usr;
    private List comment;

    public BmobDate getDate() {

        return date;
    }

    public String getLink() {

        return link;
    }

    public String getTitle() {

        return title;
    }

    public String getContent() {

        return content;
    }

    public List getUsr() {

        return usr;
    }

    public List getComment() {

        return comment;
    }
}