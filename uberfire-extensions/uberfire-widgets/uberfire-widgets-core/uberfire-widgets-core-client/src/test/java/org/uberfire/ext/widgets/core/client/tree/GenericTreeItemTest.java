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

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwtmockito.GwtMock;
import com.google.gwtmockito.GwtMockitoTestRunner;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import static org.mockito.Mockito.*;

@RunWith(GwtMockitoTestRunner.class)
public class GenericTreeItemTest {

    @GwtMock
    FlowPanel container;

    ClickHandler clickHandler;

    @Test
    public void testItemEvents() {
        when(container.addDomHandler(any(ClickHandler.class),
                                     any(ClickEvent.getType().getClass()))).thenAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock aInvocation) throws Throwable {
                clickHandler = (ClickHandler) aInvocation.getArguments()[0];
                return null;
            }
        });

        final GenericTree genericTree = mock(GenericTree.class);
        final GenericTreeItem genericTreeItem = new GenericTreeItem(GenericTreeItem.Type.ITEM,
                                                                    "item", "item", null);

        genericTreeItem.setGenericTree(genericTree);

        //Check items are selected
        clickHandler.onClick(new ClickEvent() {
        });

        verify(genericTree,
               times(1)).onSelection(eq(genericTreeItem),
                                     eq(true));
        verify(genericTree,
               never()).fireStateChanged(eq(genericTreeItem),
                                         eq(GenericTreeItem.State.OPEN));
    }

    @Test
    public void testFolderEvents() {
        when(container.addDomHandler(any(ClickHandler.class),
                                     any(ClickEvent.getType().getClass()))).thenAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock aInvocation) throws Throwable {
                clickHandler = (ClickHandler) aInvocation.getArguments()[0];
                return null;
            }
        });

        final GenericTree genericTree = mock(GenericTree.class);
        final GenericTreeItem genericTreeItem = new GenericTreeItem(GenericTreeItem.Type.CONTAINER,
                                                                    "folder", "folder", null);
        genericTreeItem.setGenericTree(genericTree);

        //Check folders are selected and opened
        clickHandler.onClick(new ClickEvent() {
        });

        verify(genericTree,
               times(1)).onSelection(eq(genericTreeItem),
                                     eq(true));
        verify(genericTree,
               times(1)).fireStateChanged(eq(genericTreeItem),
                                          eq(GenericTreeItem.State.OPEN));

        //Check folders are closed when clicked again
        clickHandler.onClick(new ClickEvent() {
        });

        verify(genericTree,
               times(2)).onSelection(eq(genericTreeItem),
                                     eq(true));
        verify(genericTree,
               times(1)).fireStateChanged(eq(genericTreeItem),
                                          eq(GenericTreeItem.State.CLOSE));
    }


    @Test
    public void testAddItem() {
        final GenericTree genericTree = mock(GenericTree.class);
        final GenericTreeItem container = new GenericTreeItem(GenericTreeItem.Type.CONTAINER,
                                                                    "folder", "folder", null);
        container.addItem(GenericTreeItem.Type.ITEM,
                          "item", "item", null);

        genericTree.addItem(container);


        //verify(genericTree.getItems(),

    }



}
