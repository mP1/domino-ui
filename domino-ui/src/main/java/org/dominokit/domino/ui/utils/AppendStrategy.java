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
package org.dominokit.domino.ui.utils;

import elemental2.dom.HTMLElement;

/** The strategy for appending the menu to the target element */
@FunctionalInterface
public interface AppendStrategy {
  /**
   * Will be called to append the menu to the target element
   *
   * @param target the target element
   * @param menu the menu element
   */
  void onAppend(HTMLElement target, HTMLElement menu);

  /**
   * {@code FIRST} strategy means that the menu will be added at the first index of the target
   * element
   */
  AppendStrategy FIRST = (target, menu) -> DominoElement.of(target).insertFirst(menu);
  /**
   * {@code LAST} strategy means that the menu will be added at the last index of the target element
   */
  AppendStrategy LAST = (target, menu) -> DominoElement.of(target).appendChild(menu);
}
