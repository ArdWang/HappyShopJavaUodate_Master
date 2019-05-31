package com.hs.user.data.net.api;


import com.hs.base.data.net.protocol.BaseResp;
import com.hs.user.data.net.protocol.UploadDataReq;


import io.reactivex.Observable;
import okhttp3.MultipartBody;
import retrofit2.http.Body;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;


public interface UploadApi {
    /**
     * 获取token
     */
    @POST("common/getUploadToken")
    Observable<BaseResp<String>> uploadData(@Body UploadDataReq req);


    @Multipart
    @POST("file/ftpUpload")
    Observable<BaseResp<Boolean>> upLoadFile(@Part MultipartBody.Part file);


}
