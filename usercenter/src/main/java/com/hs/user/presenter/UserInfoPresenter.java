package com.hs.user.presenter;

import android.util.Log;

import com.hs.base.presenter.BasePresenter;
import com.hs.base.rx.BaseObserver;
import com.hs.user.data.net.repository.UploadRepository;
import com.hs.user.model.Files;
import com.hs.user.model.User;
import com.hs.user.data.net.repository.UserRepository;
import com.hs.user.presenter.view.UserInfoView;
import com.trello.rxlifecycle2.LifecycleProvider;
import com.trello.rxlifecycle2.android.ActivityEvent;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;


/**
 * Created by rnd on 2018/4/10.
 * 用户信息
 */
public class UserInfoPresenter extends BasePresenter<UserInfoView>{

    //用户请求体
    private UserRepository userRepository;

    private UploadRepository uploadRepository;

    /**
     * 上传文件
     *
     * @param file
     */
    public void uploadFile(File file){
        uploadRepository = new UploadRepository();

        if (!checkNetWork()) {
            return;
        }

        mView.showLoading();

        if(file!=null) {
            RequestBody requestFile =
                    RequestBody.create(MediaType.parse("application/otcet-stream"), file);

            MultipartBody.Part body =
                    MultipartBody.Part.createFormData("file", file.getName(), requestFile);

            uploadRepository.uploadFile(body, lifeAProvider).subscribe(new BaseObserver<Boolean>(mView) {
                @Override
                public void onNext(Boolean aBoolean) {
                    mView.upLoadFile(aBoolean);
                }
            });
        }else{
            Log.i("error==>", "error===>");
        }

    }

    /**
     * 获取照片
     */
    public void getFile(){
        uploadRepository = new UploadRepository();

        if (!checkNetWork()) {
            return;
        }

        mView.showLoading();

        uploadRepository.getFile(lifeAProvider).subscribe(new BaseObserver<Files>(mView){
            @Override
            public void onNext(Files files) {
                if(files.getCode().equals("200")) {
                    mView.onGetFile(files.getFileBean());
                }else{
                    mView.onGetFileError(files.getMessage());
                }
            }
        });

    }



    /**
     * 编辑用户信息
     */
    public void editUser(int userid,String username,String userimg,int sex,String sign,
                         LifecycleProvider<ActivityEvent> lifeProvider) {

        userRepository = new UserRepository();

        if (!checkNetWork()) {
            return;
        }

        mView.showLoading();

        userRepository.editUser(userid, username, userimg, sex, sign, lifeProvider).subscribe(new BaseObserver<User>(mView) {
            @Override
            public void onNext(User user) {
                mView.onEditUserResult(user);
            }
        });
    }
}
