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
package org.dominokit.domino.ui.progress;

import org.dominokit.domino.ui.style.CssClass;

public interface ProgressStyles {
  CssClass dui_progress = () -> "dui-progress";
  CssClass dui_progress_bar = () -> "dui-progress-bar";
  CssClass dui_progress_text = () -> "dui-progress-text";
  CssClass dui_progress_text_blind = () -> "dui-progress-text-blind";
}
