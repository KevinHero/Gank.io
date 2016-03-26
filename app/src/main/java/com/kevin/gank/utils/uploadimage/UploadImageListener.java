package com.kevin.gank.utils.uploadimage;

/**
 * Created by creasy on 2015/7/9.
 */
public interface UploadImageListener {



    /**
     * 上传中
     */
    void onImageUploading();

    /**
     * 上传失败
     * @param s
     */
    void onImageUploadFailed(String s);

    /**
     * 上传成功
     * @param s 返回图片url
     */
    void onImageUploadSuccess(String s);
}
