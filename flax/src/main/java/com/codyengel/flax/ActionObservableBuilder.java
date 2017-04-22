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

import android.view.View;
import android.widget.TextView;

import com.jakewharton.rxbinding2.view.RxView;
import com.jakewharton.rxbinding2.widget.RxTextView;

import io.reactivex.Observable;

/**
 * @author cody
 */
public class ActionObservableBuilder {

    private Observable<Action> actionObservable;

    public ActionObservableBuilder() {
        actionObservable = Observable.just(new Action(Action.NONE));
    }

    /**
     * @return an {@link Action} {@link Observable}
     */
    public Observable<Action> build() {
        return actionObservable.filter(action -> action.getActionType() != Action.NONE);
    }

    /**
     * Maps a click event event to an action.
     * @param view the view to observe click events on
     * @return an {@link ActionObservableBuilder}, note don't forget to call {{@link #build()}}
     */
    public ActionObservableBuilder mapClick(View view) {
        actionObservable = actionObservable.mergeWith(RxView.clicks(view).map(click -> new Action(Action.CLICK, view.getId())));
        return this;
    }

    /**
     * Maps an attached event to an action.
     * @param view the view to observe attached events on
     * @return an {@link ActionObservableBuilder}, note don't forget to call {@link #build()}
     */
    public ActionObservableBuilder mapAttached(View view) {
        actionObservable = actionObservable.mergeWith(RxView.attaches(view).map(attach -> new Action(Action.ATTACHED, view.getId())));
        return this;
    }

    /**
     * Maps a deattached event to an action.
     * @param view the view to observe deattached events on
     * @return an {@link ActionObservableBuilder}, note don't forget to call {@link #build()}
     */
    public ActionObservableBuilder mapDetached(View view) {
        actionObservable = actionObservable.mergeWith(RxView.detaches(view).map(detach -> new Action(Action.DETACHED, view.getId())));
        return this;
    }

    /**
     * Maps a dragged even to an action.
     * @param view the view to observe dragged events on
     * @return an {@link ActionObservableBuilder}, note don't forget to call {@link #build()}
     */
    public ActionObservableBuilder mapDrag(View view) {
        actionObservable = actionObservable.mergeWith(RxView.drags(view).map(drag -> {
            Payload payload = new Payload();
            payload.put("drag_event", drag);
            return new Action(Action.DRAG, view.getId(), payload);
        }));
        return this;
    }

    /**
     * Maps a text change event to an action.
     * @param textView the text view to observe text change events on
     * @return an {@link ActionObservableBuilder}, note don't forget to call {@link #build()}
     */
    public ActionObservableBuilder mapTextChange(TextView textView) {
        actionObservable = actionObservable.mergeWith(RxTextView.textChanges(textView).map(text -> {
            Payload payload = new Payload();
            payload.put("text", text);
            return new Action(Action.TEXT_CHANGE, textView.getId(), payload);
        }));
        return this;
    }

}
