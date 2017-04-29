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

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.codyengel.flax.FlaxAction;
import com.codyengel.flax.FlaxPayload;
import com.codyengel.simplenetworking.R;
import com.codyengel.simplenetworking.ui.UserModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.Subject;

/**
 * @author cody
 */
class ListAdapter extends RecyclerView.Adapter<ListAdapter.ListHolder> {

    private List<UserModel> userModels;

    private Subject<FlaxAction> clickActionSubject;

    ListAdapter() {
        userModels = new ArrayList<>();
        clickActionSubject = PublishSubject.create();
    }

    Observable<FlaxAction> getObservable() {
        return clickActionSubject;
    }

    void addUser(UserModel userModel) {
        userModels.add(userModel);
    }

    @Override
    public ListHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user, parent, false);
        return new ListHolder(view);
    }

    @Override
    public void onBindViewHolder(ListHolder holder, int position) {
        UserModel userModel = userModels.get(position);
        Glide.with(holder.itemView.getContext()).load(userModel.thumbnail).into(holder.profileImage);
        holder.titleText.setText(userModel.name);
        holder.titleSubtitle.setText(userModel.phone);
    }

    @Override
    public int getItemCount() {
        return userModels.size();
    }

    class ListHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.image_profile) ImageView profileImage;
        @BindView(R.id.text_title) TextView titleText;
        @BindView(R.id.text_subtitle) TextView titleSubtitle;

        ListHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(v -> {
                FlaxPayload userFlaxPayload = new FlaxPayload();
                userFlaxPayload.put("user_model", userModels.get(getAdapterPosition()));
                clickActionSubject.onNext(new FlaxAction(ListActivity.ACTION_LIST_CLICKED, userFlaxPayload));
            });
        }
    }
}
