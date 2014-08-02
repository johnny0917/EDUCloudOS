package com.dsideal.nwagent4rcp.agent.handlers;

import org.vertx.java.core.Handler;
import org.vertx.java.core.eventbus.Message;

import com.dsideal.nwagent4rcp.agent.InstallVertIOService;
import com.dsideal.nwagent4rcp.agent.ebus.EBusField;
import com.dsideal.nwagent4rcp.agent.ebus.EBusHandler;

@EBusHandler
public class AgentHandler {

	@EBusField("app.agent.exit")
	public Handler<Message> exitHandler = new Handler<Message>() {
		public void handle(Message message) {
			System.out.println("do exit");
			InstallVertIOService.getInstance().setVertXIOState(
					InstallVertIOService.APPLICATION_EXIT);
		}
	};

	@EBusField("connect")
	public Handler<Message> connectHandler = new Handler<Message>() {
		public void handle(Message message) {
			System.out.println("node client");
		}
	};

}
