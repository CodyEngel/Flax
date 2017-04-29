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

import com.codyengel.flax.FlaxRenderer;

/**
 * @author cody
 */
class MainRenderer extends FlaxRenderer<MainModel, MainView> {

    MainRenderer(MainView view) {
        super(view);
    }

    @Override
    protected void modelUpdated(MainModel updatedModel) {
        getView().setText(String.valueOf(updatedModel.getValue()));
    }

}