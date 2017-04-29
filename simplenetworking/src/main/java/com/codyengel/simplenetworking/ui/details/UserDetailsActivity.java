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

package com.codyengel.simplenetworking.ui.details;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.codyengel.flax.ActionObservableBuilder;
import com.codyengel.flax.Renderer;
import com.codyengel.flax.Responder;
import com.codyengel.simplenetworking.AbstractFlaxActivity;
import com.codyengel.simplenetworking.R;

import butterknife.BindView;

/**
 * @author cody
 */
public class UserDetailsActivity extends AbstractFlaxActivity implements UserDetailsView {

    @BindView(R.id.imageView) ImageView imageView;
    @BindView(R.id.textLocation) TextView textLocation;
    @BindView(R.id.textName) TextView textName;
    @BindView(R.id.textPhone) TextView textPhone;
    @BindView(R.id.fab) View floatingActionButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_user_details;
    }

    @Override
    protected Renderer createRenderer() {
        return new UserDetailsRenderer(this);
    }

    @Override
    protected Responder createResponder() {
        Integer userDetailsKey = getIntent().getIntExtra("user_details_key", 0);
        return new UserDetailsResponder(new ActionObservableBuilder().build(), userDetailsKey);
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
