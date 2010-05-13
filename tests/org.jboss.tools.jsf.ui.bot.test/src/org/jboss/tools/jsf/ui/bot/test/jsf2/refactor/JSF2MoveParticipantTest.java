package org.jboss.tools.jsf.ui.bot.test.jsf2.refactor;

import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotEclipseEditor;
import org.eclipse.swtbot.swt.finder.SWTBot;
import org.eclipse.swtbot.swt.finder.exceptions.WidgetNotFoundException;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTree;
import org.jboss.tools.ui.bot.test.WidgetVariables;

public class JSF2MoveParticipantTest extends JSF2AbstractRefactorTest {

	public void testJSF2MoveParticipant() throws Exception {
		createTestPage();
		createCompositeComponent();
		createDistResFolder();
		moveCurrResFolder();
		checkContent();
	}

	private void checkContent() throws Exception {
		SWTBot innerBot = bot.viewByTitle(WidgetVariables.PACKAGE_EXPLORER)
				.bot();
		SWTBotTree tree = innerBot.tree();
		tree
				.expandNode(projectProperties.getProperty("JSFProjectName")).expandNode("WebContent").expandNode(JSF2_Test_Page_Name + ".xhtml").doubleClick(); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		delay();
		SWTBotEclipseEditor editor = bot.editorByTitle(
				JSF2_Test_Page_Name + ".xhtml").toTextEditor(); //$NON-NLS-1$
		assertEquals(
				loadFileContent("refactor/jsf2MoveTestPageRefactor.html"), editor.getText()); //$NON-NLS-1$
		delay();
		editor.close();
	}

	private void moveCurrResFolder() throws Exception {
		SWTBot innerBot = bot.viewByTitle(WidgetVariables.PACKAGE_EXPLORER)
				.bot();
		SWTBotTree tree = innerBot.tree();
		tree
				.expandNode(projectProperties.getProperty("JSFProjectName")).expandNode("WebContent").expandNode("resources").expandNode("mycomp").select(); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
		bot.menu("Refactor").menu("Move...").click(); //$NON-NLS-1$ //$NON-NLS-2$
		innerBot = bot.shell("Move").bot(); //$NON-NLS-1$
		tree = innerBot.tree();
		tree
				.expandNode(projectProperties.getProperty("JSFProjectName")).expandNode("WebContent").expandNode("resources").expandNode("mycomp1").select(); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
		delay();
		bot.button("OK").click(); //$NON-NLS-1$
		delay();
	}

	private void createDistResFolder() throws Exception {
		SWTBot innerBot = bot.viewByTitle(WidgetVariables.PACKAGE_EXPLORER)
				.bot();
		SWTBotTree tree = innerBot.tree();
		try {
			tree
					.expandNode(projectProperties.getProperty("JSFProjectName")).expandNode("WebContent").expandNode("resources").expandNode("mycomp1").select(); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
		} catch (WidgetNotFoundException e) {
			tree
					.getTreeItem(
							projectProperties.getProperty("JSFProjectName")).expandNode("WebContent").expandNode("resources").select(); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
			bot.menu("File").menu("New").menu("Folder").click(); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
			bot.textWithLabel("Folder name:").setText("mycomp1"); //$NON-NLS-1$ //$NON-NLS-2$
			bot.button("Finish").click(); //$NON-NLS-1$
			delay();
		}
	}

	@Override
	protected void tearDown() throws Exception {
		SWTBot innerBot = bot.viewByTitle(WidgetVariables.PACKAGE_EXPLORER)
				.bot();
		SWTBotTree tree = innerBot.tree();
		delay();
		tree
				.expandNode(projectProperties.getProperty("JSFProjectName")).expandNode("WebContent").expandNode(JSF2_Test_Page_Name + ".xhtml").select(); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		bot.menu("Edit").menu("Delete").click(); //$NON-NLS-1$ //$NON-NLS-2$
		bot.button("OK").click(); //$NON-NLS-1$
		delay();
		tree
				.expandNode(projectProperties.getProperty("JSFProjectName")).expandNode("WebContent").expandNode("resources").expandNode("mycomp1").select(); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
		bot.menu("Edit").menu("Delete").click(); //$NON-NLS-1$ //$NON-NLS-2$
		bot.button("OK").click(); //$NON-NLS-1$
		delay();
		super.tearDown();
	}

}
