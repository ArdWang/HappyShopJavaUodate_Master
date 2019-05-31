package com.hs.user.service.impl;

/*
 *  Copyright © 2018 Radiance Instruments Ltd. All rights reserved.
 *  author ArdWang
 *  email 278161009@qq.com
 *  Created by ArdWang on 5/31/19.
 */

import com.hs.base.rx.BaseFunction;
import com.hs.user.data.net.repository.UserRepository;
import com.hs.user.model.User;
import com.hs.user.service.LoginService;
import com.trello.rxlifecycle2.LifecycleProvider;
import com.trello.rxlifecycle2.android.ActivityEvent;

import io.reactivex.Observable;

public class LoginServiceImpl implements LoginService {

    private UserRepository repository = new UserRepository();

    @Override
    public Observable<User> getUser(String phone, String pwd, String pushid, LifecycleProvider<ActivityEvent> lifeProvider) {
        return repository.getUser(phone,pwd,pushid,lifeProvider);
    }

}
