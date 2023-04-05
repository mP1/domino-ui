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
package org.dominokit.domino.ui.layout;

import static org.dominokit.domino.ui.layout.NavBarStyles.*;

import elemental2.dom.HTMLElement;
import org.dominokit.domino.ui.elements.HeadingElement;
import org.dominokit.domino.ui.elements.NavElement;
import org.dominokit.domino.ui.utils.*;

public class NavBar extends BaseDominoElement<HTMLElement, NavBar> {
  private NavElement root;
  private LazyChild<HeadingElement> lazyTitle;

  public static NavBar create() {
    return new NavBar();
  }

  public static NavBar create(String title) {
    return new NavBar(title);
  }

  public NavBar() {
    root = nav().addCss(dui_nav_bar);
    lazyTitle = LazyChild.of(h(4).addCss(dui_nav_title), root);
    init(this);
  }

  public NavBar(String title) {
    this();
    setTitle(title);
  }

  public NavBar setTitle(String title) {
    lazyTitle.get().setTextContent(title);
    return this;
  }

  public NavBar withTitle(ChildHandler<NavBar, HeadingElement> handler) {
    handler.apply(this, lazyTitle.get());
    return this;
  }

  public HeadingElement getTitleElement() {
    return lazyTitle.get();
  }

  public String getTitle() {
    return lazyTitle.get().getTextContent();
  }

  public NavBar appendChild(PostfixAddOn<?> postfixAddOn) {
    appendChild(postfixAddOn.addCss(dui_nav_utility).element());
    return this;
  }

  @Override
  public HTMLElement element() {
    return root.element();
  }
}
