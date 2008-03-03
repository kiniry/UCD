/**
 * 
 */
package org.construct_infrastructure.main;


import java.io.File;

import org.construct_infrastructure.bonjourproxy.BonjourProxy;
import org.construct_infrastructure.loader.ComponentCreationException;
import org.construct_infrastructure.loader.ComponentLoader;
import org.construct_infrastructure.loader.PropertyFileException;

import com.apple.dnssd.DNSSDException;

/**
 * @author Graeme Stevenson
 */
public class ProxyAndConstructNoGuiMain {

   /**
    * @param args
    */
   public static void main(String[] args) throws DNSSDException, ComponentCreationException, PropertyFileException{
      // TODO Auto-generated method stub
      new BonjourProxy();
      new ComponentLoader(new File("construct.properties"));
   }

}
