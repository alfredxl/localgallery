package com.local.gallery;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.yancy.gallerypick.config.GalleryConfig;
import com.yancy.gallerypick.config.GalleryPick;
import com.yancy.gallerypick.inter.IHandlerCallBack;
import com.yancy.gallerypick.inter.ImageLoader;
import com.yancy.gallerypick.widget.GalleryImageView;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.addPicture).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addPicture();
            }
        });
        mTextView = (TextView) findViewById(R.id.text);
    }

    public void addPicture() {
        GalleryConfig galleryConfig = new GalleryConfig.Builder()
                .imageLoader(new GlideLoader())    // ImageLoader 加载框架（必填）
                .iHandlerCallBack(iHandlerCallBack)     // 监听接口（必填）
                .provider("com.local.gallery.provider")   // provider (必填)
                .pathList(new ArrayList<String>())                         // 记录已选的图片
                .multiSelect(true, 22)                   // 配置是否多选的同时 配置多选数量   默认：false ， 9
                .crop(true, 1, 1, 500, 500)             // 配置裁剪功能的参数，   默认裁剪比例 1:1
                .isShowCamera(true)                     // 是否现实相机按钮  默认：false
                .filePath("/Gallery/Pictures")          // 图片存放路径
                .build();
        GalleryPick.getInstance().setGalleryConfig(galleryConfig).open(this);
    }

    private static class GlideLoader implements ImageLoader {

        @Override
        public void displayImage(Activity activity, Context context, String path, GalleryImageView galleryImageView, int width, int height) {
            Glide.with(context)
                    .load(path)
                    .placeholder(R.mipmap.gallery_pick_photo)
                    .centerCrop()
                    .into(galleryImageView);
        }

        @Override
        public void clearMemoryCache() {

        }
    }

    private IHandlerCallBack iHandlerCallBack = new IHandlerCallBack() {

        @Override
        public void onStart() {

        }

        @Override
        public void onSuccess(List<String> photoList) {
            for (String path : photoList) {
                mTextView.append(path + "\n");
            }
        }

        @Override
        public void onCancel() {

        }

        @Override
        public void onFinish() {

        }

        @Override
        public void onError() {

        }
    };
}
