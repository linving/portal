/*
 * Copyright 2009-2011 Carsten Hufe devproof.org
 *
 * Licensed under the Apache License, Version 2.0 (the "License")
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.devproof.portal.module.comment.panel;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.behavior.HeaderContributor;
import org.apache.wicket.behavior.SimpleAttributeModifier;
import org.apache.wicket.markup.html.CSSPackageResource;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.EmptyPanel;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.AbstractReadOnlyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.devproof.portal.core.module.common.util.PortalUtil;
import org.devproof.portal.module.comment.CommentConstants;
import org.devproof.portal.module.comment.config.CommentConfiguration;
import org.devproof.portal.module.comment.service.CommentService;

/**
 * @author Carsten Hufe
 */
public class ExpandableCommentPanel extends Panel {

    private static final long serialVersionUID = 1L;
    @SpringBean(name = "commentService")
    private CommentService commentService;
    private WebMarkupContainer refreshContainer;
    private boolean visible = false;
    private CommentConfiguration configuration;

    public ExpandableCommentPanel(String id, CommentConfiguration configuration) {
        super(id);
        this.configuration = configuration;
        addJQuery();
        add(createCSSHeaderContributor());
        add(createRefreshCommentContainer());
    }


    private CommentPanel createCommentPanel() {
        return new CommentPanel("comments", configuration);
    }

    public void show(AjaxRequestTarget target) {
        refreshContainer.replace(createCommentPanel());
        target.addComponent(refreshContainer);
        target.appendJavascript("$(\"#" + refreshContainer.getMarkupId() + "\").slideDown(\"normal\");");
    }

    public void hide(AjaxRequestTarget target) {
        target.appendJavascript("$(\"#" + refreshContainer.getMarkupId() + "\").slideUp(\"normal\");");
    }

    public void toggle(AjaxRequestTarget target) {
        if (visible) {
            hide(target);
        } else {
            show(target);
        }
        visible = !visible;
    }

    public boolean isCommentsVisible() {
        return visible && isVisible();
    }

    @Override
    public boolean isVisible() {
        return configuration.isAllowedToView();
    }

    private WebMarkupContainer createRefreshCommentContainer() {
        refreshContainer = new WebMarkupContainer("refreshCommentContainer");
        refreshContainer.add(createEmptyCommentPanel());
        refreshContainer.add(createDisplayNoneModifier());
        refreshContainer.setOutputMarkupId(true);
        return refreshContainer;
    }

    private EmptyPanel createEmptyCommentPanel() {
        return new EmptyPanel("comments");
    }

    private SimpleAttributeModifier createDisplayNoneModifier() {
        return new SimpleAttributeModifier("style", "display:none;");
    }

    private void addJQuery() {
        PortalUtil.addJQuery(this);
    }

    private HeaderContributor createCSSHeaderContributor() {
        return CSSPackageResource.getHeaderContribution(CommentConstants.class, "css/comment.css");
    }
}
