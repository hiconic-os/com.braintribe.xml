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
package com.braintribe.utils.xml.dom;

import java.io.StringWriter;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Node;

/**
 * This class extends {@link com.braintribe.utils.DOMTools} with utility methods that may additional dependencies (and thus cannot be part of the
 * <code>PlatformApi</code>)
 * 
 * @author michael.lafite
 */
public class DOMTools extends com.braintribe.utils.DOMTools {

	/**
	 * Pretty-prints the passed <code>xmlString</code> using JDOM.
	 * 
	 * @see DOMTools#toFormattedString(Node)
	 */
	public static String toPrettyFormattedString(Node node) {
		if (node == null) {
			throw new IllegalArgumentException("Node must not be null");
		}
		try {
			// Setup pretty print options
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			transformerFactory.setAttribute("indent-number", 4);
			Transformer transformer = transformerFactory.newTransformer();
			transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");

			// Return pretty print xml string
			StringWriter stringWriter = new StringWriter();
			transformer.transform(new DOMSource(node), new StreamResult(stringWriter));
			return stringWriter.toString();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Same as {@link #toPrettyFormattedString(Node)}.
	 */
	public static String format(Node node) {
		return toPrettyFormattedString(node);
	}

}
