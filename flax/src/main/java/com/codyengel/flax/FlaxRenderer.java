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

package com.codyengel.flax;

import java.lang.reflect.ParameterizedType;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

/**
 * @author cody
 */
public abstract class FlaxRenderer<M extends FlaxModel, V extends FlaxView> {

    private Disposable disposable;
    private V view;

    public FlaxRenderer(V view) {
        this.view = view;

        disposable = getObservable().subscribe(this::modelUpdated);

    }

    protected abstract void modelUpdated(M updatedModel);

    public void dispose() {
        disposable.dispose();
    }

    protected V getView() {
        return view;
    }

    private Observable<M> getObservable() {
        //noinspection unchecked
        return getModel().getObservable();
    }

    private M getModel() {
        return FlaxStore.getModel(getModelClass());
    }

    private Class getModelClass() {
        //noinspection unchecked
        return (Class<M>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

}
