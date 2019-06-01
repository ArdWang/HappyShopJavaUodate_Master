package com.hs.user.ui.activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.clb.commonlibrary.utils.DateUtil;
import com.hs.base.common.BaseConstant;
import com.hs.base.ext.CommonExt;
import com.hs.base.ui.activity.BaseTakePhotoActivity;
import com.hs.base.utils.AppPrefsUtils;
import com.hs.base.utils.GlideUtils;
import com.hs.provider.common.ProviderConstant;
import com.hs.user.R;
import com.hs.user.model.FileBean;
import com.hs.user.model.User;
import com.hs.user.presenter.UserInfoPresenter;
import com.hs.user.presenter.view.UserInfoView;
import com.hs.user.utils.UserPrefsUtils;
import com.jph.takephoto.model.TResult;
import com.qiniu.android.http.ResponseInfo;
import com.qiniu.android.storage.UpCompletionHandler;
import com.qiniu.android.storage.UploadManager;
import org.json.JSONObject;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;


public class UserInfoActivity extends BaseTakePhotoActivity<UserInfoPresenter> implements UserInfoView{
    private RelativeLayout mUserIconView;
    private ImageView mUserIconIv;
    private EditText mUserNameEt;
    private RadioButton mGenderMaleRb,mGenderFemaleRb;
    private TextView mUserMobileTv;
    private EditText mUserSignEt;
    private TextView mRightTv;
    private String mLocalFileUrl;
    private String mRemoteFileUrl;
    private String username,usericon,mobile,sign,sex,userid;

    private File mPhotoFile;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
    }

    @Override
    protected void initView() {
        mUserIconView = findViewById(R.id.mUserIconView);
        mUserIconIv = findViewById(R.id.mUserIconIv);
        mUserNameEt = findViewById(R.id.mUserNameEt);
        mGenderMaleRb = findViewById(R.id.mGenderMaleRb);
        mGenderFemaleRb = findViewById(R.id.mGenderFemaleRb);
        mUserMobileTv = findViewById(R.id.mUserMobileTv);
        mUserSignEt = findViewById(R.id.mUserSignEt);
        mRightTv = findViewById(R.id.mRightTv);

        mUserIconView.setOnClickListener(this);
        mRightTv.setOnClickListener(this);

    }


    private void initData(){
       // mPresenter.mView = this;
        mPresenter.lifeAProvider = this;

        //获取当前的数据
        username = AppPrefsUtils.getInstance().getString(ProviderConstant.KEY_SP_USER_NAME);
        usericon = AppPrefsUtils.getInstance().getString(ProviderConstant.KEY_SP_USER_ICON);
        mobile = AppPrefsUtils.getInstance().getString(ProviderConstant.KEY_SP_USER_MOBILE);
        sex = AppPrefsUtils.getInstance().getString(ProviderConstant.KEY_SP_USER_GENDER);
        sign = AppPrefsUtils.getInstance().getString(ProviderConstant.KEY_SP_USER_SIGN);
        userid = AppPrefsUtils.getInstance().getString(ProviderConstant.KEY_SP_USER_ID);

        mRemoteFileUrl = usericon;

        if(!usericon.equals("")){
            GlideUtils.loadUrlImage(this,usericon,mUserIconIv);
        }

        mUserNameEt.setText(username);
        mUserMobileTv.setText(mobile);

        if(sex.equals("0")){
            mGenderMaleRb.setChecked(true);
        }else{
            mGenderFemaleRb.setChecked(true);
        }

        mUserSignEt.setText(sign);

    }

    @Override
    protected void onResume() {
        super.onResume();
        //每次运行获取一次照片
        //mPresenter.getFileTest();
        mPresenter.getFile();
    }

    @Override
    protected int getLayoutResID() {
        return R.layout.activity_user_info;
    }


    @Override
    public void takeSuccess(TResult result) {
        mLocalFileUrl = result.getImage().getOriginalPath();
        mPhotoFile =  getFile(getBitmapFromUrl(mLocalFileUrl,300,300));
        mPresenter.uploadFile(mPhotoFile);
    }


    public File getFile(Bitmap bitmap) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 50, baos);
        String time = DateUtil.getCurrentDate("yyyyMMddHHmmss");
        File file = new File(Environment.getExternalStorageDirectory() + "/"+time+".png");
        try {
            file.createNewFile();
            FileOutputStream fos = new FileOutputStream(file);
            InputStream is = new ByteArrayInputStream(baos.toByteArray());
            int x = 0;
            byte[] b = new byte[1024 * 100];
            while ((x = is.read(b)) != -1) {
                fos.write(b, 0, x);
            }
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return file;
    }


    private Bitmap getBitmapFromUrl(String url, double width, double height) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true; // 设置了此属性一定要记得将值设置为false
        Bitmap bitmap = BitmapFactory.decodeFile(url);
        // 防止OOM发生
        options.inJustDecodeBounds = false;
        int mWidth = bitmap.getWidth();
        int mHeight = bitmap.getHeight();
        Matrix matrix = new Matrix();
        float scaleWidth = 1;
        float scaleHeight = 1;
        // 按照固定宽高进行缩放
        if(mWidth <= mHeight) {
            scaleWidth = (float) (width/mWidth);
            scaleHeight = (float) (height/mHeight);
        } else {
            scaleWidth = (float) (height/mWidth);
            scaleHeight = (float) (width/mHeight);
        }
        // 按照固定大小对图片进行缩放
        matrix.postScale(scaleWidth, scaleHeight);
        Bitmap newBitmap = Bitmap.createBitmap(bitmap, 0, 0, mWidth, mHeight, matrix, true);
        // 用完了记得回收
        bitmap.recycle();
        return newBitmap;
    }





    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.mUserIconView) {
            showAlertView();

        } else if (i == R.id.mRightTv) {
            int cuserid = Integer.parseInt(userid);
            int csex;
            if (mGenderMaleRb.isChecked()) {
                csex = 0;
            } else {
                csex = 1;
            }
            mPresenter.editUser(cuserid, mUserNameEt.getText().toString().trim(), mRemoteFileUrl,
                    csex, mUserSignEt.getText().toString().trim(), this);

        }
    }

    /**
     * 上传图片到七牛
     * @param filePath 要上传的图片路径
     * @param token 在七牛官网上注册的token
     */
    private void uploadImageToQiniu(String filePath, String token) {
        UploadManager uploadManager = new UploadManager();
        // 设置图片名字
        String key = "icon_"+ DateUtil.getCurrentDate("yyyyMMddHHmmss");

        uploadManager.put(filePath, key, token, new UpCompletionHandler() {
            @Override
            public void complete(String key, ResponseInfo info, JSONObject res) {
                // info.error中包含了错误信息，可打印调试
                // 上传成功后将key值上传到自己的服务器
                try {
                    //String url = res.toString();
                    //Log.i("TAG","url"+res.get("hash"));
                    mRemoteFileUrl = BaseConstant.IMAGE_SERVER_ADDRESS + res.get("key");

                    GlideUtils.loadUrlImage(UserInfoActivity.this, mRemoteFileUrl,mUserIconIv);

                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }, null);
    }

//    @Override
//    public void onGetUploadTokenResult(String result) {
//        uploadImageToQiniu(mLocalFileUrl,result);
//    }

    @Override
    public void onEditUserResult(User result) {
        CommonExt.toast("修改资料成功");
        UserPrefsUtils.putUserInfo(result);
    }

    @Override
    public void upLoadFile(Boolean result) {
        if(result){
            mPresenter.getFile();
        }
    }


    @Override
    public void onGetFile(FileBean result) {
        if(result.getFileurl()!=null){
            GlideUtils.loadUrlImage(UserInfoActivity.this, result.getFileurl(),mUserIconIv);
            //存储头像
            AppPrefsUtils.getInstance().putString(ProviderConstant.KEY_SP_USER_ICON,result.getFileurl());
        }
    }

    @Override
    public void onGetFileError(String result) {
        CommonExt.toast(result);
    }

}
