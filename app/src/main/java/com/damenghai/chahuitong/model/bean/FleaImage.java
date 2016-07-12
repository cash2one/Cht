package com.damenghai.chahuitong.model.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */
public class FleaImage implements Parcelable {

    private int upload_id;

    private String file_name;

    private String file_thumb;
    /**
     * 水印图片
     */
    private String file_wm;

    private int file_size;

    private int store_id;

    /**
     * 	默认为0，12为商品切换图片，13为商品内容图片
     */
    private int upload_type;

    private String upload_time;

    private int item_id;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.upload_id);
        dest.writeString(this.file_name);
        dest.writeString(this.file_thumb);
        dest.writeString(this.file_wm);
        dest.writeInt(this.file_size);
        dest.writeInt(this.store_id);
        dest.writeInt(this.upload_type);
        dest.writeString(this.upload_time);
        dest.writeInt(this.item_id);
    }

    public FleaImage() {
    }

    protected FleaImage(Parcel in) {
        this.upload_id = in.readInt();
        this.file_name = in.readString();
        this.file_thumb = in.readString();
        this.file_wm = in.readString();
        this.file_size = in.readInt();
        this.store_id = in.readInt();
        this.upload_type = in.readInt();
        this.upload_time = in.readString();
        this.item_id = in.readInt();
    }

    public static final Creator<FleaImage> CREATOR = new Creator<FleaImage>() {
        @Override
        public FleaImage createFromParcel(Parcel source) {
            return new FleaImage(source);
        }

        @Override
        public FleaImage[] newArray(int size) {
            return new FleaImage[size];
        }
    };

    public int getUpload_id() {
        return upload_id;
    }

    public void setUpload_id(int upload_id) {
        this.upload_id = upload_id;
    }

    public String getFile_name() {
        return file_name;
    }

    public void setFile_name(String file_name) {
        this.file_name = file_name;
    }

    public String getFile_thumb() {
        return file_thumb;
    }

    public void setFile_thumb(String file_thumb) {
        this.file_thumb = file_thumb;
    }

    public String getFile_wm() {
        return file_wm;
    }

    public void setFile_wm(String file_wm) {
        this.file_wm = file_wm;
    }

    public int getFile_size() {
        return file_size;
    }

    public void setFile_size(int file_size) {
        this.file_size = file_size;
    }

    public int getStore_id() {
        return store_id;
    }

    public void setStore_id(int store_id) {
        this.store_id = store_id;
    }

    public int getUpload_type() {
        return upload_type;
    }

    public void setUpload_type(int upload_type) {
        this.upload_type = upload_type;
    }

    public String getUpload_time() {
        return upload_time;
    }

    public void setUpload_time(String upload_time) {
        this.upload_time = upload_time;
    }

    public int getItem_id() {
        return item_id;
    }

    public void setItem_id(int item_id) {
        this.item_id = item_id;
    }
}
