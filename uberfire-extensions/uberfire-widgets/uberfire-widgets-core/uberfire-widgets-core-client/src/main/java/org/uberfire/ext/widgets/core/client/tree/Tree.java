/*
 * Copyright 2017 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.uberfire.ext.widgets.core.client.tree;

import java.util.Iterator;

public class Tree extends GenericTree  {

    public Tree() {
        super();
    }

    public TreeItem addItem(final GenericTreeItem.Type type,
                            final String value) {
        final TreeItem item = new TreeItem(type,
                                           value);
        return (TreeItem) addItem(item);
    }

    public Iterable<TreeItem> getItems() {
        return new Iterable<TreeItem>() {
            @Override
            public Iterator<TreeItem> iterator() {
                return new TreeItem.TreeItemIterator(container);
            }
        };
    }


}
