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

import com.codyengel.flax.FlaxRenderer;

import static org.apache.commons.lang3.text.WordUtils.capitalize;

/**
 * @author cody
 */
class UserDetailsRenderer extends FlaxRenderer<UserDetailsModel, UserDetailsView, UserDetailsModel.UserDetailsFlaxState> {

    UserDetailsRenderer(UserDetailsView view) {
        super(view);
    }

    @Override
    protected void modelUpdated(UserDetailsModel.UserDetailsFlaxState updatedFlaxState) {
        setName(updatedFlaxState.firstName, updatedFlaxState.lastName, updatedFlaxState.title);
        setLocation(updatedFlaxState.street, updatedFlaxState.city, updatedFlaxState.state, updatedFlaxState.postalCode);
        setPhone(updatedFlaxState.phone);
        setPicture(updatedFlaxState.largePictureUrl);
    }

    private void setName(String firstName, String lastName, String title) {
        String name = String.format("%s %s %s",
                capitalize(title), capitalize(firstName), capitalize(lastName));
        getView().setName(name);
    }

    private void setLocation(String street, String city, String state, String postCode) {
        String location = String.format("%s\n%s, %s, %s",
                capitalize(street), capitalize(city), capitalize(state), postCode);
        getView().setLocation(location);
    }

    private void setPicture(String picture) {
        getView().setPicture(picture);
    }

    private void setPhone(String phone) {
        String formattedPhone = phone.replaceFirst("-", " ");
        getView().setPhone(formattedPhone);
    }
}
