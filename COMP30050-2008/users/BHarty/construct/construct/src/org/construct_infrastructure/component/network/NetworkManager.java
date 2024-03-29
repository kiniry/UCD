//
// $Revision: 7373 $: $Date: 2008-01-24 11:20:18 +0000 (Thu, 24 Jan 2008) $ - $Author: gstevenson $
//
//  This file is part of Construct, a context-aware systems platform.
//  Copyright (c) 2006, 2007, 2008 UCD Dublin. All rights reserved.
// 
//  Construct is free software; you can redistribute it and/or modify
//  it under the terms of the GNU Lesser General Public License as
//  published by the Free Software Foundation; either version 2.1 of
//  the License, or (at your option) any later version.
// 
//  Construct is distributed in the hope that it will be useful,
//  but WITHOUT ANY WARRANTY; without even the implied warranty of
//  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
//  GNU Lesser General Public License for more details.
// 
//  You should have received a copy of the GNU Lesser General Public
//  License along with Construct; if not, write to the Free Software
//  Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA 02111-1307
//  USA
//
//  Further information about Construct can be obtained from
//  http://www.construct-infrastructure.org
package org.construct_infrastructure.component.network;

import org.construct_infrastructure.component.ConstructComponent;
import org.construct_infrastructure.component.gossiping.membership.Contact;

/**
 * The network manager is responsible for actually performing the communication
 * between processes in the construct network. It must listen for incoming
 * messages and deliver them up the stack to the gossip manager, and may also
 * provide notifications to the membership management component if it detects
 * processes joining or leaving the network.
 * 
 * @author Graham Williamson (graham.williamson@ucd.ie)
 */
public interface NetworkManager extends ConstructComponent {

   /**
    * Send a message to a particular contact.
    * 
    * @param a_contact
    *           The contact to send to.
    * @param some_data
    *           The data to send.
    */
   void sendMessage(Contact a_contact, byte[] some_data);

}
