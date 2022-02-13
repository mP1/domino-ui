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

import static org.dominokit.domino.ui.loaders.LoaderStyles.*;
import static org.jboss.elemento.Elements.div;

import elemental2.dom.HTMLDivElement;
import org.dominokit.domino.ui.style.Style;
import org.dominokit.domino.ui.style.Styles;
import org.dominokit.domino.ui.utils.DominoElement;
import org.jboss.elemento.IsElement;

/** Bounce loader implementation */
public class BounceLoader extends BaseLoader<BounceLoader> implements IsElement<HTMLDivElement> {

  private final HTMLDivElement progress1 =
      DominoElement.of(div())
          .css(WAIT_ME_PROGRESS_ELEM_1)
          .style("background-color:#555;")
          .element();
  private final HTMLDivElement progress2 =
      DominoElement.of(div())
          .css(WAIT_ME_PROGRESS_ELEM_2)
          .style("background-color:#555;")
          .element();
  private final HTMLDivElement progress3 =
      DominoElement.of(div())
          .css(WAIT_ME_PROGRESS_ELEM_3)
          .style("background-color:#555;")
          .element();

  private final HTMLDivElement loader =
      DominoElement.of(div())
          .css(WAIT_ME_PROGRESS)
          .css(BOUNCE)
          .add(progress1)
          .add(progress2)
          .add(progress3)
          .element();

  private final HTMLDivElement content =
      DominoElement.of(div())
          .css(WAIT_ME_CONTENT)
          .css(Styles.vertical_center)
          .add(loader)
          .add(loadingText)
          .element();

  private final HTMLDivElement element =
      DominoElement.of(div())
          .css(WAIT_ME)
          .style("background: rgba(255, 255, 255, 0.9);")
          .add(content)
          .element();

  public BounceLoader() {
    init(this);
  }

  public static BounceLoader create() {
    return new BounceLoader();
  }

  /** {@inheritDoc} */
  @Override
  public void setLoadingText(String text) {
    loadingText.textContent = text;
  }

  /** {@inheritDoc} */
  @Override
  public void setSize(String width, String height) {
    onAttached(
        mutationRecord -> {
          Style.of(loader).setWidth(width).setHeight(height);
        });
  }

  /** {@inheritDoc} */
  @Override
  public void removeLoadingText() {
    onAttached(mutationRecord -> loadingText.remove());
  }

  /** {@inheritDoc} */
  @Override
  public DominoElement<HTMLDivElement> getContentElement() {
    return DominoElement.of(content);
  }

  /** {@inheritDoc} */
  @Override
  public HTMLDivElement element() {
    return element;
  }
}
