package no.hvl.dat110.system.display;

import no.hvl.dat110.TODO;
import no.hvl.dat110.rpc.RPCServer;
import no.hvl.dat110.system.controller.Common;


public class DisplayDevice {
	
	public static void main(String[] args) {
		
		System.out.println("Display server starting ...");
		
		// COMPLETED
		// implement the operation of the display RPC server
		// see how this is done for the sensor RPC server in SensorDevice
		
		//=================================================================
		
		DisplayImpl displayImpl = new DisplayImpl();
		RPCServer displayServer = new RPCServer(Common.DISPLAYPORT);
		displayServer.register(1, displayImpl);
		
		displayServer.run();
		displayServer.stop();
		
		
		//=================================================================
		
		System.out.println("Display server stopping ...");
		
	}
}
