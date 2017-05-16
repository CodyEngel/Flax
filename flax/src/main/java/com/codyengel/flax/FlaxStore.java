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

package com.codyengel.flax;

import android.annotation.SuppressLint;

import java.util.HashMap;
import java.util.Map;

/**
 * @author cody
 */
@Deprecated
@SuppressLint("UseSparseArrays")
class FlaxStore {

    private Map<Class, Map<Integer, FlaxModel>> modelStore;

    private static FlaxStore instance;

    private FlaxStore() {
        modelStore = new HashMap<>();
    }

    static<M extends FlaxModel> M getModel(Class modelClass) {
        return getModel(modelClass, 0);
    }

    static<M extends FlaxModel> M getModel(Class modelClass, Integer modelKey) {
        if (!getModelStore().containsKey(modelClass)) {
            // SparseArray creates different model for FlaxResponder and FlaxRenderer which breaks the idea
            // of having a single model for each FlaxResponder and FlaxRenderer.
            getModelStore().put(modelClass, new HashMap<>());
        }

        if (!getModelStore().get(modelClass).containsKey(modelKey)) {
            try {
                //noinspection unchecked
                getModelStore().get(modelClass).put(modelKey, (M) modelClass.newInstance());
            } catch (InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        //noinspection unchecked
        return (M) getModelStore().get(modelClass).get(modelKey);
    }

    static<M extends FlaxModel> void putModel(M model, Integer modelKey) {
        Class modelClass = model.getClass();

        if (!getModelStore().containsKey(modelClass)) {
            getModelStore().put(modelClass, new HashMap<>());
        }

        getModelStore().get(modelClass).put(modelKey, model);
    }

    private static Map<Class, Map<Integer, FlaxModel>> getModelStore() {
        return get().modelStore;
    }

    private static FlaxStore get() {
        if (instance == null) {
            instance = new FlaxStore();
        }
        return instance;
    }
}
