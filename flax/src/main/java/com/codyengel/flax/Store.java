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

import java.util.HashMap;

/**
 * @author cody
 */
@SuppressWarnings("unchecked")
class Store {

    private HashMap<Class, Model> modelStore;

    private static Store instance;

    private Store() {
        modelStore = new HashMap<>();
    }

    static<M extends Model> M getModel(Class modelClass) {
        if (!get().modelStore.containsKey(modelClass)) {
            try {
                get().modelStore.put(modelClass, (M) modelClass.newInstance());
            } catch (InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return (M) get().modelStore.get(modelClass);
    }

    private static Store get() {
        if (instance == null) {
            instance = new Store();
        }
        return instance;
    }
}
