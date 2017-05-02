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
import com.codyengel.flax.FlaxState;
import com.codyengel.simplenetworking.services.models.Result;
import com.codyengel.simplenetworking.ui.UserModel;

/**
 * @author cody
 */
class UserDetailsModel extends FlaxModel<UserDetailsModel, UserDetailsModel.UserDetailsFlaxState> {

    private Result randomUserResult;

    void loadRandomUser(Integer userDetailsKey) {
        randomUserResult = ((UserModel)getModel(UserModel.class, userDetailsKey)).userResult;
        notifyModelChanged();
    }

    @Override
    protected UserDetailsFlaxState getFlaxState() {
        return new UserDetailsFlaxState(randomUserResult);
    }

    // FlaxRenderer Related Methods
    // We'll probably want to use something like ModelRenderer, then the FlaxRenderer won't interface
    // directly with our model since it doesn't need to call void the void methods found above.

    class UserDetailsFlaxState implements FlaxState<UserDetailsFlaxState> {

        final String title;
        final String firstName;
        final String lastName;
        final String street;
        final String city;
        final String state;
        final String postalCode;
        final String phone;
        final String largePictureUrl;

        UserDetailsFlaxState(Result randomUserResult) {
            title = randomUserResult != null ? randomUserResult.getName().getTitle() : "";
            firstName = randomUserResult != null ? randomUserResult.getName().getFirst() : "";
            lastName = randomUserResult != null ? randomUserResult.getName().getLast() : "";
            street = randomUserResult != null ? randomUserResult.getLocation().getStreet() : "";
            state = randomUserResult != null ? randomUserResult.getLocation().getState() : "";
            city = randomUserResult != null ? randomUserResult.getLocation().getCity() : "";
            postalCode = randomUserResult != null ? String.valueOf(randomUserResult.getLocation().getPostcode()) : "";
            phone = randomUserResult != null ? randomUserResult.getPhone() : "";
            largePictureUrl = randomUserResult != null ? randomUserResult.getPicture().getLarge() : "";
        }
    }

}
