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

import org.junit.Before;
import org.junit.Test;

import io.reactivex.Observable;
import io.reactivex.observers.TestObserver;

/**
 * @author cody
 */
public class FlaxModelTest {

    private FlaxModel flaxModel;

    @Before
    public void setUp() throws Exception {
        flaxModel = new FlaxModel() {
            @Override
            Observable getObservable() {
                return super.getObservable();
            }

            @Override
            protected void notifyModelChanged() {
                super.notifyModelChanged();
            }
        };
    }

    @Test
    public void testNotifyModelChanged() throws Exception {
        TestObserver testSubscriber = flaxModel.getObservable().test();

        flaxModel.notifyModelChanged();
        flaxModel.notifyModelChanged();

        // 3 values because the constructor of FlaxModel will call notifyModelChanged
        testSubscriber.assertValueCount(3);
        testSubscriber.assertValues(flaxModel, flaxModel, flaxModel);
    }

}