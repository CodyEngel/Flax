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

import android.support.annotation.CallSuper;
import android.support.annotation.Nullable;

import java.lang.reflect.ParameterizedType;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * @author cody
 */
public abstract class FlaxResponder<M extends FlaxModel> {

    private Disposable disposable;

    public FlaxResponder(Observable<FlaxAction> actions) {
        actions.subscribe(new Observer<FlaxAction>() {

            private Disposable disposable;

            @Override
            public void onSubscribe(Disposable disposable) {
                this.disposable = disposable;
                disposableCreated(disposable);
            }

            @Override
            public void onNext(FlaxAction flaxAction) {
                actionReceived(flaxAction);
            }

            @Override
            public void onError(Throwable error) {
                disposable.dispose();
                errorReceived(error);
            }

            @Override
            public void onComplete() {
                disposable.dispose();
                completed();
            }
        });
    }

    protected abstract void actionReceived(FlaxAction flaxAction);

    protected abstract void errorReceived(Throwable error);

    protected abstract void completed();

    public final void dispose() {
        disposable.dispose();
    }

    protected M getModel() {
        return getModel(null);
    }

    protected M getModel(@Nullable Integer modelKey) {
        if (modelKey == null) return FlaxStore.getModel(getModelClass());
        return FlaxStore.getModel(getModelClass(), modelKey);
    }

    @CallSuper
    protected void disposableCreated(Disposable disposable) {
        this.disposable = disposable;
    }

    private Class getModelClass() {
        //noinspection unchecked
        return (Class<M>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

}
