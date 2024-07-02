// ============================================================================
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.
// ============================================================================
// ============================================================================
// Copyright BRAINTRIBE TECHNOLOGY GMBH, Austria, 2002-2022
// 
// This library is free software; you can redistribute it and/or modify it under the terms of the GNU Lesser General Public
// License as published by the Free Software Foundation; either version 3 of the License, or (at your option) any later version.
// 
// This library is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more details.
// 
// You should have received a copy of the GNU Lesser General Public License along with this library; See http://www.gnu.org/licenses/.
// ============================================================================
package com.braintribe.utils.xml;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class ElementIterator extends NodeIterator<Element> {

	private static class ElementFilter implements Predicate<Node> {
		private Predicate<Element> specificFilter;
		
		public ElementFilter(Predicate<Element> specificFilter) {
			super();
			this.specificFilter = specificFilter;
		}

		@Override
		public boolean test(Node node) {
			if (node instanceof Element) {
				Element element = (Element)node;
				return specificFilter == null || specificFilter.test(element);
			}
			else return false;
		}
	}
	
	public ElementIterator(Element parent) {
		this(parent, (Predicate<Element>)null);
	}
	
	public ElementIterator(Element parent, final String... varArgTagNames) {
		this(parent, new Predicate<Element>() {
			private final List<String> tagNames = Arrays.asList(varArgTagNames);
			@Override
			public boolean test(Element element) {
				return tagNames.contains(element.getTagName());
			}
		});
	}
	
	public ElementIterator(Element parent, Predicate<Element> filter) {
		super(parent, new ElementFilter(filter));
	}
}
