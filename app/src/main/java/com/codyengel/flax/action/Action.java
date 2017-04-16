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

package com.codyengel.flax.action;

import android.annotation.SuppressLint;
import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @author cody
 */
public class Action {

    public static final int CLICK = 1;
    @IntDef({CLICK})
    @Retention(RetentionPolicy.SOURCE)
    public @interface ActionType{}

    private final int actionType;
    private final Payload payload;

    @SuppressLint("UseSparseArrays")
    public Action(@ActionType int actionType, Payload payload) {
        this.actionType = actionType;
        this.payload = payload;
    }

    @ActionType
    public final int getActionType() {
        return actionType;
    }

    public final Payload getPayload() {
        return payload;
    }

}
