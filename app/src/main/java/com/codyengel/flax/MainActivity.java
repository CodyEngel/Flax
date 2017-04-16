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

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;

import com.codyengel.flax.action.Action;
import com.codyengel.flax.action.Payload;
import com.jakewharton.rxbinding2.view.RxView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author cody
 */
public class MainActivity extends AppCompatActivity implements MainView {

    @BindView(R.id.button) Button button;
    @BindView(R.id.text) TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        new MainRenderer(this);
        new MainResponder(RxView.clicks(button).map(click -> new Action(Action.CLICK, new Payload())));
    }

    @Override
    public void setText(CharSequence text) {
        this.text.setText(text);
    }
}
