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
package org.devproof.portal.core.module.box.page;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.PageParameters;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.extensions.ajax.markup.html.modal.ModalWindow;
import org.apache.wicket.extensions.markup.html.repeater.util.SortableDataProvider;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.image.Image;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.markup.repeater.data.IDataProvider;
import org.apache.wicket.model.AbstractReadOnlyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.devproof.portal.core.module.box.entity.BoxEntity;
import org.devproof.portal.core.module.box.panel.BoxEditPanel;
import org.devproof.portal.core.module.box.registry.BoxRegistry;
import org.devproof.portal.core.module.box.service.BoxService;
import org.devproof.portal.core.module.common.CommonConstants;
import org.devproof.portal.core.module.common.page.TemplatePage;
import org.devproof.portal.core.module.common.panel.AuthorPanel;

/**
 * @author Carsten Hufe
 */
public class BoxPage extends TemplatePage {

	private static final long serialVersionUID = 1L;

	@SpringBean(name = "boxDataProvider")
	private SortableDataProvider<BoxEntity> boxDataProvider;
	@SpringBean(name = "boxService")
	private BoxService boxService;
	@SpringBean(name = "boxRegistry")
	private BoxRegistry boxRegistry;

	private final ModalWindow modalWindow;
	private final WebMarkupContainer container;

	public BoxPage(final PageParameters params) {
		super(params);
		container = new WebMarkupContainer("refreshTable");
		container.setOutputMarkupId(true);
		add(container);

		modalWindow = new ModalWindow("modalWindow");
		modalWindow.setTitle("Portal");
		add(modalWindow);

		BoxDataView dataView = new BoxDataView("tableRow", boxDataProvider, params);
		container.add(dataView);
		AjaxLink<BoxEntity> createLink = new AjaxLink<BoxEntity>("adminLink") {
			private static final long serialVersionUID = 1L;

			@Override
			public void onClick(final AjaxRequestTarget target) {
				final BoxEditPanel boxEditPanel = new BoxEditPanel(modalWindow.getContentId(), boxService
						.newBoxEntity()) {

					private static final long serialVersionUID = 1L;

					@Override
					public void onSave(final AjaxRequestTarget target) {
						target.addComponent(container);
						target.addComponent(BoxPage.this.getFeedback());
						info(getString("msg.saved"));
						modalWindow.close(target);
					}

				};
				modalWindow.setInitialHeight(280);
				modalWindow.setInitialWidth(550);
				modalWindow.setContent(boxEditPanel);
				modalWindow.show(target);
			}
		};

		createLink.add(new Label("linkName", getString("createLink")));
		addPageAdminBoxLink(createLink);
	}

	private class BoxDataView extends DataView<BoxEntity> {
		private static final long serialVersionUID = 1L;

		public BoxDataView(final String id, final IDataProvider<BoxEntity> dataProvider, final PageParameters params) {
			super(id, dataProvider);
		}

		@Override
		protected void populateItem(final Item<BoxEntity> item) {
			final BoxEntity box = item.getModelObject();
			item.add(new Label("sort", Integer.toString(box.getSort())));
			String name = boxRegistry.getNameBySimpleClassName(box.getBoxType());
			item.add(new Label("type", name));
			item.add(new Label("title", box.getTitle()));

			item.add(new AuthorPanel<BoxEntity>("authorButtons", box) {
				private static final long serialVersionUID = 1L;

				@Override
				public void onDelete(final AjaxRequestTarget target) {
					boxService.delete(box);
					target.addComponent(container);
					target.addComponent(getFeedback());
					info(getString("msg.deleted"));
				}

				@Override
				public void onEdit(final AjaxRequestTarget target) {
					final BoxEditPanel editUserPanel = new BoxEditPanel(modalWindow.getContentId(), box) {

						private static final long serialVersionUID = 1L;

						@Override
						public void onSave(final AjaxRequestTarget target) {
							target.addComponent(container);
							target.addComponent(getFeedback());
							info(getString("msg.saved"));
							modalWindow.close(target);
						}

					};

					modalWindow.setContent(editUserPanel);
					modalWindow.show(target);
				}

			});

			item.add(new AjaxLink<BoxEntity>("upLink") {
				private static final long serialVersionUID = 1L;

				@Override
				public void onClick(final AjaxRequestTarget target) {
					boxService.moveUp(box);
					target.addComponent(container);
				}
			}.add(new Image("upImage", CommonConstants.REF_UP_IMG)));

			item.add(new AjaxLink<BoxEntity>("downLink") {
				private static final long serialVersionUID = 1L;

				@Override
				public void onClick(final AjaxRequestTarget target) {
					boxService.moveDown(box);
					target.addComponent(container);
				}
			}.add(new Image("downImage", CommonConstants.REF_DOWN_IMG)));

			item.add(new AttributeModifier("class", true, new AbstractReadOnlyModel<String>() {
				private static final long serialVersionUID = 1L;

				@Override
				public String getObject() {
					return (item.getIndex() % 2 != 0) ? "even" : "odd";
				}
			}));
		}
	};
}