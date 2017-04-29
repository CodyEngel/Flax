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

import com.codyengel.flax.FlaxModel;
import com.codyengel.simplenetworking.services.models.Result;
import com.codyengel.simplenetworking.ui.UserModel;

/**
 * @author cody
 */
class UserDetailsModel extends FlaxModel<UserDetailsModel> {

    private Result randomUserResult;

    void loadRandomUser(Integer userDetailsKey) {
        randomUserResult = ((UserModel)getModel(UserModel.class, userDetailsKey)).userResult;
        notifyModelChanged();
    }

    // FlaxRenderer Related Methods
    // We'll probably want to use something like ModelRenderer, then the FlaxRenderer won't interface
    // directly with our model since it doesn't need to call void the void methods found above.

    String getTitle() {
        if (randomUserResult == null) return "";
        return randomUserResult.getName().getTitle();
    }

    String getFirstName() {
        if (randomUserResult == null) return "";
        return randomUserResult.getName().getFirst();
    }

    String getLastName() {
        if (randomUserResult == null) return "";
        return randomUserResult.getName().getLast();
    }

    String getStreet() {
        if (randomUserResult == null) return "";
        return randomUserResult.getLocation().getStreet();
    }

    String getCity() {
        if (randomUserResult == null) return "";
        return randomUserResult.getLocation().getCity();
    }

    String getState() {
        if (randomUserResult == null) return "";
        return randomUserResult.getLocation().getState();
    }

    String getPostCode() {
        if (randomUserResult == null) return "";
        return String.valueOf(randomUserResult.getLocation().getPostcode());
    }

    String getThumbnailPicture() {
        if (randomUserResult == null) return "";
        return randomUserResult.getPicture().getThumbnail();
    }

    String getLargePicture() {
        if (randomUserResult == null) return "";
        return randomUserResult.getPicture().getLarge();
    }

    String getMediumPicture() {
        if (randomUserResult == null) return "";
        return randomUserResult.getPicture().getLarge();
    }

    String getPhone() {
        if (randomUserResult == null) return "";
        return randomUserResult.getPhone();
    }

}
