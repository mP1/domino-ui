/*
 * Copyright © 2019 Dominokit
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.dominokit.domino.ui.menu.direction;

import static elemental2.dom.DomGlobal.window;
import static org.dominokit.domino.ui.style.Unit.px;

import elemental2.dom.DOMRect;
import elemental2.dom.HTMLElement;

public class BottomMiddleDropDirection implements DropDirection {
  @Override
  public void position(HTMLElement source, HTMLElement target) {
    DOMRect targetRect = target.getBoundingClientRect();
    DOMRect sourceRect = source.getBoundingClientRect();
    double delta = 0;
    double availableSpace = targetRect.left + targetRect.width;
    if (availableSpace < sourceRect.width) {
      delta = sourceRect.width - availableSpace;
    }

    source.style.setProperty(
        "top", px.of((targetRect.top + window.pageYOffset) + targetRect.height + 1));
    source.style.setProperty(
        "left",
        px.of(
            targetRect.left
                + window.pageXOffset
                - ((sourceRect.width - targetRect.width) / 2)
                + delta));
  }
}
