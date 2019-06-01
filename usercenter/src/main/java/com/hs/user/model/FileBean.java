package com.hs.user.model;

/*
 *  Copyright  2018 Radiance Instruments Ltd. All rights reserved.
 *  author ArdWang
 *  email 278161009@qq.com
 *  Created by ArdWang on 2019/5/31.
 */

public class FileBean {
    private Integer fileid;

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    private Integer userid;

    private String filename;
    private String fileurl;
    private String createtime;
    private String updatetime;

    public Integer getFileid() {
        return fileid;
    }

    public void setFileid(Integer fileid) {
        this.fileid = fileid;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getFileurl() {
        return fileurl;
    }

    public void setFileurl(String fileurl) {
        this.fileurl = fileurl;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public String getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(String updatetime) {
        this.updatetime = updatetime;
    }
}
