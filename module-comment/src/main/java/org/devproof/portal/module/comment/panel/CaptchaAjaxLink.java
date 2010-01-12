package org.devproof.portal.module.comment.panel;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxFallbackLink;
import org.devproof.portal.core.app.PortalSession;

public abstract class CaptchaAjaxLink extends AjaxFallbackLink<Void> {
	private static final long serialVersionUID = 1L;
	private BubblePanel bubblePanel;

	public CaptchaAjaxLink(String id, BubblePanel bubblePanel) {
		super(id);
		this.bubblePanel = bubblePanel;
		setOutputMarkupId(true);
	}

	@Override
	final public void onClick(AjaxRequestTarget target) {
		if (!PortalSession.get().hasRight("captcha.disabled")) {
			CaptchaPanel captchaPanel = new CaptchaPanel(bubblePanel.getContentId()) {
				private static final long serialVersionUID = 1L;

				@Override
				protected void onClickAndCaptchaValidated(AjaxRequestTarget target) {
					bubblePanel.hide(target);
					CaptchaAjaxLink.this.onClickAndCaptchaValidated(target);
				}

				@Override
				protected void onCancel(AjaxRequestTarget target) {
					bubblePanel.hide(target);
				}
			};
			bubblePanel.setContent(captchaPanel);
			bubblePanel.show(getMarkupId(), target);
		} else {
			onClickAndCaptchaValidated(target);
		}
	}

	public abstract void onClickAndCaptchaValidated(AjaxRequestTarget target);
}
