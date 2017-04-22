/*
 * Copyright (c) 2017 Cody Engel
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.codyengel.simplenetworking;

import android.util.Log;

import com.codyengel.flax.Action;
import com.codyengel.flax.Responder;
import com.codyengel.simplenetworking.services.RandomUserService;

import java.util.Locale;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author cody
 */
class MainResponder extends Responder<MainModel> {

    private RandomUserService randomUserService;

    MainResponder(Observable<Action> actions) {
        super(actions);
        randomUserService = new Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://randomuser.me/")
                .build()
                .create(RandomUserService.class);
        loadRandomUser();
    }

    @Override
    protected void actionReceived(Action action) {
        switch(action.getActionType()) {
            case Action.CLICK:
                clickActionReceived(action);
                break;
            default:
                throw new UnsupportedOperationException(String.format(Locale.US, "Action type %d not supported.", action.getActionType()));
        }
    }

    @Override
    protected void errorReceived(Throwable error) {
        logError(error);
    }

    @Override
    protected void completed() {
        Log.i(getClass().getName(), "COMPLETED");
    }

    private void clickActionReceived(Action action) {
        if (action.getViewId() == R.id.fab) {
            getModel().dataIsStale();
            loadRandomUser();
        }
    }

    private void loadRandomUser() {
        randomUserService.getRandomUser("us").subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
            .subscribe(getModel()::randomUserResultReceived, this::logError);
    }

    private void logError(Throwable throwable) {
        Log.d(getClass().getName(), throwable.getMessage());
    }
}
