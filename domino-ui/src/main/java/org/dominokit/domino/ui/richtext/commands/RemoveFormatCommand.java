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
package org.dominokit.domino.ui.richtext.commands;

import static org.dominokit.domino.ui.utils.Domino.*;

import elemental2.dom.HTMLElement;
import org.dominokit.domino.ui.button.Button;
import org.dominokit.domino.ui.icons.lib.Icons;
import org.dominokit.domino.ui.richtext.IsRichTextEditor;
import org.dominokit.domino.ui.richtext.RichTextCommand;
import org.dominokit.domino.ui.utils.DominoDom;

/**
 * Represents a UI command to remove formatting from the selected text in the rich text editor.
 *
 * <p>The {@code RemoveFormatCommand} extends {@link RichTextCommand} and provides users with the
 * ability to remove any inline formatting (like bold, italic, underline, color, etc.) from the
 * selected text in the rich text editor. The command is visually represented by a button with a
 * format removal icon.
 *
 * <p>Once clicked, the button will clear any formatting on the selected text, reverting it back to
 * the default style.
 *
 * <p><b>Usage Example:</b>
 *
 * <pre>{@code
 * DivElement editableDiv = DivElement.create();
 * RemoveFormatCommand removeFormatCommand = RemoveFormatCommand.create(editableDiv);
 * }</pre>
 */
public class RemoveFormatCommand extends RichTextCommand<RemoveFormatCommand> {

  private Button button;

  /**
   * Factory method to create a new instance of RemoveFormatCommand.
   *
   * @param isRichTextEditor The div element where the rich text is edited.
   * @return A new instance of RemoveFormatCommand.
   */
  public static RemoveFormatCommand create(IsRichTextEditor isRichTextEditor) {
    return new RemoveFormatCommand(isRichTextEditor);
  }

  /**
   * Constructs a new RemoveFormatCommand instance for the specified editable div element.
   *
   * @param isRichTextEditor The div element where the rich text is edited.
   */
  public RemoveFormatCommand(IsRichTextEditor isRichTextEditor) {
    super(isRichTextEditor);
    this.button =
        Button.create(Icons.format_color_marker_cancel())
            .setTooltip(getLabels().removeFormat())
            .addClickListener(evt -> execute());
    init(this);
  }

  /**
   * @dominokit-site-ignore {@inheritDoc}
   *     <p>Returns the main HTMLElement of this command, which is the button used to trigger the
   *     format removal action.
   * @return The HTMLElement of the button.
   */
  @Override
  public HTMLElement element() {
    return button.element();
  }

  /**
   * Executes the remove format command, stripping the selected text in the rich text editor of its
   * formatting.
   */
  @Override
  protected void execute() {
    getSelectedRange()
        .ifPresent(
            range -> {
              DominoDom.document.execCommand("removeFormat");
            });
  }
}
