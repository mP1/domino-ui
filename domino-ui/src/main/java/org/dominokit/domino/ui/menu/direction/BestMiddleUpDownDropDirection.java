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

import static org.dominokit.domino.ui.style.SpacingCss.dui_flex_col_reverse;

import elemental2.dom.Element;

/** BestMiddleUpDownDropDirection class. */
public class BestMiddleUpDownDropDirection implements DropDirection {

  /** {@inheritDoc} */
  @Override
  public DropDirection position(DropDirectionContext context) {
    Element source = context.getSource();
    dui_flex_col_reverse.remove(source);
    cleanup(source);

    SpaceChecker spaceChecker = context.getSpaceChecker();

    if (spaceChecker.hasSpaceAbove()) {
      return TOP_MIDDLE.position(context);
    } else if (spaceChecker.hasSpaceBelow()) {
      return BOTTOM_MIDDLE.position(context);
    } else {
      return MIDDLE_SCREEN.position(context);
    }
  }

  /** {@inheritDoc} */
  @Override
  public void cleanup(Element source) {
    BOTTOM_MIDDLE.cleanSelf(source);
    TOP_MIDDLE.cleanSelf(source);
    MIDDLE_SCREEN.cleanSelf(source);
  }
}
