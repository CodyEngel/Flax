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

import com.codyengel.flax.model.FlaxModel;
import com.codyengel.flax.model.FlaxState;
import com.codyengel.flax.store.FlaxStore;

import java.lang.reflect.ParameterizedType;

/**
 * @author cody
 */
public abstract class FlaxRenderer<FM extends FlaxModel, FS extends FlaxState>
        implements FlaxState.FlaxStateChangedCallback<FS> {

    public FlaxRenderer() {

    }

    private FM getModel() {
        return FlaxStore.getModel(getModelClass());
    }

    private Class getModelClass() {
        //noinspection unchecked
        return (Class<FM>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

}
