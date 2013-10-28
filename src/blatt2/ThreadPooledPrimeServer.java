package blatt2;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import blatt1.ServerEndpoint;

public final class ThreadPooledPrimeServer {
	
	private ExecutorService executor;
	private final ServerEndpoint endpoint;
	
	public volatile boolean exit;
	
	private static final int MAX_THREADS = 2;
	
	public ThreadPooledPrimeServer()
	{
		executor = Executors.newFixedThreadPool(MAX_THREADS);
		endpoint = new ServerEndpoint();
		exit = false;
	}
	
	void run() {
		System.out.println("ThreadPooledPrimeServer up and running...");

		new Thread(new ServerStop(this)).start();
		
		while (true)
		{
			ServerEndpoint.Request request = endpoint.blockingReceive();
			if (exit){
				break;				
			}
			PrimeCalculator calc = new PrimeCalculator(endpoint, request);
			executor.execute(calc);
		}
	
		executor.shutdown();
		System.out.println("Server stopped.");
	}

	public static void main(String[] args)
	{
		new ThreadPooledPrimeServer().run();
	}
		
}
