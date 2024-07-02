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
package com.braintribe.utils.xml.parser.sax;

import java.io.PrintStream;

import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

/**
 * standard error handler implementation<br/>
 * warnings are output to a print stream (System.err if instantiated by default), errors and fatal errors are rethrown as exceptions
 * 
 * @author pit
 *
 */
public class SaxParserErrorHandler implements ErrorHandler {
	private PrintStream outStream;
	
	public SaxParserErrorHandler(PrintStream out) {
		this.outStream = out;
	}
	
	private String getParseExceptionInfo(SAXParseException spe) {
        String systemId = spe.getSystemId();
        if (systemId == null) {
            systemId = "null";
        }
        String info = "URI =" + systemId + " Line="  + spe.getLineNumber() + ": " + spe.getMessage();
        return info;
    }

	@Override
	public void warning(SAXParseException exception) throws SAXException {
		outStream.println( "Warning :" + getParseExceptionInfo(exception));		
	}

	@Override
	public void error(SAXParseException exception) throws SAXException {
		String msg = "Error :" + getParseExceptionInfo(exception);
		throw new SAXException(msg);		
	}

	@Override
	public void fatalError(SAXParseException exception) throws SAXException {
		String msg = "FatalError :" + getParseExceptionInfo(exception);
		throw new SAXException(msg);		
	}		
}
