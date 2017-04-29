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

package com.codyengel.helloflax;

import android.util.Log;

import com.codyengel.flax.FlaxAction;
import com.codyengel.flax.FlaxResponder;

import java.util.Locale;

import io.reactivex.Observable;

/**
 * @author cody
 */
class MainResponder extends FlaxResponder<MainModel> {

    MainResponder(Observable<FlaxAction> actions) {
        super(actions);
    }

    @Override
    protected void actionReceived(FlaxAction flaxAction) {
        switch (flaxAction.getActionType()) {
            case FlaxAction.CLICK:
                if (flaxAction.getViewId() == R.id.button) {
                    getModel().plus();
                }
                break;
            default:
                throw new UnsupportedOperationException(String.format(Locale.US, "FlaxAction Type %s Not Supported", flaxAction.getActionType()));
        }
    }

    @Override
    protected void errorReceived(Throwable error) {
        Log.e(getClass().getName(), error.getMessage());
    }

    @Override
    protected void completed() {
        Log.i(getClass().getName(), "Completed");
    }

}