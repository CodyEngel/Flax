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

package com.codyengel.flax.flax;

import java.lang.reflect.ParameterizedType;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * @author cody
 */
public abstract class Renderer<M extends Model, V extends View> {

    private V view;

    public Renderer(V view) {
        this.view = view;

        getObservable().subscribe(new Observer<M>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(M model) {
                modelUpdated(model);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }

    protected abstract void modelUpdated(M updatedModel);

    protected V getView() {
        return view;
    }

    private Observable<M> getObservable() {
        //noinspection unchecked
        return getModel().getObservable();
    }

    private M getModel() {
        return Store.getModel(getModelClass());
    }

    private Class getModelClass() {
        //noinspection unchecked
        return (Class<M>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

}
