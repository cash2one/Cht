package com.damenghai.chahuitong.module.special;

import com.damenghai.chahuitong.model.bean.Sample;
import com.damenghai.chahuitong.module.MvpView;

import java.util.List;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */
public interface SampleListMvpView extends MvpView {
    void showList(List<Sample> list);
}