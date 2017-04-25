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

import com.google.gwt.user.client.ui.ComplexPanel;
import com.google.gwt.user.client.ui.IsWidget;
import org.gwtbootstrap3.client.ui.Icon;
import org.gwtbootstrap3.client.ui.constants.IconType;

public class TreeItem extends GenericTreeItem {

    public TreeItem(Type type,
                    String value) {
        super(type,
              value,
              value,
              createIcon(type));
    }

    protected static class TreeItemIterator implements Iterator<TreeItem> {

        private final ComplexPanel container;
        private int index = 0;

        TreeItemIterator(ComplexPanel container) {
            this.container = container;
        }

        @Override
        public boolean hasNext() {
            if (container == null) {
                return false;
            }
            return index < container.getWidgetCount();
        }

        @Override
        public TreeItem next() {
            return (TreeItem) container.getWidget(index++);
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    public TreeItem getChild(final int i) {
        if (i + 1 > content.getWidgetCount()) {
            return null;
        }
        return (TreeItem) content.getWidget(i);
    }

    private static final Icon createIcon(final Type type) {
        IconType iconType = IconType.FOLDER;
        switch (type) {
            case ITEM:
                iconType = IconType.FILE_O;
                break;
            case CONTAINER:
                iconType = IconType.FOLDER;
                break;
            case FOLDER:
                iconType = IconType.FOLDER;
                break;
            case ROOT:
                iconType = IconType.FOLDER;
                break;
        }
        return new Icon(iconType);
    }

    public TreeItem addItem(final Type type,
                            final String value) {
        return (TreeItem)
                super.addItem(type,
                              value,
                              value,
                              createIcon(type));
    }



    protected TreeItem makeChild(final Type type,
                                        final String value) {
        return (TreeItem) new GenericTreeItem(type,
                                              value,
                                              value,
                                              createIcon(type));
    }

    @Override
    protected void onOpenState() {
        super.onOpenState();
        getIcon().setType(IconType.FOLDER_OPEN);
    }

    @Override
    protected void onCloseState() {
        super.onCloseState();
        getIcon().setType(IconType.FOLDER);
    }

    private Icon getIcon() {
        return (Icon) getIconWidget();
    }


    //@Override
    public Iterable<TreeItem> getChildren() {
        return new Iterable<TreeItem>() {
            @Override
            public Iterator<TreeItem> iterator() {
                return new TreeItemIterator(content);
            }
        };
    }
}
