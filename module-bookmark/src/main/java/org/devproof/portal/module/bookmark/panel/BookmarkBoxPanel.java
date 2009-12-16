/*
 * Copyright 2009-2010 Carsten Hufe devproof.org
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
package org.devproof.portal.module.bookmark.panel;

import java.util.List;

import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.RepeatingView;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.devproof.portal.core.app.PortalSession;
import org.devproof.portal.core.module.box.panel.BoxTitleVisibility;
import org.devproof.portal.core.module.configuration.service.ConfigurationService;
import org.devproof.portal.module.bookmark.BookmarkConstants;
import org.devproof.portal.module.bookmark.entity.BookmarkEntity;
import org.devproof.portal.module.bookmark.page.BookmarkPage;
import org.devproof.portal.module.bookmark.service.BookmarkService;

/**
 * @author Carsten Hufe
 */
public class BookmarkBoxPanel extends Panel implements BoxTitleVisibility {

	private static final long serialVersionUID = 1L;

	@SpringBean(name = "bookmarkService")
	private BookmarkService bookmarkService;
	@SpringBean(name = "configurationService")
	private ConfigurationService configurationService;
	private WebMarkupContainer titleContainer;
	private List<BookmarkEntity> latestBookmarks;

	public BookmarkBoxPanel(String id) {
		super(id);
		createLatestBookmarks();
		setVisible(isBookmarkAvailable());
		add(createTitleContainer());
		add(createRepeatingViewWithBookmarks());
	}

	private boolean isBookmarkAvailable() {
		return latestBookmarks.size() > 0;
	}

	private RepeatingView createRepeatingViewWithBookmarks() {
		RepeatingView repeating = new RepeatingView("repeating");
		for (BookmarkEntity bookmark : latestBookmarks) {
			WebMarkupContainer item = new WebMarkupContainer(repeating.newChildId());
			item.add(createLinkToBookmark(bookmark));
			repeating.add(item);
		}
		return repeating;
	}

	private BookmarkablePageLink<BookmarkPage> createLinkToBookmark(BookmarkEntity bookmark) {
		BookmarkablePageLink<BookmarkPage> link = new BookmarkablePageLink<BookmarkPage>("link", BookmarkPage.class);
		link.setParameter("id", bookmark.getId());
		link.add(new Label("linkName", bookmark.getTitle()));
		return link;
	}

	private List<BookmarkEntity> createLatestBookmarks() {
		PortalSession session = (PortalSession) getSession();
		Integer num = configurationService.findAsInteger(BookmarkConstants.CONF_BOX_NUM_LATEST_BOOKMARKS);
		latestBookmarks = bookmarkService.findAllBookmarksForRoleOrderedByDateDesc(session.getRole(), 0, num);
		return latestBookmarks;
	}

	private WebMarkupContainer createTitleContainer() {
		titleContainer = new WebMarkupContainer("title");
		return titleContainer;
	}

	@Override
	public void setTitleVisible(boolean visible) {
		titleContainer.setVisible(visible);
	}
}
