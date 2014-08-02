package com.dsideal.nwagent4rcp.agent;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import org.eclipse.core.runtime.Platform;
import org.vertx.java.core.Vertx;
import org.vertx.java.core.VertxFactory;
import org.vertx.java.core.http.HttpServer;
import org.vertx.java.core.json.JsonArray;
import org.vertx.java.core.json.JsonObject;
import org.vertx.java.core.sockjs.SockJSServer;
import org.vertx.java.platform.Verticle;

import com.dsideal.nwagent4rcp.agent.ebus.ScanEBAnnotaion;
import com.dsideal.nwagent4rcp.agent.handlers.AgentHandler;

public class InstallVertIOService {

	private static Vertx vx = null;
	// 退出应用
	public static final int APPLICATION_EXIT = 0;
	// 重启应用
	public static final int APPLICATION_RESTART = 1;
	// 运行状态
	public static final int APPLICATION_RUNNING = 2;
	private static int current_state = -1;

	private static class InstallVertIO extends Verticle {

		static InstallVertIOService ivos = new InstallVertIOService();
	}

	private InstallVertIOService() {
		vx = VertxFactory.newVertx();
		HttpServer httpServer = vx.createHttpServer();
		SockJSServer sockJSServer = vx.createSockJSServer(httpServer);
		JsonObject config = new JsonObject().putString("prefix", "/eventbus");
		JsonArray noPermitted = new JsonArray();
		noPermitted.add(new JsonObject());

		sockJSServer.bridge(config, noPermitted, noPermitted);

		httpServer.listen(30533, "127.0.0.1");

		System.out.println("start vert.io 30533");
		installEBus();
		initNWApp();

	}

	public static InstallVertIOService getInstance() {
		return InstallVertIO.ivos;
	}

	public static Vertx getVertx() {
		return vx;
	}

	/**
	 * 初始化组件中的事件总线
	 */
	private static void installEBus() {

		ScanEBAnnotaion.scanEBus(new AgentHandler());

	}

	public synchronized int getVertXIOState() {
		return current_state;
	}

	public synchronized void setVertXIOState(int state) {
		current_state = state;
	}

	/**
	 * 启动node-webkit以及应用程序
	 */
	private void initNWApp() {
		String osname = System.getProperty("os.name");
		ProcessBuilder pb = null;
		if (osname.toLowerCase().startsWith("win")) {
			pb = new ProcessBuilder("." + File.separator + "nw.exe", ".."+File.separator+"app");
			Map<String, String> env = pb.environment();
			pb.directory(new File(Platform.getInstallLocation().getURL()
					.getFile(), "nw4win"));
		} else if (osname.toLowerCase().startsWith("mac")) {
			pb = new ProcessBuilder("." + File.separator + "nw.app"
					+ File.separator + "Contents" + File.separator + "MacOS"
					+ File.separator + "node-webkit", "../app");
			Map<String, String> env = pb.environment();
			pb.directory(new File(Platform.getInstallLocation().getURL()
					.getFile(), "nw4mac"));
		} else if (osname.toLowerCase().startsWith("win")) {
			pb = new ProcessBuilder("." + File.separator + "nw", "../app");
			Map<String, String> env = pb.environment();
			pb.directory(new File(Platform.getInstallLocation().getURL()
					.getFile(), "nw4linux"));
		} else {

		}

		pb.redirectErrorStream(true);
		File logFile = new File(Platform.getInstallLocation().getURL()
				.getFile().toString(), "startup.log");
		if (!logFile.exists()) {
			try {
				logFile.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println(pb.command());
		pb.redirectOutput(logFile);
		try {
			Process p = pb.start();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
