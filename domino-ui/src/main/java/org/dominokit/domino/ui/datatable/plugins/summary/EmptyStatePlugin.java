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

package org.dominokit.domino.ui.datatable.plugins.summary;

import static java.util.Objects.nonNull;

import org.dominokit.domino.ui.datatable.DataTable;
import org.dominokit.domino.ui.datatable.events.TableDataUpdatedEvent;
import org.dominokit.domino.ui.datatable.plugins.DataTablePlugin;
import org.dominokit.domino.ui.elements.TDElement;
import org.dominokit.domino.ui.elements.TFootElement;
import org.dominokit.domino.ui.elements.TableRowElement;
import org.dominokit.domino.ui.icons.Icon;
import org.dominokit.domino.ui.layout.EmptyState;
import org.dominokit.domino.ui.utils.ChildHandler;

/**
 * The {@code EmptyStatePlugin} class is a plugin for a {@link DataTable} that displays an empty
 * state message when the table has no data.
 *
 * <p>This plugin provides an empty state message with an icon and a title, and it automatically
 * shows the empty state when the table has no data rows and hides it when there is data available
 * in the table.
 *
 * <p>Usage example:
 *
 * <pre>
 * DataTable<MyData> dataTable = DataTable.create();
 * dataTable.addPlugin(EmptyStatePlugin.create(Icons.warning(), "No Data Available"));
 * </pre>
 *
 * @param <T> The type of data in the DataTable.
 * @see DataTable
 * @see EmptyState
 * @see Icon
 * @see TableDataUpdatedEvent
 */
public class EmptyStatePlugin<T> implements DataTablePlugin<T> {

  private EmptyState emptyState;
  private TableRowElement rowElement = tr();
  private TDElement stateCell = td();
  private TFootElement footer;
  private DataTable<T> datatable;

  /**
   * Creates and returns a new instance of {@code EmptyStatePlugin} with the provided icon and
   * title.
   *
   * @param <T> The type of data in the DataTable.
   * @param emptyStateIcon The icon to display in the empty state.
   * @param title The title to display in the empty state.
   * @return A new {@code EmptyStatePlugin} instance.
   */
  public static <T> EmptyStatePlugin<T> create(Icon<?> emptyStateIcon, String title) {
    return new EmptyStatePlugin<>(emptyStateIcon, title);
  }

  /**
   * Creates a new instance of {@code EmptyStatePlugin} with the provided icon and title.
   *
   * @param emptyStateIcon The icon to display in the empty state.
   * @param title The title to display in the empty state.
   */
  public EmptyStatePlugin(Icon<?> emptyStateIcon, String title) {
    emptyState = EmptyState.create(emptyStateIcon).setTitle(title).addCss(dui_accent_grey);
  }

  @Override
  public void init(DataTable<T> dataTable) {
    this.datatable = dataTable;
    rowElement
        .addCss(dui_table_row)
        .appendChild(stateCell.addCss(dui_table_cell).appendChild(emptyState));
    updateColSpan(dataTable);
  }

  /**
   * Invoked when the footer is added to the DataTable.
   *
   * @param datatable The DataTable to which the footer is added.
   */
  @Override
  public void onFooterAdded(DataTable<T> datatable) {
    this.footer = datatable.footerElement();
    this.footer.appendChild(rowElement);
  }

  @Override
  public void onAfterAddTable(DataTable<T> dataTable) {
    dataTable.addTableEventListener(
        TableDataUpdatedEvent.DATA_UPDATED,
        event -> {
          TableDataUpdatedEvent tableDataUpdatedEvent = (TableDataUpdatedEvent) event;
          updateColSpan(dataTable);

          if (tableDataUpdatedEvent.getTotalCount() == 0) {
            rowElement.show();
          } else {
            rowElement.hide();
          }
        });
    this.footer.insertFirst(rowElement);
  }

  private void updateColSpan(DataTable<T> dataTable) {
    if (nonNull(dataTable)) {
      long columnsCount =
          dataTable.getTableConfig().getLeafColumns().stream().filter(c -> !c.isHidden()).count();
      stateCell.setAttribute("colspan", columnsCount);
    }
  }

  /**
   * Gets the empty state element.
   *
   * @return The empty state element.
   */
  public EmptyState getEmptyState() {
    return emptyState;
  }

  /**
   * Allows customizing the empty state using a handler.
   *
   * @param handler The handler to customize the empty state.
   * @return This {@code EmptyStatePlugin} instance for method chaining.
   */
  public EmptyStatePlugin<T> withEmptyState(ChildHandler<EmptyStatePlugin<T>, EmptyState> handler) {
    handler.apply(this, emptyState);
    return this;
  }
}
