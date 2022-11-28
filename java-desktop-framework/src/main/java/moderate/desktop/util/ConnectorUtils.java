package moderate.desktop.util;

import lombok.extern.slf4j.Slf4j;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;

@Slf4j
public class ConnectorUtils {

	private static void bindPort(String host, int port) throws Exception {
		Socket s = new Socket();
		s.bind(new InetSocketAddress(host, port));
		s.close();
	}

	public static boolean isPortAvailable(int port) {
		try {
			bindPort("0.0.0.0", port);
			bindPort(InetAddress.getLocalHost().getHostAddress(), port);
			return true;
		} catch (Exception e) {
			log.info("Port can't use!", "" + port);
			return false;
		}
	}

	public static int findAvailablePort(int start, int end) {
		int s = start < end ? start : end;
		int e = start > end ? start : end;

		for (int i = s; i < e; i++) {
			if (isPortAvailable(i))
				return i;
		}
		return -1;
	}

}
