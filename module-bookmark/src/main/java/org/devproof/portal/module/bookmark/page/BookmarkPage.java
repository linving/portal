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
package org.devproof.portal.module.bookmark.page;

import org.apache.wicket.Component;
import org.apache.wicket.PageParameters;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.image.Image;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.panel.Fragment;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.model.util.ListModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.devproof.portal.core.app.PortalSession;
import org.devproof.portal.core.module.common.component.ExtendedLabel;
import org.devproof.portal.core.module.common.component.StatelessRatingPanel;
import org.devproof.portal.core.module.common.dataprovider.QueryDataProvider;
import org.devproof.portal.core.module.common.panel.AuthorPanel;
import org.devproof.portal.core.module.common.panel.BookmarkablePagingPanel;
import org.devproof.portal.core.module.common.panel.MetaInfoPanel;
import org.devproof.portal.core.module.configuration.service.ConfigurationService;
import org.devproof.portal.core.module.tag.panel.ContentTagPanel;
import org.devproof.portal.core.module.tag.service.TagService;
import org.devproof.portal.module.bookmark.BookmarkConstants;
import org.devproof.portal.module.bookmark.entity.BookmarkEntity;
import org.devproof.portal.module.bookmark.entity.BookmarkTagEntity;
import org.devproof.portal.module.bookmark.entity.BookmarkEntity.Source;
import org.devproof.portal.module.bookmark.panel.BookmarkSearchBoxPanel;
import org.devproof.portal.module.bookmark.query.BookmarkQuery;
import org.devproof.portal.module.bookmark.service.BookmarkService;
import org.devproof.portal.module.deadlinkcheck.DeadLinkCheckConstants;

/**
 * @author Carsten Hufe
 */
public class BookmarkPage extends BookmarkBasePage {

	private static final long serialVersionUID = 1L;
	@SpringBean(name = "bookmarkService")
	private BookmarkService bookmarkService;
	@SpringBean(name = "bookmarkDataProvider")
	private QueryDataProvider<BookmarkEntity> bookmarkDataProvider;
	@SpringBean(name = "bookmarkTagService")
	private TagService<BookmarkTagEntity> bookmarkTagService;
	@SpringBean(name = "configurationService")
	private ConfigurationService configurationService;

	private BookmarkDataView dataView;
	private BookmarkQuery query;
	private PageParameters params;

	public BookmarkPage(PageParameters params) {
		super(params);
		this.params = params;
		createBookmarkQuery();
		add(createBookmarkDataView());
		add(createPagingPanel());
		addFilterBox(createBookmarkSearchBoxPanel());
		addTagCloudBox();
	}

	private void addTagCloudBox() {
		addTagCloudBox(bookmarkTagService, new PropertyModel<BookmarkTagEntity>(query, "tag"), BookmarkPage.class,
				params);
	}

	private BookmarkablePagingPanel createPagingPanel() {
		return new BookmarkablePagingPanel("paging", dataView, BookmarkPage.class, params);
	}

	private BookmarkSearchBoxPanel createBookmarkSearchBoxPanel() {
		return new BookmarkSearchBoxPanel("box", query, bookmarkDataProvider, this, dataView, params);
	}

	private BookmarkDataView createBookmarkDataView() {
		dataView = new BookmarkDataView("listBookmark");
		return dataView;
	}

	private BookmarkQuery createBookmarkQuery() {
		PortalSession session = (PortalSession) getSession();
		query = new BookmarkQuery();
		if (!session.hasRight("bookmark.view")) {
			query.setRole(session.getRole());
		}
		if (!isAuthor() && configurationService.findAsBoolean(BookmarkConstants.CONF_BOOKMARK_HIDE_BROKEN)) {
			query.setBroken(false);
		}
		bookmarkDataProvider.setQueryObject(query);
		return query;
	}

	private class BookmarkDataView extends DataView<BookmarkEntity> {
		private static final long serialVersionUID = 1L;
		private boolean onlyOneBookmarkInResult;

		public BookmarkDataView(String id) {
			super(id, bookmarkDataProvider);
			onlyOneBookmarkInResult = bookmarkDataProvider.size() == 1;
			setItemsPerPage(configurationService.findAsInteger(BookmarkConstants.CONF_BOOKMARKS_PER_PAGE));
		}

		@Override
		protected void populateItem(Item<BookmarkEntity> item) {
			setBookmarkNameAsPageTitle(item);
			item.setOutputMarkupId(true);
			item.add(createBookmarkView(item));
		}

		private void setBookmarkNameAsPageTitle(Item<BookmarkEntity> item) {
			if (onlyOneBookmarkInResult) {
				BookmarkEntity bookmark = item.getModelObject();
				setPageTitle(bookmark.getTitle());
			}
		}

		private BookmarkView createBookmarkView(Item<BookmarkEntity> item) {
			return new BookmarkView("bookmarkView", item);
		}
	}

	private class BookmarkView extends Fragment {

		private static final long serialVersionUID = 1L;

		private Model<Boolean> hasVoted;
		private BookmarkEntity bookmark;

		public BookmarkView(String id, Item<BookmarkEntity> item) {
			super(id, "bookmarkView", BookmarkPage.this);
			bookmark = item.getModelObject();
			hasVoted = Model.of(!isAllowedToVote());
			add(createBrokenLabel());
			add(createTitleLink());
			add(createDeliciousSourceImage());
			add(createAppropriateAuthorPanel(item));
			add(createMetaInfoPanel());
			add(createDescriptionLabel());
			add(createHitsLabel());
			add(createTagPanel());
			add(createRatingPanel());
			add(createVisitLink());
		}

		private BookmarkablePageLink<?> createVisitLink() {
			BookmarkablePageLink<?> visitLink = new BookmarkablePageLink<Void>("bookmarkLink",
					BookmarkRedirectPage.class);
			visitLink.add(createVisitLinkImage());
			visitLink.add(createVisitLinkLabel());
			visitLink.setParameter("0", bookmark.getId());
			visitLink.setEnabled(isAllowedToVisit());
			return visitLink;
		}

		private Image createVisitLinkImage() {
			return new Image("bookmarkImage", DeadLinkCheckConstants.REF_DOWNLOAD_IMG);
		}

		private Label createVisitLinkLabel() {
			String labelKey = isAllowedToVisit() ? "visitNow" : "loginToVisit";
			return new Label("bookmarkLinkLabel", BookmarkPage.this.getString(labelKey));
		}

		private Component createRatingPanel() {
			StatelessRatingPanel ratingPanel = newRatingPanel();
			ratingPanel.setVisible(isVoteEnabled());
			return ratingPanel;
		}

		private StatelessRatingPanel newRatingPanel() {
			return new StatelessRatingPanel("vote", new PropertyModel<Integer>(bookmark, "calculatedRating"), Model
					.of(5), new PropertyModel<Integer>(bookmark, "numberOfVotes"), hasVoted, true, params, bookmark
					.getId()) {
				private static final long serialVersionUID = 1L;

				@Override
				protected boolean onIsStarActive(int star) {
					return star < ((int) (bookmark.getCalculatedRating() + 0.5));
				}

				@Override
				protected void onRated(int rating) {
					if (isAllowedToVote()) {
						hasVoted.setObject(Boolean.TRUE);
						bookmarkService.rateBookmark(rating, bookmark);
						info(BookmarkPage.this.getString("voteCounted"));
					}
				}
			};
		}

		private boolean isAllowedToVote() {
			PortalSession session = (PortalSession) getSession();
			return session.hasRight("bookmark.vote", bookmark.getVoteRights());
		}

		private boolean isVoteEnabled() {
			return configurationService.findAsBoolean(BookmarkConstants.CONF_BOOKMARK_VOTE_ENABLED);
		}

		private ContentTagPanel<BookmarkTagEntity> createTagPanel() {
			return new ContentTagPanel<BookmarkTagEntity>("tags", new ListModel<BookmarkTagEntity>(bookmark.getTags()),
					BookmarkPage.class, params);
		}

		private Label createHitsLabel() {
			return new Label("hits", String.valueOf(bookmark.getHits()));
		}

		private ExtendedLabel createDescriptionLabel() {
			return new ExtendedLabel("description", bookmark.getDescription());
		}

		private Component createDeliciousSourceImage() {
			if (isAuthor() && bookmark.getSource() == Source.DELICIOUS) {
				// be aware... images are stateful
				return new Image("delicious", BookmarkConstants.REF_DELICIOUS);
			} else {
				Component hiddenImage = new WebMarkupContainer("delicious");
				hiddenImage.setVisible(false);
				return hiddenImage;
			}
		}

		private MetaInfoPanel createMetaInfoPanel() {
			return new MetaInfoPanel("metaInfo", bookmark);
		}

		private BookmarkablePageLink<BookmarkRedirectPage> createTitleLink() {
			BookmarkablePageLink<BookmarkRedirectPage> titleLink = new BookmarkablePageLink<BookmarkRedirectPage>(
					"titleLink", BookmarkRedirectPage.class);
			titleLink.setParameter("0", bookmark.getId());
			titleLink.setEnabled(isAllowedToVisit());
			titleLink.add(createTitleLabel());
			return titleLink;
		}

		private Label createTitleLabel() {
			return new Label("titleLabel", bookmark.getTitle());
		}

		private boolean isAllowedToVisit() {
			PortalSession session = (PortalSession) getSession();
			boolean allowedToVisit = session.hasRight("bookmark.visit", bookmark.getVisitRights());
			return allowedToVisit;
		}

		private Component createBrokenLabel() {
			return new Label("broken", BookmarkPage.this.getString("broken")).setVisible(bookmark.getBroken() != null
					&& bookmark.getBroken());
		}

		private Component createAppropriateAuthorPanel(Item<BookmarkEntity> item) {
			if (isAuthor()) {
				return createAuthorPanel(item);
			} else {
				return createEmptyAuthorPanel();
			}
		}

		private AuthorPanel<BookmarkEntity> createAuthorPanel(final Item<BookmarkEntity> item) {
			return new AuthorPanel<BookmarkEntity>("authorButtons", bookmark) {
				private static final long serialVersionUID = 1L;

				@Override
				public void onDelete(AjaxRequestTarget target) {
					bookmarkService.delete(getEntity());
					item.setVisible(false);
					target.addComponent(item);
					target.addComponent(getFeedback());
					info(getString("msg.deleted"));
				}

				@Override
				public void onEdit(AjaxRequestTarget target) {
					BookmarkEntity refreshedBookmark = bookmarkService.findById(bookmark.getId());
					setResponsePage(new BookmarkEditPage(refreshedBookmark));
				}
			};
		}

		private WebMarkupContainer createEmptyAuthorPanel() {
			return new WebMarkupContainer("authorButtons");
		}
	}
}
