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
package org.dominokit.domino.ui.forms.suggest;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;
import static org.dominokit.domino.ui.style.DisplayCss.dui_hidden;
import static org.dominokit.domino.ui.style.SpacingCss.dui_flex_nowrap;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import org.dominokit.domino.ui.badges.Badge;
import org.dominokit.domino.ui.elements.DivElement;
import org.dominokit.domino.ui.style.BooleanCssClass;
import org.dominokit.domino.ui.utils.BaseDominoElement;
import org.dominokit.domino.ui.utils.PrimaryAddOn;

/**
 * Represents a multi-selection dropdown menu UI component, allowing users to select multiple
 * options.
 *
 * <p><b>Usage example:</b>
 *
 * <pre>
 * MultiSelect&lt;String&gt; multiSelect = MultiSelect.create("Select Items");
 * multiSelect.withValue("Option1", "Option2");
 * </pre>
 *
 * @param <V> the type of the value represented by each selectable option
 * @see AbstractSelect
 */
public class MultiSelect<V>
    extends AbstractSelect<V, List<V>, DivElement, SelectOption<V>, MultiSelect<V>> {

  private Set<SelectOption<V>> selectedOptions = new HashSet<>();
  private Badge selectionCountBadge;

  /**
   * Creates a new instance of {@link MultiSelect} without any predefined label.
   *
   * @param <V> the type of the value
   * @return a new instance of {@link MultiSelect}
   */
  public static <V> MultiSelect<V> create() {
    return new MultiSelect<>();
  }

  /**
   * Creates a new instance of {@link MultiSelect} with a predefined label.
   *
   * @param label the label for the {@link MultiSelect}
   * @param <V> the type of the value
   * @return a new instance of {@link MultiSelect}
   */
  public static <V> MultiSelect<V> create(String label) {
    return new MultiSelect<>(label);
  }

  /** Default constructor initializing the options menu for multi-selection. */
  public MultiSelect() {
    optionsMenu.setMultiSelect(true);
    setAutoCloseOnSelect(false);
    selectionCountBadge =
        Badge.create(getConfig().multiSelectSelectionCountFormatExpression(0)).addCss(dui_hidden);
    appendChild(PrimaryAddOn.of(selectionCountBadge));
  }

  /**
   * Constructor with a predefined label.
   *
   * @param label the label for the {@link MultiSelect}
   */
  public MultiSelect(String label) {
    this();
    setLabel(label);
  }

  /**
   * Converts a single value to a list containing the value.
   *
   * @param singleValue the single value
   * @return a list containing the single value
   * @see AbstractSelect#asValue(Object)
   */
  @Override
  protected List<V> asValue(V singleValue) {
    return Arrays.asList(singleValue);
  }

  /**
   * Sets the value(s) of this {@link MultiSelect}.
   *
   * @param value the values to set
   * @return the current {@link MultiSelect} instance for chaining
   */
  public MultiSelect<V> withValue(V... value) {
    return withValue(isChangeListenersPaused(), value);
  }

  /**
   * Sets the value(s) for this {@link MultiSelect} with the ability to silence the change
   * listeners.
   *
   * <p>This method allows specifying multiple values and whether the change should notify listeners
   * or not.
   *
   * <p><b>Usage example:</b>
   *
   * <pre>
   * MultiSelect&lt;String&gt; multiSelect = MultiSelect.create("Select Items");
   * multiSelect.withValue(true, "Option1", "Option2"); // silent set
   * multiSelect.withValue(false, "Option3", "Option4"); // non-silent set
   * </pre>
   *
   * @param silent if {@code true}, change listeners will not be triggered; if {@code false}, they
   *     will be triggered
   * @param value the array of values to set
   * @return the current {@link MultiSelect} instance for chaining
   * @see #withValue(Object[])
   */
  public MultiSelect<V> withValue(boolean silent, V... value) {
    return withValue(Arrays.asList(value), silent);
  }

  protected void doSetValue(List<V> value, boolean silent) {
    if (isNull(value) || value.isEmpty()) {
      clearValue(silent);
    } else {
      withPauseChangeListenersToggle(
          silent,
          field ->
              value.forEach(
                  v -> {
                    Optional<SelectOption<V>> optionByValue = findOptionByValue(v);
                    optionByValue.ifPresent(
                        vSelectOption ->
                            onOptionSelected(vSelectOption, isChangeListenersPaused()));
                  }));
    }
  }

  /**
   * Called when an option is selected.
   *
   * @param option the selected option
   */
  @Override
  protected void onOptionSelected(SelectOption<V> option, boolean silent) {
    if (nonNull(this.selectedOptions) && this.selectedOptions.contains(option)) {
      return;
    }
    withOption(option, silent);
    updateTextValue();
    option.addCss(() -> "dui-selected-option");
    fieldInput.appendChild(option);
    selectedOptions.add(option);
    getInputElement().element().focus();
    selectionCountBadge.setText(
        getConfig().multiSelectSelectionCountFormatExpression(selectedOptions.size()));
    option.onSelected();
  }

  /**
   * Adds an option to the {@link MultiSelect}.
   *
   * @param option the option to add
   * @param silent if true, change listeners will not be triggered
   * @return the current {@link MultiSelect} instance for chaining
   */
  @Override
  public MultiSelect<V> withOption(SelectOption<V> option, boolean silent) {
    List<V> oldValue = getValue();
    if (isNull(oldValue) || !oldValue.contains(option.getValue())) {
      doSetOption(option);
      if (!silent) {
        triggerChangeListeners(oldValue, getValue());
      }
    }
    autoValidate();
    return this;
  }

  /**
   * Called when an option is deselected.
   *
   * @param option the deselected option
   */
  @Override
  protected void onOptionDeselected(SelectOption<V> option, boolean silent) {
    List<V> oldValue = getValue();
    if (nonNull(oldValue) && oldValue.contains(option.getValue())) {
      selectedOptions.remove(option);
      option.remove();
      if (!silent) {
        triggerChangeListeners(oldValue, getValue());
      }
      selectionCountBadge.setText(
          getConfig().multiSelectSelectionCountFormatExpression(selectedOptions.size()));
      option.onDeselected();
    }
  }

  /**
   * Adds an option to the internal set of selected options.
   *
   * @param option the option to set
   */
  @Override
  protected void doSetOption(SelectOption<V> option) {
    if (isNull(this.selectedOptions)) {
      this.selectedOptions = new HashSet<>();
    }
    this.selectedOptions.add(option);
    if (nonNull(option)) {
      optionsMenu.select(option.getMenuItem(), true);
    }
  }

  /**
   * Retrieves a list of values representing all the selected options.
   *
   * @return a list of selected values
   * @see AbstractSelect#getValue()
   */
  @Override
  public List<V> getValue() {
    return this.selectedOptions.stream().map(Option::getValue).collect(Collectors.toList());
  }

  @Override
  protected void onTypingStart() {
    this.selectedOptions.forEach(BaseDominoElement::hide);
  }

  @Override
  protected void onTypingEnd() {
    this.selectedOptions.forEach(BaseDominoElement::show);
  }

  @Override
  protected void onOptionRemoved(SelectOption<V> option) {
    selectedOptions.remove(option);
  }

  /**
   * Sets whether the selection should wrap or not.
   *
   * <p>If {@code wrapSelection} is {@code true}, the selection will wrap. Otherwise, it will be
   * forced to stay on a single line using a no-wrap CSS class.
   *
   * @param wrapSelection {@code true} to allow wrapping, {@code false} to prevent wrapping
   * @return this {@code MultiSelect} instance for method chaining
   */
  public MultiSelect<V> setWrapSelection(boolean wrapSelection) {
    fieldInput.addCss(BooleanCssClass.of(dui_flex_nowrap, !wrapSelection));
    return this;
  }

  /**
   * Sets whether the selection count badge should be visible.
   *
   * <p>If {@code showSelectionCount} is {@code true}, the selection count badge will be shown.
   * Otherwise, it will be hidden using the appropriate CSS class.
   *
   * @param showSelectionCount {@code true} to display the selection count badge, {@code false} to
   *     hide it
   * @return this {@code MultiSelect} instance for method chaining
   */
  public MultiSelect<V> setShowSelectionCount(boolean showSelectionCount) {
    selectionCountBadge.addCss(BooleanCssClass.of(dui_hidden, !showSelectionCount));
    return this;
  }
}
