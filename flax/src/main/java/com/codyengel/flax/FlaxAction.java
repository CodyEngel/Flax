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

/**
 * @author cody
 */
public class FlaxAction {

    public static final int SYSTEM_ACTION = -1;

    public static final int SYS_DESTROY = -7;
    public static final int SYS_STOP = -6;
    public static final int SYS_RESUME = -5;
    public static final int SYS_PAUSE = -4;
    public static final int SYS_DETACH = -3;
    public static final int SYS_ATTACH = -2;
    public static final int SYS_CREATE = -1;

    public static final int NONE = 0;

    public static final int CLICK = 1;
    public static final int ATTACHED = 2;
    public static final int DETACHED = 3;
    public static final int DRAG = 4;
    public static final int TEXT_CHANGE = 5;

    private final int actionType;
    private final int viewId;
    private final FlaxPayload flaxPayload;

    public FlaxAction(int actionType) {
        this(actionType, SYSTEM_ACTION);
    }

    public FlaxAction(int actionType, FlaxPayload flaxPayload) {
        this(actionType, SYSTEM_ACTION, flaxPayload);
    }

    public FlaxAction(int actionType, int viewId) {
        this(actionType, viewId, new FlaxPayload());
    }

    public FlaxAction(int actionType, int viewId, FlaxPayload flaxPayload) {
        this.actionType = actionType;
        this.viewId = viewId;
        this.flaxPayload = flaxPayload;
    }

    public final int getActionType() {
        return actionType;
    }

    public final int getViewId() {
        return viewId;
    }

    public final FlaxPayload getFlaxPayload() {
        return flaxPayload;
    }

}
