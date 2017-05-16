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

package com.codyengel.flax.renderer;

import android.support.annotation.Nullable;

import com.codyengel.flax.FlaxUtils;
import com.codyengel.flax.model.FlaxModel;
import com.codyengel.flax.model.FlaxState;
import com.codyengel.flax.store.FlaxStore;
import com.codyengel.flax.view.FlaxView;

/**
 * @author cody
 */
public abstract class FlaxRenderer<FM extends FlaxModel, FS extends FlaxState, FV extends FlaxView>
        implements FlaxState.FlaxStateChangedCallback<FS> {

    private FV flaxView;

    @Nullable
    private Integer modelKey;

    public FlaxRenderer(FV flaxView) {
        this(flaxView, null);
    }

    public FlaxRenderer(FV flaxView, @Nullable Integer modelKey) {
        this.flaxView = flaxView;
        this.modelKey = modelKey;

        //noinspection unchecked
        getModel(modelKey).addFlaxStateChanged(this);
    }

    public void finished() {
        //noinspection unchecked
        getModel(modelKey).removeFlaxStateChanged(this);
    }

    protected FV getView() {
        return flaxView;
    }

    private FM getModel(@Nullable Integer modelKey) {
        return FlaxStore.getModel(FlaxUtils.getModelClass(getClass(), 0), modelKey);
    }

}
