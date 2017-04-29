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

package com.codyengel.simplenetworking.ui.list;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.codyengel.flax.Action;
import com.codyengel.flax.ActionObservableBuilder;
import com.codyengel.flax.Renderer;
import com.codyengel.flax.Responder;
import com.codyengel.simplenetworking.AbstractFlaxActivity;
import com.codyengel.simplenetworking.R;
import com.codyengel.simplenetworking.ui.UserModel;
import com.codyengel.simplenetworking.ui.details.UserDetailsActivity;

import butterknife.BindView;
import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;

/**
 * @author cody
 */
public class ListActivity extends AbstractFlaxActivity implements ListView {

    public static final int ACTION_START_ACTIVITY = 9001;
    public static final int ACTION_LIST_CLICKED = 9000;

    private PublishSubject<Action> activitySubject;

    private ListAdapter listAdapter;

    @BindView(R.id.fab) View fab;
    @BindView(R.id.list_user) RecyclerView listUser;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activitySubject = PublishSubject.create();

        listAdapter = new ListAdapter();
        listUser.setLayoutManager(new LinearLayoutManager(this));
        listUser.setAdapter(listAdapter);
    }

    @Override
    public void startActivity(Intent intent) {
        activitySubject.onNext(new Action(ACTION_START_ACTIVITY));
        super.startActivity(intent);
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_list;
    }

    @Override
    protected Renderer createRenderer() {
        return new ListRenderer(this);
    }

    @Override
    protected Responder createResponder() {
        Observable<Action> actionObservable = new ActionObservableBuilder().mapClick(fab).build()
                .mergeWith(listAdapter.getObservable())
                .mergeWith(activitySubject);
        return new ListResponder(actionObservable);
    }

    @Override
    public void addUser(UserModel userModel) {
        listAdapter.addUser(userModel);
        listAdapter.notifyDataSetChanged();
    }

    @Override
    public void scrollToPosition(int position) {
        listUser.scrollToPosition(position);
    }

    @Override
    public void navigateToUserProfile(Integer userDetailsKey) {
        Intent userProfileIntent = new Intent(this, UserDetailsActivity.class);
        userProfileIntent.putExtra("user_details_key", userDetailsKey);
        startActivity(userProfileIntent);
    }
}
