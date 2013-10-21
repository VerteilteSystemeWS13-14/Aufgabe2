package blatt2;

import java.net.InetSocketAddress;
import javax.swing.JOptionPane;
import blatt1.ClientEndpoint;

public final class ServerStop implements Runnable {

	private ThreadPooledPrimeServer server;
	private ClientEndpoint endpoint;
	
	public ServerStop(ThreadPooledPrimeServer server) {
		this.server = server;
		endpoint = new ClientEndpoint();
	}
	
	@Override
	public void run() {
		JOptionPane.showMessageDialog(null, "Press OK to stop server.");

		server.exit = true;
		endpoint.send(new InetSocketAddress("localhost", 4711), 1);
	}

}
