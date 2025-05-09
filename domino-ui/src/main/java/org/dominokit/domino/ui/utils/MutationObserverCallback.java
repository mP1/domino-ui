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

import elemental2.dom.MutationRecord;

/**
 * A functional interface for attaching and detaching callback methods to observe DOM mutations.
 *
 * @deprecated use {@link ObserverCallback}
 */
@Deprecated
@FunctionalInterface
public interface MutationObserverCallback<T> extends ObserverCallback<T> {

  /**
   * Invoked when observed DOM mutations occur.
   *
   * @param mutationRecord The mutation record containing information about the DOM mutations.
   * @deprecated use {@link #onObserved(Object, MutationRecord)}
   */
  @Deprecated
  void onObserved(MutationRecord mutationRecord);

  default void onObserved(T target, MutationRecord mutationRecord) {
    onObserved(mutationRecord);
  }
}
