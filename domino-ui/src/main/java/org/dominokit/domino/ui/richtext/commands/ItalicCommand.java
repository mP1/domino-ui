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
 * Represents a UI command to apply the italic formatting to the selected text within a rich text
 * editor.
 *
 * <p>The {@code ItalicCommand} extends {@link RichTextCommand} and offers users the ability to make
 * selected text italic in a rich text editable div element. The command is depicted by a button
 * with an italic icon. Clicking this button will apply the italic formatting to the currently
 * selected text.
 *
 * <p><b>Usage Example:</b>
 *
 * <pre>{@code
 * DivElement editableDiv = DivElement.create();
 * ItalicCommand italicCommand = ItalicCommand.create(editableDiv);
 * }</pre>
 */
public class ItalicCommand extends RichTextCommand<ItalicCommand> {

  private Button button;

  /**
   * Factory method to create a new instance of ItalicCommand.
   *
   * @param isRichTextEditor The div element where the rich text is edited.
   * @return A new instance of ItalicCommand.
   */
  public static ItalicCommand create(IsRichTextEditor isRichTextEditor) {
    return new ItalicCommand(isRichTextEditor);
  }

  /**
   * Constructs a new ItalicCommand instance for the specified editable div element.
   *
   * @param isRichTextEditor The div element where the rich text is edited.
   */
  public ItalicCommand(IsRichTextEditor isRichTextEditor) {
    super(isRichTextEditor);
    this.button =
        Button.create(Icons.format_italic())
            .setTooltip(getLabels().italic())
            .addClickListener(evt -> execute());
    init(this);
  }

  /**
   * @dominokit-site-ignore {@inheritDoc}
   *     <p>Returns the main HTMLElement of this command, which is the button used to apply the
   *     italic formatting.
   * @return The HTMLElement of the button.
   */
  @Override
  public HTMLElement element() {
    return button.element();
  }

  /**
   * Executes the command, applying italic formatting to the selected text in the editable div
   * element.
   */
  @Override
  protected void execute() {
    getSelectedRange()
        .ifPresent(
            range -> {
              DominoDom.document.execCommand("italic");
            });
  }
}
