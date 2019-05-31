package com.hs.user.data.net.protocol;

/*
 *  Copyright © 2018 Radiance Instruments Ltd. All rights reserved.
 *  author ArdWang
 *  email 278161009@qq.com
 *  Created by ArdWang on 5/31/19.
 */

import java.io.File;

import okhttp3.MultipartBody;

public class UploadFileReq {

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    private File file;




}
