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
package org.dominokit.domino.ui.loaders;

import static org.jboss.elemento.Elements.div;

import elemental2.dom.HTMLDivElement;
import org.dominokit.domino.ui.utils.BaseDominoElement;
import org.dominokit.domino.ui.utils.DominoElement;
import org.jboss.elemento.IsElement;

/**
 * Base loader implementation
 *
 * @param <T> the type of the loader
 * @see BaseDominoElement
 * @see IsLoader
 */
public abstract class BaseLoader<T extends BaseLoader<T>>
    extends BaseDominoElement<HTMLDivElement, T> implements IsLoader, IsElement<HTMLDivElement> {

  protected HTMLDivElement loadingText =
      DominoElement.of(div())
          .css(LoaderStyles.WAIT_ME_TEXT)
          .style("color:#555")
          .textContent("Loading...")
          .element();

  /** {@inheritDoc} */
  @Override
  public HTMLDivElement getElement() {
    return element();
  }
}
