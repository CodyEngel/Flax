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

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * @author cody
 */
@RunWith(JUnit4.class)
public class StoreTest {

    @Test
    public void testStoreReturnsSameValueForSameKeys() throws Exception {
        Foo fooOne = Store.getModel(Foo.class, 0);
        Foo fooTwo = Store.getModel(Foo.class, 0);

        assertEquals("fooOne and fooTwo should be the same but they are different.", fooOne, fooTwo);
    }

    @Test
    public void testStoreReturnsDifferentValuesForDifferentKeys() throws Exception {
        Foo fooOne = Store.getModel(Foo.class, 0);
        Foo fooTwo = Store.getModel(Foo.class, 1);

        assertNotEquals("fooOne and fooTwo should be different but they are the same.", fooOne, fooTwo);
    }

    private static class Foo extends Model<Foo> {
        public Foo() {
            super();
        }
    }

}
