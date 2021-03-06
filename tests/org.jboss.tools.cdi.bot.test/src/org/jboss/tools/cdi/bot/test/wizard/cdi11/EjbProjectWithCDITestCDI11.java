package org.jboss.tools.cdi.bot.test.wizard.cdi11;

import org.eclipse.reddeer.eclipse.jst.ejb.ui.project.facet.EjbProjectFirstPage;
import org.eclipse.reddeer.eclipse.jst.ejb.ui.project.facet.EjbProjectWizard;
import org.eclipse.reddeer.eclipse.ui.perspectives.JavaEEPerspective;
import org.eclipse.reddeer.junit.annotation.RequirementRestriction;
import org.eclipse.reddeer.junit.requirement.matcher.RequirementMatcher;
import org.eclipse.reddeer.requirements.openperspective.OpenPerspectiveRequirement.OpenPerspective;
import org.eclipse.reddeer.requirements.server.ServerRequirementState;
import org.jboss.ide.eclipse.as.reddeer.server.family.ServerMatcher;
import org.jboss.ide.eclipse.as.reddeer.server.requirement.ServerRequirement.JBossServer;
import org.jboss.tools.cdi.bot.test.wizard.template.ProjectWithCDITemplate;
import org.junit.Before;

@OpenPerspective(JavaEEPerspective.class)
@JBossServer(state=ServerRequirementState.PRESENT, cleanup=false)
public class EjbProjectWithCDITestCDI11 extends ProjectWithCDITemplate{

	@RequirementRestriction
	public static RequirementMatcher getRestrictionMatcher() {
	  return new RequirementMatcher(JBossServer.class, "family", ServerMatcher.WildFly());
	}
	
	public EjbProjectWithCDITestCDI11(){
		enabledByDefault = true;
		PROJECT_NAME = "EjbProject";
	}
	
	@Before
	public void createEjbProject(){
		EjbProjectWizard dw = new EjbProjectWizard();
		dw.open();
		EjbProjectFirstPage fp = new EjbProjectFirstPage(dw);
		fp.setProjectName(PROJECT_NAME);
		dw.finish();
	}

}
