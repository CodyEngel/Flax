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

package com.codyengel.flax.store;

import android.annotation.SuppressLint;

import com.codyengel.flax.model.FlaxModel;

import java.util.HashMap;
import java.util.Map;

/**
 * @author cody
 */
public class FlaxStore {

    private Map<Class, Map<Integer, FlaxModel>> modelStore;

    private FlaxStore() {
        modelStore = new HashMap<>();
        // TODO: 5/7/17 need to check if model state currently exists and auto add from there.
    }

    // Static Related Things

    private static FlaxStore flaxStore;

    public static<FM extends FlaxModel> FM getModel(Class modelClass) {
        return getModel(modelClass, 0);
    }

    // SparseArray creates different model for FlaxResponder and FlaxRenderer which breaks the idea
    // of having a single model for each FlaxResponder and FlaxRenderer.
    @SuppressLint("UseSparseArrays")
    public static<FM extends FlaxModel> FM getModel(Class modelClass, Integer modelKey) {
        if (!getModelStore().containsKey(modelClass)) {
            getModelStore().put(modelClass, new HashMap<>());
        }

        if (!getModelStore().get(modelClass).containsKey(modelKey)) {
            try {
                //noinspection unchecked
                getModelStore().get(modelClass).put(modelKey, (FM) modelClass.newInstance());
            } catch (InstantiationException | IllegalAccessException ex) {
                ex.printStackTrace();
            }
        }

        //noinspection unchecked
        return (FM) getModelStore().get(modelClass).get(modelKey);
    }

    private static Map<Class, Map<Integer, FlaxModel>> getModelStore() {
        return get().modelStore;
    }

    private static FlaxStore get() {
        // This isn't the safest singleton pattern, threading can cause issues with dual instances.
        // However forcing this to be created in the Application class is likely enough to mitigate
        // that issue.
        if (flaxStore == null) {
            flaxStore = new FlaxStore();
        }
        return flaxStore;
    }

}
