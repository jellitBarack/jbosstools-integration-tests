/*******************************************************************************
 * Copyright (c) 2011 Red Hat, Inc.
 * Distributed under license by Red Hat, Inc. All rights reserved.
 * This program is made available under the terms of the
 * Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Red Hat, Inc. - initial API and implementation
 ******************************************************************************/ 
package org.jboss.tools.maven.ui.bot.test.configurator;

import static org.junit.Assert.assertEquals;

import org.eclipse.reddeer.common.wait.WaitUntil;
import org.eclipse.reddeer.eclipse.ui.navigator.resources.ProjectExplorer;
import org.eclipse.reddeer.eclipse.ui.perspectives.JavaEEPerspective;
import org.eclipse.reddeer.requirements.openperspective.OpenPerspectiveRequirement.OpenPerspective;
import org.eclipse.reddeer.requirements.server.ServerRequirementState;
import org.eclipse.reddeer.swt.impl.button.PushButton;
import org.eclipse.reddeer.swt.impl.combo.LabeledCombo;
import org.eclipse.reddeer.swt.impl.menu.ContextMenuItem;
import org.eclipse.reddeer.swt.impl.tree.DefaultTreeItem;
import org.eclipse.reddeer.workbench.ui.dialogs.WorkbenchPreferenceDialog;
import org.jboss.ide.eclipse.as.reddeer.server.requirement.ServerRequirement.JBossServer;
import org.jboss.tools.maven.reddeer.maven.ui.preferences.ConfiguratorPreferencePage;
import org.jboss.tools.maven.reddeer.requirement.NewRepositoryRequirement.DefineMavenRepository;
import org.jboss.tools.maven.reddeer.requirement.NewRepositoryRequirement.PredefinedMavenRepository;
import org.jboss.tools.maven.reddeer.wizards.ConfigureMavenRepositoriesWizard;
import org.jboss.tools.maven.ui.bot.test.project.SeamProjectTest;
import org.jboss.tools.maven.ui.bot.test.utils.ProjectHasNature;
import org.jboss.tools.seam.reddeer.preferences.SeamPreferencePage;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
/**
 * @author Rastislav Wagner
 * 
 */
@OpenPerspective(JavaEEPerspective.class)
@JBossServer(state=ServerRequirementState.PRESENT)
@DefineMavenRepository(predefinedRepositories = { @PredefinedMavenRepository(ID="jboss-public-repository",snapshots=true) })
public class SeamConfiguratorTest extends AbstractConfiguratorsTest{
	
	private String projectNameNoRuntime = PROJECT_NAME_SEAM+"_noRuntime";
	
	@BeforeClass
	public static void setupRuntimes(){
		WorkbenchPreferenceDialog preferenceDialog = new WorkbenchPreferenceDialog();
		preferenceDialog.open();
		SeamPreferencePage sp = new SeamPreferencePage(preferenceDialog);
		preferenceDialog.select(sp);
		sp.addRuntime(SeamProjectTest.SEAM_2_1_NAME, SeamProjectTest.SEAM_2_1, "2.1");
		sp.addRuntime(SeamProjectTest.SEAM_2_2_NAME, SeamProjectTest.SEAM_2_2, "2.2");
		sp.addRuntime(SeamProjectTest.SEAM_2_3_NAME, SeamProjectTest.SEAM_2_3, "2.3");
		preferenceDialog.ok();
	}
	
	@AfterClass
	public static void cleanRepo(){
		WorkbenchPreferenceDialog preferenceDialog = new WorkbenchPreferenceDialog();
		preferenceDialog.open();
		ConfiguratorPreferencePage jm = new ConfiguratorPreferencePage(preferenceDialog);
		preferenceDialog.select(jm);

		ConfigureMavenRepositoriesWizard mr = jm.configureRepositories();
		boolean deleted = mr.removeAllRepos();
		if(deleted){
			mr.confirm();
		} else {
			mr.cancel();
		}
		preferenceDialog.ok();
		
	}
	
	@Test
	public void testSeamConfigurator21Seam(){
		createWebProject(projectNameNoRuntime, null, false);
		convertToMavenProject(projectNameNoRuntime, "war", false);
		addDependency(projectNameNoRuntime, "org.jboss.seam", "jboss-seam", "2.1.2.GA"); //dependency type EJB
		updateConf(projectNameNoRuntime);
		new WaitUntil(new ProjectHasNature(projectNameNoRuntime, SEAM_FACET, "2.1"));
	}
	
	@Test
	public void testSeamConfigurator22Seam(){
		createWebProject(projectNameNoRuntime, null, false);
		convertToMavenProject(projectNameNoRuntime, "war", false);
		addDependency(projectNameNoRuntime, "org.jboss.seam", "jboss-seam", "2.2.2.Final"); //dependency type EJB
		updateConf(projectNameNoRuntime);
		new WaitUntil(new ProjectHasNature(projectNameNoRuntime, SEAM_FACET, "2.2"));
	}
	
	@Test
	public void testSeamConfigurator22SeamDebug(){
		createWebProject(projectNameNoRuntime, null,false);
		convertToMavenProject(projectNameNoRuntime, "war", false);
		addDependency(projectNameNoRuntime, "org.jboss.seam", "jboss-seam-debug", "2.2.2.Final"); //dependency type EJB
		updateConf(projectNameNoRuntime);
		new WaitUntil(new ProjectHasNature(projectNameNoRuntime, SEAM_FACET, "2.2"));
	}
	
	@Test
	public void testSeamConfigurator23Seam(){
		createWebProject(projectNameNoRuntime, null, false);
		convertToMavenProject(projectNameNoRuntime, "war", false);
		addDependency(projectNameNoRuntime, "org.jboss.seam", "jboss-seam", "2.3.0.Beta2"); //dependency type EJB
		updateConf(projectNameNoRuntime);
		new WaitUntil(new ProjectHasNature(projectNameNoRuntime, SEAM_FACET, "2.3"));
	}
	@Test
	public void testSeamConfigurator23SeamUI() {	
		createWebProject(projectNameNoRuntime, null, false);
		convertToMavenProject(projectNameNoRuntime, "war", false);
		addDependency(projectNameNoRuntime, "org.jboss.seam", "jboss-seam-ui", "2.3.0.Beta2");
		updateConf(projectNameNoRuntime);
		new WaitUntil(new ProjectHasNature(projectNameNoRuntime, SEAM_FACET, "2.3"));
	}
	@Test
	public void testSeamConfigurator23SeamPDF(){		
		createWebProject(projectNameNoRuntime, null, false);
		convertToMavenProject(projectNameNoRuntime, "war", false);
		addDependency(projectNameNoRuntime, "org.jboss.seam", "jboss-seam-pdf", "2.3.0.Beta2");
		updateConf(projectNameNoRuntime);
		new WaitUntil(new ProjectHasNature(projectNameNoRuntime, SEAM_FACET, "2.3"));
	}
	@Test
	public void testSeamConfigurator23SeamRemoting(){		
		createWebProject(projectNameNoRuntime, null, false);
		convertToMavenProject(projectNameNoRuntime, "war", false);
		addDependency(projectNameNoRuntime, "org.jboss.seam", "jboss-seam-remoting", "2.3.0.Beta2");
		updateConf(projectNameNoRuntime);
		new WaitUntil(new ProjectHasNature(projectNameNoRuntime, SEAM_FACET, "2.3"));	
	}
	@Test
	public void testSeamConfigurator23SeamIOC(){	
		createWebProject(projectNameNoRuntime,null, false);
		convertToMavenProject(projectNameNoRuntime, "war", false);
		addDependency(projectNameNoRuntime, "org.jboss.seam", "jboss-seam-ioc", "2.3.0.Beta2");
		updateConf(projectNameNoRuntime);
		new WaitUntil(new ProjectHasNature(projectNameNoRuntime, SEAM_FACET, "2.3"));	
	}
	
	@Test
	public void testSeamRuntimeConfigurator23(){
		createWebProject(PROJECT_NAME_SEAM, null, false);
		convertToMavenProject(PROJECT_NAME_SEAM, "war", false);
		addDependency(PROJECT_NAME_SEAM, "org.jboss.seam", "jboss-seam", "2.3.0.Final"); //dependency type EJB
		updateConf(PROJECT_NAME_SEAM);
		new WaitUntil(new ProjectHasNature(PROJECT_NAME_SEAM, SEAM_FACET, "2.3"));
		String seamRuntime = getSeamRuntime(PROJECT_NAME_SEAM);
		assertEquals("Project "+PROJECT_NAME_SEAM+" with jboss-seam dependency doesn't have proper seam runtime chosen",  SeamProjectTest.SEAM_2_3_NAME, seamRuntime);
		
	}
	
	@Test
	public void testSeamRuntimeConfigurator22(){
		createWebProject(PROJECT_NAME_SEAM, null, false);
		convertToMavenProject(PROJECT_NAME_SEAM, "war", false);
		addDependency(PROJECT_NAME_SEAM, "org.jboss.seam", "jboss-seam", "2.2.2.Final"); //dependency type EJB
		updateConf(PROJECT_NAME_SEAM);
		new WaitUntil(new ProjectHasNature(PROJECT_NAME_SEAM, SEAM_FACET, "2.2"));
		String seamRuntime = getSeamRuntime(PROJECT_NAME_SEAM);
		assertEquals("Project "+PROJECT_NAME_SEAM+" with jboss-seam dependency doesn't have proper seam runtime chosen", SeamProjectTest.SEAM_2_2_NAME, seamRuntime);
	}
	
	@Test
	public void testSeamRuntimeConfigurator21(){
		createWebProject(PROJECT_NAME_SEAM, null, false);
		convertToMavenProject(PROJECT_NAME_SEAM, "war", false);
		addDependency(PROJECT_NAME_SEAM, "org.jboss.seam", "jboss-seam", "2.1.2.GA"); //dependency type EJB
		updateConf(PROJECT_NAME_SEAM);
		new WaitUntil(new ProjectHasNature(PROJECT_NAME_SEAM, SEAM_FACET, "2.1"));
		String seamRuntime = getSeamRuntime(PROJECT_NAME_SEAM);
		assertEquals("Project "+PROJECT_NAME_SEAM+" with jboss-seam dependency doesn't have proper seam runtime chosen",  SeamProjectTest.SEAM_2_1_NAME, seamRuntime);
	}
	
	private String getSeamRuntime(String project){
		ProjectExplorer pe = new ProjectExplorer();
		pe.open();
		pe.getProject(project).select();
		new ContextMenuItem("Properties").select();
		new DefaultTreeItem("Seam Settings").select();
		String runtime =  new LabeledCombo("Seam Runtime:").getSelection();
		new PushButton("OK").click();
		return runtime;
	}
}