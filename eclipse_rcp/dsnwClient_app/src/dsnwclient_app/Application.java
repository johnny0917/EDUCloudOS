package dsnwclient_app;

import org.eclipse.equinox.app.IApplication;
import org.eclipse.equinox.app.IApplicationContext;

import com.dsideal.nwagent4rcp.agent.InstallVertIOService;

/**
 * This class controls all aspects of the application's execution
 */
public class Application implements IApplication {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.equinox.app.IApplication#start(org.eclipse.equinox.app.
	 * IApplicationContext)
	 */
	public Object start(IApplicationContext context) throws Exception {

		while (true) {
			if (InstallVertIOService.getInstance().getVertXIOState() == 0) {
				return IApplication.EXIT_OK;
			} else if (InstallVertIOService.getInstance().getVertXIOState() == 1) {
				return IApplication.EXIT_RESTART;
			}

		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.equinox.app.IApplication#stop()
	 */
	public void stop() {
		// nothing to do

	}
}
