package com.hs.hsj.utils;


import android.app.Activity;
import android.content.Context;
import android.widget.Toast;

import com.hs.hsj.common.ShareContent;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;

public class ShareUtils {
    private Context context;
    //private Activity activity;

    public ShareUtils(Context context,Activity activity){
        this.context = context;
        //this.activity = activity;

        UMWeb web = new UMWeb(ShareContent.url);//连接地址
        web.setTitle(ShareContent.title);//标题
        web.setDescription(ShareContent.text);//描述
        web.setThumb(new UMImage(context, ShareContent.imageurl));  //本地缩略图

        new ShareAction(activity)
                .withMedia(web)
                .setDisplayList(SHARE_MEDIA.SINA,SHARE_MEDIA.QQ,SHARE_MEDIA.WEIXIN)
                .setCallback(shareListener).open();
    }

    private UMShareListener shareListener = new UMShareListener() {
        /**
         * @descrption 分享开始的回调
         * @param platform 平台类型
         */
        @Override
        public void onStart(SHARE_MEDIA platform) {
        }
        /**
         * @descrption 分享成功的回调
         * @param platform 平台类型
         */
        @Override
        public void onResult(SHARE_MEDIA platform) {
            Toast.makeText(context,"分享成功了",Toast.LENGTH_LONG).show();
        }
        /**
         * @descrption 分享失败的回调
         * @param platform 平台类型
         * @param t 错误原因
         */
        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {
            Toast.makeText(context,"分享失败了"+t.getMessage(),Toast.LENGTH_LONG).show();
        }
        /**
         * @descrption 分享取消的回调
         * @param platform 平台类型
         */
        @Override
        public void onCancel(SHARE_MEDIA platform) {
            Toast.makeText(context,"分享取消了",Toast.LENGTH_LONG).show();
        }
    };


}
