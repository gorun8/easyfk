/*
 * Project:Easy Web Framework
 * Description:
 * EasyFK stands for Easy Web Framework.It's an open source product for E-Business / E-Commerce.It
 * was launched by a chinese Hezhiping(QQ:110476592) in 2015.The goal of EasyFK is to  provide a
 * foundation and starting point for reliable, secure , simple-to-use ,cost-effective ,scalable
 * and suitable-for-Chinese E-Business / E-Commerce solutions. With EasyFK, you can get started
 * right away without the huge deployment and maintenance costs of E-Business / E-Commerce systems.
 * Of course, you can customize it or use it as a framework to implement your most challenging business needs.
 * EasyFk is licensed under the Apache License Version 2.0.  You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Author:hezhiping   Email:110476592@qq.com
 */
package cn.gorun8.easyfk.base.util;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.rmi.server.RMISocketFactory;

/**
 * A <code>RMISocketFactory</code> implementation that creates <code>ServerSocket</code>s bound 
 * on a specified network interface.  
 */
public class RMIExtendedSocketFactory extends RMISocketFactory {
    
    /**
     * The network interface to bind the <code>ServerSocket</code> to. If null than bind to all interfaces.
     */
    private InetAddress hostInetAddress;
    
    /**
     * Default constructor. Bind the server sockets on all interfaces.
     */
    public RMIExtendedSocketFactory() {
        // leave hostInetAddress null
    }
    
    /**
     * Creates a new <code>RMIExtendedSocketFactory</code> which will create <code>ServerSocket</code>s 
     * bound on the specified network interface. 
     * 
     * @param inetAddress The <code>InetAddress</code> of the network interface. 
     */
    public RMIExtendedSocketFactory( InetAddress inetAddress ) {
        this.hostInetAddress = inetAddress;
    }

    /**
     * Creates a new <code>RMIExtendedSocketFactory</code> which will create <code>ServerSocket</code>s 
     * bound on the specified network interface. 
     * 
     * @param hostIpAddress The IP address of the interface to bind the server sockets to.
     * @throws UnknownHostException If an invalid IP address is provided. 
     */
    public RMIExtendedSocketFactory( String hostIpAddress ) throws UnknownHostException {
        
        // check if host length is at least equal to "0.0.0.0"
        if ( hostIpAddress != null && hostIpAddress.length() >= 7 ) {
            String[] octets = hostIpAddress.split( "\\." );
            
            if ( octets == null || octets.length != 4 ) {
                throw new UnknownHostException( "Invalid IP address: " + hostIpAddress );
            }
            
            byte[] ipAddr = new byte[4];
            for ( int i = 0; i < octets.length; i++ ) {
                try {
                    ipAddr[i] = ( byte ) Integer.parseInt( octets[i] );
                } catch ( NumberFormatException nfEx ) {
                    throw new UnknownHostException( "Invalid IP address: " + hostIpAddress );
                }
            }
            
            hostInetAddress = InetAddress.getByAddress( ipAddr );
            
        }
        
        
    }
    
    @Override
    public ServerSocket createServerSocket(int port) throws IOException {
        if ( hostInetAddress !=  null ) {
            return new ServerSocket( port, 0, hostInetAddress );
        } else {
            return new ServerSocket( port );
        }
    }

    @Override
    public Socket createSocket(String host, int port) throws IOException {
        
        return new Socket( host, port );
    }

}
