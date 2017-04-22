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

import com.codyengel.flax.Model;
import com.codyengel.simplenetworking.services.models.RandomUserResult;
import com.codyengel.simplenetworking.services.models.Result;

/**
 * @author cody
 */
class MainModel extends Model<MainModel> {

    private Result randomUserResult;
    private boolean isDataStale = true;

    // Responder Related Methods
    // We'll probably want to use something like ModelResponder, then the Responder won't interface
    // directly with our model since it doesn't need the actual values that are being set.
    void randomUserResultReceived(RandomUserResult randomUserResult) {
        if (isDataStale) {
            isDataStale = false;
            this.randomUserResult = randomUserResult.getResults().get(0);
            notifyModelChanged();
        }
    }

    void dataIsStale() {
        isDataStale = true;
    }

    // Renderer Related Methods
    // We'll probably want to use something like ModelRenderer, then the Renderer won't interface
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
