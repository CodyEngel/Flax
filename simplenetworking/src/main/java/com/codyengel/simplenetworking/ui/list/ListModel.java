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

import android.support.annotation.Nullable;

import com.codyengel.flax.FlaxModel;
import com.codyengel.flax.FlaxState;
import com.codyengel.simplenetworking.services.models.RandomUserResult;
import com.codyengel.simplenetworking.services.models.Result;
import com.codyengel.simplenetworking.ui.UserModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.apache.commons.lang3.text.WordUtils.capitalize;

/**
 * @author cody
 */
class ListModel extends FlaxModel<ListModel, ListModel.ListFlaxState> {

    private List<UserModel> users;
    private UserModel selectedUser = null;

    public ListModel() {
        super();
        users = new ArrayList<>();
    }

    @Override
    protected ListFlaxState getFlaxState() {
        return new ListFlaxState(users, selectedUser);
    }

    void randomUserReceived(RandomUserResult randomUserResult) {
        final Result result = randomUserResult.getResults().get(0);
        final String name = capitalize(String.format("%s %s %s",
                result.getName().getTitle(), result.getName().getFirst(), result.getName().getLast()));
        final String phone = result.getPhone().replaceFirst("-", " ");
        users.add(new UserModel(name, phone, result.getPicture().getThumbnail(), result));
        notifyModelChanged();
    }

    void userSelected(UserModel userModel) {
        selectedUser = userModel;
        if (userModel != null) putModel(selectedUser, selectedUser.hashCode());
        notifyModelChanged();
    }

    class ListFlaxState implements FlaxState<ListFlaxState> {

        final List<UserModel> users;
        final UserModel selectedUser;

        ListFlaxState(@Nullable List<UserModel> users, @Nullable UserModel selectedUser) {
            this.users = users == null ? new ArrayList<>() : Collections.unmodifiableList(users);
            this.selectedUser = selectedUser;
        }
    }
}
