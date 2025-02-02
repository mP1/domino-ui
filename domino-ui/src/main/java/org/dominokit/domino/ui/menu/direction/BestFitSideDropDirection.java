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

import elemental2.dom.DOMRect;
import elemental2.dom.HTMLElement;

public class BestFitSideDropDirection implements DropDirection {

  private DropDirection currentPosition;

  @Override
  public void position(HTMLElement source, HTMLElement target) {

    DOMRect targetRect = target.getBoundingClientRect();
    DOMRect sourceRect = source.getBoundingClientRect();
    int innerWidth = window.innerWidth;
    int innerHeight = window.innerHeight;

    double sourceWidth = sourceRect.width;
    double sourceHeight = sourceRect.height;
    double rightSpace = innerWidth - targetRect.right - window.pageXOffset;
    double downSpace = innerHeight - targetRect.height;

    if (hasSpaceOnRightSide(sourceWidth, rightSpace)) {
      if (hasSpaceBelow(sourceHeight, downSpace)) {
        currentPosition = new RightDownDropDirection();
      } else {
        currentPosition = new RightUpDropDirection();
      }
    } else {
      if (hasSpaceBelow(sourceHeight, downSpace)) {
        currentPosition = new LeftDownDropDirection();
      } else {
        currentPosition = new LeftUpDropDirection();
      }
    }
    currentPosition.position(source, target);
  }

  private boolean hasSpaceBelow(double sourceHeight, double downSpace) {
    return downSpace > sourceHeight;
  }

  private boolean hasSpaceOnRightSide(double sourceWidth, double rightSpace) {
    return rightSpace > sourceWidth;
  }
}
