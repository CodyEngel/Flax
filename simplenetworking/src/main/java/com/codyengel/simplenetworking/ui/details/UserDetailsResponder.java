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

package com.codyengel.simplenetworking.ui.details;

import android.util.Log;

import com.codyengel.flax.Action;
import com.codyengel.flax.Responder;

import java.util.Locale;

import io.reactivex.Observable;

/**
 * @author cody
 */
class UserDetailsResponder extends Responder<UserDetailsModel> {


    UserDetailsResponder(Observable<Action> actions, Integer userDetailsKey) {
        super(actions);
        getModel().loadRandomUser(userDetailsKey);
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
    }

    private void logError(Throwable throwable) {
        Log.d(getClass().getName(), throwable.getMessage());
    }
}
