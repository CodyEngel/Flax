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

import com.codyengel.flax.Renderer;
import com.codyengel.flax.Responder;

import butterknife.ButterKnife;

/**
 * @author cody
 */
public abstract class AbstractFlaxActivity extends AppCompatActivity {

    private Responder responder;
    private Renderer renderer;

    protected abstract int getContentViewId();
    protected abstract Renderer createRenderer();
    protected abstract Responder createResponder();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentViewId());
        ButterKnife.bind(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (renderer == null) renderer = createRenderer();
        if (responder == null) responder = createResponder();
    }

    @Override
    protected void onDestroy() {
        responder.dispose(); // prevents responders from holding references to activity
        renderer.dispose(); // prevents stale renderers from responding to model updates
        super.onDestroy();
    }
}
