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

package com.codyengel.flax.model;

import com.codyengel.flax.model.FlaxState.FlaxStateChangedCallback;

import java.util.ArrayList;
import java.util.List;

/**
 * @author cody
 */
public abstract class FlaxModel<FS extends FlaxState> {

    private FS flaxState;

    private List<FlaxStateChangedCallback<FS>> flaxStateChangedCallbacks;

    public FlaxModel() {
        flaxStateChangedCallbacks = new ArrayList<>();
    }

    public void addFlaxStateChanged(FlaxStateChangedCallback<FS> flaxStateChangedCallback) {
        flaxStateChangedCallbacks.add(flaxStateChangedCallback);
        flaxStateChangedCallback.flaxStateChanged(flaxState);
    }

    public void removeFlaxStateChanged(FlaxStateChangedCallback<FS> flaxStateChangedCallback) {
        flaxStateChangedCallbacks.remove(flaxStateChangedCallback);
    }

    protected void updateFlaxState(FS flaxState) {
        this.flaxState = flaxState;
        notifyStateChanged();
    }

    private void notifyStateChanged() {
        for (FlaxStateChangedCallback<FS> flaxStateChangedCallback : flaxStateChangedCallbacks) {
            flaxStateChangedCallback.flaxStateChanged(flaxState);
        }
    }

}
