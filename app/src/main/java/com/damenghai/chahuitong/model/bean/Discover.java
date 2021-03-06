package com.damenghai.chahuitong.model.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */
public class Discover implements Parcelable {

    private List<Ad> ad_list;

    private List<Trace> trace_list;

    private List<Flea> flea_list;

    private List<Category> class_list;

    private List<User> member_list;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(this.ad_list);
        dest.writeTypedList(this.trace_list);
        dest.writeTypedList(this.flea_list);
        dest.writeTypedList(this.class_list);
        dest.writeTypedList(this.member_list);
    }

    public Discover() {
    }

    protected Discover(Parcel in) {
        this.ad_list = in.createTypedArrayList(Ad.CREATOR);
        this.trace_list = in.createTypedArrayList(Trace.CREATOR);
        this.flea_list = in.createTypedArrayList(Flea.CREATOR);
        this.class_list = in.createTypedArrayList(Category.CREATOR);
        this.member_list = in.createTypedArrayList(User.CREATOR);
    }

    public static final Creator<Discover> CREATOR = new Creator<Discover>() {
        @Override
        public Discover createFromParcel(Parcel source) {
            return new Discover(source);
        }

        @Override
        public Discover[] newArray(int size) {
            return new Discover[size];
        }
    };

    public List<Ad> getAd_list() {
        return ad_list;
    }

    public void setAd_list(List<Ad> ad_list) {
        this.ad_list = ad_list;
    }

    public List<Trace> getTrace_list() {
        return trace_list;
    }

    public void setTrace_list(List<Trace> trace_list) {
        this.trace_list = trace_list;
    }

    public List<Flea> getFlea_list() {
        return flea_list;
    }

    public void setFlea_list(List<Flea> flea_list) {
        this.flea_list = flea_list;
    }

    public List<Category> getClass_list() {
        return class_list;
    }

    public void setClass_list(List<Category> class_list) {
        this.class_list = class_list;
    }

    public List<User> getMember_list() {
        return member_list;
    }

    public void setMember_list(List<User> member_list) {
        this.member_list = member_list;
    }
}
