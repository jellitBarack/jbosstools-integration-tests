package org.jboss.tools.cdi.bot.test.beans.dialog.cdi11;

import org.eclipse.reddeer.eclipse.ui.perspectives.JavaEEPerspective;
import org.eclipse.reddeer.junit.annotation.RequirementRestriction;
import org.eclipse.reddeer.junit.requirement.matcher.RequirementMatcher;
import org.eclipse.reddeer.requirements.openperspective.OpenPerspectiveRequirement.OpenPerspective;
import org.eclipse.reddeer.requirements.server.ServerRequirementState;
import org.jboss.ide.eclipse.as.reddeer.server.family.ServerMatcher;
import org.jboss.ide.eclipse.as.reddeer.server.requirement.ServerRequirement.JBossServer;
import org.jboss.tools.cdi.bot.test.beans.dialog.template.AssignableDialogFilterTemplate;
import org.junit.Before;

@JBossServer(state=ServerRequirementState.PRESENT, cleanup=false)
@OpenPerspective(JavaEEPerspective.class)
public class AssignableDialogFilterTestCDI11 extends AssignableDialogFilterTemplate{

	@RequirementRestriction
	public static RequirementMatcher getRestrictionMatcher() {
	  return new RequirementMatcher(JBossServer.class, "family", ServerMatcher.WildFly());
	}
		
	@Before
	public void prepareBeansXML() {
		prepareBeanXml("all", true);
	}

}
