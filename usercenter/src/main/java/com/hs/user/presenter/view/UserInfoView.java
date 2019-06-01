package com.hs.user.presenter.view;

import com.hs.base.presenter.view.BaseView;
import com.hs.user.model.FileBean;
import com.hs.user.model.User;


public interface UserInfoView extends BaseView {
    /**
        编辑用户资料回调
     */
    void onEditUserResult(User result);

    /**
     * 获取上传照片
     * @param result
     */
    void upLoadFile(Boolean result);

    /**
     * 获取返回照片
     * @param result
     */
    void onGetFile(FileBean result);

    /**
     * 获取返回照片错误
     * @param result
     */
    void onGetFileError(String result);


//    /**
//     * 开始上传
//     */
//    void uploadStart();
//
//    /**
//     * 上传中
//     */
//    void uploading(long bytesWritten, long contentLength);
//
//    /**
//     * 上传成功
//     * @param file
//     */
//    void uploadSuccess(File file);
//
//    /**
//     * 上传失败
//     * @param error
//     */
//    void uploadFail(String error);
//
//    /**
//     * 上传完成
//     */
//    void uploadComplete();


}
