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
package org.dominokit.domino.ui.elements;

import static org.dominokit.domino.ui.utils.Domino.*;

import elemental2.dom.HTMLIFrameElement;

/**
 * Represents an HTML IFrameElement element (<iframe>) wrapper.
 *
 * <p>The HTML <iframe> element represents a nested browsing context, embedding another HTML page
 * into the current one. This class provides a convenient way to create, manipulate, and control the
 * behavior of <iframe> elements in Java-based web applications. Example usage:
 *
 * <pre>
 * HTMLIFrameElement iframeElement = ...;  // Obtain an <iframe> element from somewhere
 * IFrameElement iframe = IFrameElement.of(iframeElement);
 * </pre>
 *
 * @see <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/iframe">MDN Web Docs
 *     (iframe)</a>
 */
public class IFrameElement extends BaseElement<HTMLIFrameElement, IFrameElement> {

  /**
   * Creates a new {@link IFrameElement} instance by wrapping the provided HTML <iframe> element.
   *
   * @param e The HTML <iframe> element.
   * @return A new {@link IFrameElement} instance wrapping the provided element.
   */
  public static IFrameElement of(HTMLIFrameElement e) {
    return new IFrameElement(e);
  }

  /**
   * Constructs an {@link IFrameElement} instance by wrapping the provided HTML <iframe> element.
   *
   * @param element The HTML <iframe> element to wrap.
   */
  public IFrameElement(HTMLIFrameElement element) {
    super(element);
  }
}
