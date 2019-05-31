package com.hs.user.service.impl;

/*
 *  Copyright © 2018 Radiance Instruments Ltd. All rights reserved.
 *  author ArdWang
 *  email 278161009@qq.com
 *  Created by ArdWang on 5/31/19.
 */

import com.hs.user.data.net.repository.UserRepository;
import com.hs.user.service.ForgetPwdService;
import com.trello.rxlifecycle2.LifecycleProvider;
import com.trello.rxlifecycle2.android.ActivityEvent;

import io.reactivex.Observable;

public class ForgetPwdServiceImpl implements ForgetPwdService {

    private UserRepository repository = new UserRepository();

    @Override
    public Observable<Boolean> forgetPwd(String mobile, String verifyCode, LifecycleProvider<ActivityEvent> lifeProvider) {
        return repository.forgetPwd(mobile,verifyCode,lifeProvider);
    }
}
