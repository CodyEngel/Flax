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

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;

import com.codyengel.flax.ActionObservableBuilder;
import com.codyengel.flax.Responder;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author cody
 */
public class MainActivity extends AppCompatActivity implements MainView {

    @BindView(R.id.button) Button button;
    @BindView(R.id.text) TextView text;

    private Responder responder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        new MainRenderer(this);
        responder = new MainResponder(new ActionObservableBuilder().mapClick(button).build());
    }

    @Override
    protected void onDestroy() {
        if (responder != null) {
            responder.dispose();
        }
        super.onDestroy();
    }

    @Override
    public void setText(CharSequence text) {
        this.text.setText(text);
    }
}