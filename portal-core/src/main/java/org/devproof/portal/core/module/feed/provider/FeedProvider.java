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
package org.devproof.portal.core.module.feed.provider;

import java.util.List;

import org.devproof.portal.core.module.common.page.TemplatePage;

import com.sun.syndication.feed.synd.SyndFeed;

/**
 * @author Carsten Hufe
 */
public interface FeedProvider {
	/**
	 * Returns the feed with feed items. See https://rome.dev.java.net/
	 */
	public SyndFeed getFeed();

	/**
	 * Returns the supported feed pages where the feed reference will be
	 * embedded
	 */
	public List<Class<? extends TemplatePage>> getSupportedFeedPages();

	/**
	 * Returns the feed name which is shown in the overview and in the page
	 * reference
	 */
	public String getFeedName();
}