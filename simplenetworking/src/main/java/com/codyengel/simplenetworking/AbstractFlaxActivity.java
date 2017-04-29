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

package com.codyengel.simplenetworking;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.codyengel.flax.FlaxRenderer;
import com.codyengel.flax.FlaxResponder;

import butterknife.ButterKnife;

/**
 * @author cody
 */
public abstract class AbstractFlaxActivity extends AppCompatActivity {

    private FlaxResponder flaxResponder;
    private FlaxRenderer flaxRenderer;

    protected abstract int getContentViewId();
    protected abstract FlaxRenderer createRenderer();
    protected abstract FlaxResponder createResponder();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentViewId());
        ButterKnife.bind(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (flaxRenderer == null) flaxRenderer = createRenderer();
        if (flaxResponder == null) flaxResponder = createResponder();
    }

    @Override
    protected void onDestroy() {
        flaxResponder.dispose(); // prevents responders from holding references to activity
        flaxRenderer.dispose(); // prevents stale renderers from responding to model updates
        super.onDestroy();
    }
}
