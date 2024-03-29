//
// $Revision: 7871 $: $Date: 2008-02-25 15:06:55 +0000 (Mon, 25 Feb 2008) $ - $Author:
// gstevenson $
//
// This file is part of Construct, a context-aware systems platform.
// Copyright (c) 2006, 2007, 2008 UCD Dublin. All rights reserved.
// 
// Construct is free software; you can redistribute it and/or modify
// it under the terms of the GNU Lesser General Public License as
// published by the Free Software Foundation; either version 2.1 of
// the License, or (at your option) any later version.
// 
// Construct is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
// GNU Lesser General Public License for more details.
// 
// You should have received a copy of the GNU Lesser General Public
// License along with Construct; if not, write to the Free Software
// Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA 02111-1307
// USA
//
// Further information about Construct can be obtained from
// http://www.construct-infrastructure.org
package org.construct_infrastructure.client;

import java.io.IOException;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.Resource;

/**
 * Tutorial 2: You can also add a Jena model to the proxy as follows...
 * 
 * @author Graeme Stevenson (graeme.stevenson@ucd.ie)
 */
public final class JavaTutorial2 {

   /**
    * Exists to defeat instantiation.
    */
   private JavaTutorial2() {
      // Exists to defeat instantiation
   }

   /**
    * A main method that constructs a jena model and uses the proxy to add it to Construct.
    * 
    * @param some_args
    *           not used.
    * @throws ServiceException
    *            if a data port cannot be found.
    * @throws IOException
    *            if an error occurs while talking to the data port, or if the local proxy is not running.
    */
   public static void main(final String[] some_args) throws ServiceException, IOException {
      // Create a new DataPortProxy object.
      final DataPortProxy proxy = new DataPortProxy();

      // Create a Jena model and add a statement
      final Model model = ModelFactory.createDefaultModel();
      final Resource person = model.createResource("http://example.com/people/bob");
      final Property name = model.createProperty("http://example.com/terms#name");
      person.addProperty(name, "Bob Smith");

      // Add the contents of the Jena model to Construct
      final boolean response = proxy.add(model);
      System.out.println(response);

      // Close the connection to the data port.
      proxy.close();
   }
}
