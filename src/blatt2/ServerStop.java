package blatt2;

import javax.swing.JOptionPane;

public final class ServerStop implements Runnable {

	private ThreadPooledPrimeServer server;
	
	public ServerStop(ThreadPooledPrimeServer server) {
		this.server = server;
	}
	
	@Override
	public void run() {
		JOptionPane.showMessageDialog(null, "Press OK to stop server.");

		server.exit = true;
	}

}
