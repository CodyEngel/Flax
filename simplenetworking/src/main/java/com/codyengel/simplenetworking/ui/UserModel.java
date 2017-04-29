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

package com.codyengel.simplenetworking.ui;

import com.codyengel.flax.FlaxModel;
import com.codyengel.simplenetworking.services.models.Result;

/**
 * @author cody
 */

public class UserModel extends FlaxModel<UserModel> {
    public final String name;
    public final String phone;
    public final String thumbnail;
    public final Result userResult;

    public UserModel(String name, String phone, String thumbnail, Result userResult) {
        this.name = name;
        this.phone = phone;
        this.thumbnail = thumbnail;
        this.userResult = userResult;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserModel userModel = (UserModel) o;

        if (!name.equals(userModel.name)) return false;
        if (!phone.equals(userModel.phone)) return false;
        if (!thumbnail.equals(userModel.thumbnail)) return false;
        return userResult.equals(userModel.userResult);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + phone.hashCode();
        result = 31 * result + thumbnail.hashCode();
        result = 31 * result + userResult.hashCode();
        return result;
    }
}
