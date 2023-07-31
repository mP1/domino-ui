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
package org.dominokit.domino.ui.themes;

import elemental2.dom.Element;
import org.dominokit.domino.ui.style.CssClass;

public class DominoThemeLight implements IsDominoTheme {

  public static final IsDominoTheme INSTANCE = new DominoThemeLight();

  private CssClass dui_theme_light = () -> "dui-colors-light";

  @Override
  public String getName() {
    return "dui-theme-light";
  }

  @Override
  public String getCategory() {
    return "dui-dark-mode";
  }

  @Override
  public void apply(Element element) {
    elementOf(element).addCss(dui_theme_light);
  }

  @Override
  public void cleanup(Element element) {
    elementOf(element).removeCss(dui_theme_light);
  }

  @Override
  public boolean isApplied(Element element) {
    return dui_theme_light.isAppliedTo(element);
  }
}
