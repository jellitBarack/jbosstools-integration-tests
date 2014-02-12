package org.jboss.ide.eclipse.as.ui.bot.test.wildfly8;

import org.jboss.ide.eclipse.as.reddeer.server.requirement.ServerReqOperator;
import org.jboss.ide.eclipse.as.reddeer.server.requirement.ServerReqState;
import org.jboss.ide.eclipse.as.reddeer.server.requirement.ServerReqType;
import org.jboss.ide.eclipse.as.reddeer.server.requirement.ServerRequirement.Server;
import org.jboss.ide.eclipse.as.ui.bot.test.template.OperateServerTemplate;

@Server(state=ServerReqState.STOPPED, type=ServerReqType.WILDFLY, version="8.0", operator=ServerReqOperator.GREATER_OR_EQUAL)
public class OperateWildfly8Server extends OperateServerTemplate {

	@Override
	public String getWelcomePageText() {
		return "Welcome to WildFly 8";
	}

}
