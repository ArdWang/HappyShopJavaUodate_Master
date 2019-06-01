package com.hs.user.model;

/*
 *  Copyright  2018 Radiance Instruments Ltd. All rights reserved.
 *  author ArdWang
 *  email 278161009@qq.com
 *  Created by ArdWang on 2019/6/1.
 */

public class Files {
    private String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public FileBean getFileBean() {
        return fileBean;
    }

    public void setFileBean(FileBean fileBean) {
        this.fileBean = fileBean;
    }

    private String message;
    private FileBean fileBean;
}
