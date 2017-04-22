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
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.codyengel.flax.ActionObservableBuilder;
import com.codyengel.flax.Renderer;
import com.codyengel.flax.Responder;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author cody
 */
public class MainActivity extends AppCompatActivity implements MainView {

    private Responder responder;
    private Renderer renderer;

    @BindView(R.id.imageView) ImageView imageView;
    @BindView(R.id.textLocation) TextView textLocation;
    @BindView(R.id.textName) TextView textName;
    @BindView(R.id.textPhone) TextView textPhone;
    @BindView(R.id.fab) View floatingActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        prepareFLAX();
    }

    // This should be called from onCreate, this is what is needed for Flax to function
    private void prepareFLAX() {
        renderer = new MainRenderer(this);
        responder = new MainResponder(new ActionObservableBuilder().mapClick(floatingActionButton).build());
    }

    @Override
    protected void onDestroy() {
        renderer.dispose(); // prevents stale renderers from responding to model updates
        responder.dispose(); // prevents responders from holding references to activity
        super.onDestroy();
    }

    @Override
    public void setName(CharSequence name) {
        textName.setText(name);
    }

    @Override
    public void setLocation(CharSequence location) {
        textLocation.setText(location);
    }

    @Override
    public void setPicture(String pictureUrl) {
        Glide.with(this).load(pictureUrl).into(imageView);
    }

    @Override
    public void setPhone(String phone) {
        textPhone.setText(phone);
    }
}
