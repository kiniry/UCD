# $Id: SocketWrapper.rb 3615 2007-03-09 10:14:49Z gstevenson $

# Copyright (c) 2006, Graeme Stevenson. All rights reserved.
# This file is part of Construct, a context-aware systems platform.
# Copyright (c) 2006, 2007 UCD Dublin. All rights reserved.
#
# Construct is free software; you can redistribute it and/or modify
# it under the terms of the GNU Lesser General Public License as
# published by the Free Software Foundation; either version 2.1 of
# the License, or (at your option) any later version.
#
# Construct is distributed in the hope that it will be useful,
# but WITHOUT ANY WARRANTY; without even the implied warranty of
# MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
# GNU Lesser General Public License for more details.
#
# You should have received a copy of the GNU Lesser General Public
# License along with Construct; if not, write to the Free Software
# Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA 02111-1307
# USA
#
# Further information about Construct can be obtained from
# http://www.construct-infrastructure.org

# Client-side proxy object that can talk to a Construct discovery service, find services and
# interact with them.
# 
# Author: Graeme Stevenson (graeme.stevenson@ucd.ie)
#
require 'socket'

class SocketWrapper
  
  attr_reader :socket
  
  def initialize(socket)
   @socket = socket
  end
  
  # close the socket
  def close
      if @socket
          @socket.close
        end
  end

    # Writes a string to the socket.
    def writeToSocket(a_string)
      # send data
      @socket.puts(a_string)
      # get response
      return Message.new(socket).payload()
    end
end


