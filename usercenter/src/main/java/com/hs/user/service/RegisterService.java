package com.hs.user.service;

/*
 *  Copyright © 2018 Radiance Instruments Ltd. All rights reserved.
 *  author ArdWang
 *  email 278161009@qq.com
 *  Created by ArdWang on 5/31/19.
 */

import com.trello.rxlifecycle2.LifecycleProvider;
import com.trello.rxlifecycle2.android.ActivityEvent;

import io.reactivex.Observable;

public interface RegisterService {
    Observable<Boolean> regUser(String mobile, String verifyCode, String password
            , LifecycleProvider<ActivityEvent> lifeProvider);
}
