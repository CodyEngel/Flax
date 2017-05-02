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

import com.codyengel.flax.FlaxModel;
import com.codyengel.flax.FlaxState;

/**
 * @author cody
 */
public class MainModel extends FlaxModel<MainModel, MainModel.MainFlaxState> {
//  Public for Espresso tests, otherwise will give IllegalAccessException, feel free to make this better
//  kind stranger on GitHub.

    private Integer value = 0;

    public MainModel() {
        super();
    }

    @Override
    protected MainModel.MainFlaxState getFlaxState() {
        return new MainFlaxState(value == null ? 0 : value);
    }

    void plus() {
        value++;
        notifyModelChanged();
    }

    final class MainFlaxState implements FlaxState<MainFlaxState> {

        final Integer value;

        MainFlaxState(Integer value) {
            this.value = value;
        }

    }

}