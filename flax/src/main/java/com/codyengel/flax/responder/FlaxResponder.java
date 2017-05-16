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

package com.codyengel.flax.responder;

import android.support.annotation.Nullable;

import com.codyengel.flax.FlaxUtils;
import com.codyengel.flax.action.FlaxActionReceivedCallback;
import com.codyengel.flax.model.FlaxModel;
import com.codyengel.flax.store.FlaxStore;

/**
 * @author cody
 */
@Deprecated
public abstract class FlaxResponder<FM extends FlaxModel> implements FlaxActionReceivedCallback {

    public FlaxResponder() {

    }

    public FlaxActionReceivedCallback getFlaxActionReceivedCallback() {
        return this;
    }

    protected FM getModel() {
        return getModel(null);
    }

    protected FM getModel(@Nullable Integer modelKey) {
        return FlaxStore.getModel(FlaxUtils.getModelClass(getClass(), 0), modelKey);
    }

}
