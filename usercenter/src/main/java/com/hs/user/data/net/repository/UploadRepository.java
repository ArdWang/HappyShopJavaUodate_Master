package com.hs.user.data.net.repository;


import com.hs.base.data.net.http.RetrofitFactory;
import com.hs.base.data.net.repository.BaseRepository;
import com.hs.base.rx.BaseFunction;
import com.hs.base.rx.BaseFunctionBoolean;
import com.hs.user.data.net.api.UploadApi;
import com.hs.user.data.net.protocol.UploadDataReq;
import com.hs.user.data.net.protocol.UploadFileReq;
import com.trello.rxlifecycle2.LifecycleProvider;
import com.trello.rxlifecycle2.android.ActivityEvent;

import java.io.File;

import io.reactivex.Observable;
import okhttp3.MultipartBody;


public class UploadRepository extends BaseRepository{

    private UploadApi uploadApi;

    public UploadRepository(){
        uploadApi = RetrofitFactory.getInstance().create(UploadApi.class);
    }

    /**
     * 用户登录
     */
    public Observable<String> uploadData(LifecycleProvider<ActivityEvent> lifeProvider){
        return observeat(uploadApi.uploadData(new UploadDataReq()),lifeProvider)
                .flatMap(new BaseFunction<String>());
    }


    public Observable<Boolean> uploadFile(MultipartBody.Part body, LifecycleProvider<ActivityEvent> lifeProvider){
        return observeat(uploadApi.upLoadFile(body),lifeProvider)
                .flatMap(new BaseFunctionBoolean<Boolean>());
    }


}
