package org.jboss.ide.eclipse.as.ui.bot.test.as51;

import org.jboss.ide.eclipse.as.reddeer.server.requirement.ServerReqState;
import org.jboss.ide.eclipse.as.reddeer.server.requirement.ServerReqType;
import org.jboss.ide.eclipse.as.reddeer.server.requirement.ServerRequirement.Server;
import org.jboss.ide.eclipse.as.ui.bot.test.template.DeleteServerTemplate;

@Server(state=ServerReqState.PRESENT, type=ServerReqType.AS, version="5.1")
public class DeleteServerAS51Server extends DeleteServerTemplate {

}
