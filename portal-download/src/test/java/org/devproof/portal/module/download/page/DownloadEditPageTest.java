/*
 * Copyright 2009 Carsten Hufe devproof.org
 * 
 * Licensed under the Apache License, Version 2.0 (the "License")
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 *   
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package org.devproof.portal.module.download.page;

import junit.framework.TestCase;

import org.apache.wicket.util.tester.FormTester;
import org.apache.wicket.util.tester.WicketTester;
import org.devproof.portal.module.download.entity.DownloadEntity;
import org.devproof.portal.test.PortalTestUtil;

/**
 * @author Carsten Hufe
 */
public class DownloadEditPageTest extends TestCase {
	private WicketTester tester;

	@Override
	public void setUp() throws Exception {
		tester = PortalTestUtil.createWicketTesterWithSpringAndDatabase("create_tables_hsql_download.sql",
				"insert_download.sql");
		PortalTestUtil.loginDefaultAdminUser(tester);
	}

	@Override
	protected void tearDown() throws Exception {
		PortalTestUtil.destroy(tester);
	}

	public void testRenderDefaultPage() {
		tester.startPage(new DownloadEditPage(new DownloadEntity()));
		tester.assertRenderedPage(DownloadEditPage.class);
	}

	public void testSaveDownload() {
		callDownloadEditPage();
		submitDownloadForm();
		assertDownloadPage();
	}

	public void testEditDownload() {
		navigateToDownloadEditPage();
		submitDownloadForm();
		assertDownloadPage();
		assertFalse(tester.getServletResponse().getDocument().contains("This is a sample."));
	}

	private void callDownloadEditPage() {
		tester.startPage(getNewDownloadEditPage());
		tester.assertRenderedPage(DownloadEditPage.class);
	}

	private DownloadEditPage getNewDownloadEditPage() {
		return new DownloadEditPage(new DownloadEntity());
	}

	private void submitDownloadForm() {
		FormTester form = tester.newFormTester("form");
		form.setValue("title", "testing title");
		form.setValue("description", "testing description");
		form.setValue("url", "http://www.devproof.org/download");
		form.submit();
	}

	private void navigateToDownloadEditPage() {
		tester.startPage(DownloadPage.class);
		tester.assertRenderedPage(DownloadPage.class);
		tester.assertContains("This is a sample.");
		tester.clickLink("listDownload:1:downloadView:authorButtons:editLink");
		tester.assertRenderedPage(DownloadEditPage.class);
	}

	private void assertDownloadPage() {
		String expectedMsgs[] = PortalTestUtil.getMessage("msg.saved", getNewDownloadEditPage());
		tester.assertRenderedPage(DownloadPage.class);
		tester.assertInfoMessages(expectedMsgs);
		tester.startPage(DownloadPage.class);
		tester.assertRenderedPage(DownloadPage.class);
		tester.assertContains("testing title");
		tester.assertContains("testing description");
	}
}
