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

package com.codyengel.simplenetworking.ui.list;

import android.util.Log;

import com.codyengel.flax.Action;
import com.codyengel.flax.Responder;
import com.codyengel.simplenetworking.R;
import com.codyengel.simplenetworking.services.RandomUserService;
import com.codyengel.simplenetworking.ui.UserModel;

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
class ListResponder extends Responder<ListModel> {

    private RandomUserService randomUserService;

    ListResponder(Observable<Action> actions) {
        super(actions);
        randomUserService = new Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://randomuser.me/")
                .build()
                .create(RandomUserService.class);
    }

    @Override
    protected void actionReceived(Action action) {
        switch(action.getActionType()) {
            case Action.CLICK:
                clickActionReceived(action);
                break;
            case ListActivity.ACTION_LIST_CLICKED:
                listClickActionReceived(action);
                break;
            case ListActivity.ACTION_START_ACTIVITY:
                startActivityReceived();
                break;
            default:
                throw new UnsupportedOperationException(String.format(Locale.US, "Action type %d not supported.", action.getActionType()));
        }
    }

    @Override
    protected void errorReceived(Throwable error) {}

    @Override
    protected void completed() {}

    private void listClickActionReceived(Action action) {
        UserModel userModel = (UserModel) action.getPayload().get("user_model");
        getModel().userSelected(userModel);
    }

    private void startActivityReceived() {
        getModel().userSelected(null);
    }

    private void clickActionReceived(Action action) {
        if (action.getViewId() == R.id.fab) {
            loadRandomUser();
        }
    }

    private void loadRandomUser() {
        randomUserService.getRandomUser("us").subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(getModel()::randomUserReceived, this::logError);
    }

    private void logError(Throwable throwable) {
        Log.d(getClass().getName(), throwable.getMessage());
    }
}
