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
package org.devproof.portal.module.download.service;

import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.feed.synd.SyndFeedImpl;
import org.apache.wicket.RequestCycle;
import org.devproof.portal.core.module.common.CommonConstants;
import org.devproof.portal.core.module.common.dataprovider.SortableQueryDataProvider;
import org.devproof.portal.core.module.configuration.service.ConfigurationService;
import org.devproof.portal.module.download.DownloadConstants;
import org.devproof.portal.module.download.entity.Download;
import org.devproof.portal.module.download.page.DownloadPage;
import org.devproof.portal.module.download.query.DownloadQuery;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.easymock.EasyMock.*;
import static org.junit.Assert.*;

/**
 * @author Carsten Hufe
 */
public class DownloadFeedProviderImplTest {
    private DownloadFeedProviderImpl impl;
    private SortableQueryDataProvider<Download, DownloadQuery> dataProviderMock;
    private ConfigurationService configurationServiceMock;

    @Before
    @SuppressWarnings("unchecked")
    public void setUp() throws Exception {
        dataProviderMock = createStrictMock(SortableQueryDataProvider.class);
        configurationServiceMock = createMock(ConfigurationService.class);
        impl = new DownloadFeedProviderImpl() {
            @Override
            protected String getUrl(RequestCycle rc) {
                return "http://url";
            }

            @Override
            protected String getUrl(RequestCycle rc, Download download) {
                return "http://url/" + download.getId();
            }
        };
        impl.setConfigurationService(configurationServiceMock);
        impl.setDownloadDataProvider(dataProviderMock);
    }

    @Test
    public void testGetFeedName() {
        expect(configurationServiceMock.findAsString(CommonConstants.CONF_PAGE_TITLE)).andReturn("pagetitle");
        expect(configurationServiceMock.findAsString(DownloadConstants.CONF_DOWNLOAD_FEED_TITLE)).andReturn("feedtitle");
        replay(configurationServiceMock);
        assertEquals("pagetitle - feedtitle", impl.getFeedName());
        verify(configurationServiceMock);
    }

    @Test
    public void testSupportedPages() {
        assertEquals(DownloadPage.class, impl.getSupportedFeedPages().get(0));
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testGetDownloadEntries() {
        Download download = createDownload();
        @SuppressWarnings("rawtypes")
		Iterator it = Arrays.asList(download).iterator();
        expect(configurationServiceMock.findAsInteger(DownloadConstants.CONF_DOWNLOAD_ENTRIES_IN_FEED)).andReturn(10);
        expect(dataProviderMock.iterator(0, 10)).andReturn(it);
        replay(configurationServiceMock);
        replay(dataProviderMock);
        Iterator<? extends Download> bookmarkEntries = impl.getDownloadEntries();
        assertSame(bookmarkEntries, it);
        verify(configurationServiceMock);
        verify(dataProviderMock);
    }

    @Test
    public void testGenerateFeed() {
        expect(configurationServiceMock.findAsString(CommonConstants.CONF_PAGE_TITLE)).andReturn("pagetitle").anyTimes();
        expect(configurationServiceMock.findAsString(DownloadConstants.CONF_DOWNLOAD_FEED_TITLE)).andReturn("feedtitle").anyTimes();
        replay(configurationServiceMock);
        SyndFeed feed = impl.generateFeed(null);
        assertEquals("pagetitle - feedtitle", feed.getTitle());
        assertEquals("pagetitle - feedtitle", feed.getDescription());
        assertEquals("http://url", feed.getLink());
        verify(configurationServiceMock);
    }

    @Test
    public void testGenerateFeedEntries() {
        Download download = createDownload();
		Iterator<Download> it = Arrays.asList(download).iterator();
        List<SyndEntry> generateFeedEntries = impl.generateFeedEntries(null, it);
        SyndEntry entry = generateFeedEntries.get(0);
        assertEquals("hello", entry.getTitle());
        assertEquals("http://url/" + download.getId(), entry.getLink());
        assertEquals("world", entry.getDescription().getValue());
        assertEquals("text/plain", entry.getDescription().getType());
        assertEquals("maxpower", entry.getAuthor());
        assertNotNull(entry.getPublishedDate());
    }

    @Test
    public void testGetFeed() {
        final List<SyndEntry> entries = new ArrayList<SyndEntry>();
        final StringBuilder callOrder = new StringBuilder();
        impl = new DownloadFeedProviderImpl() {
            @Override
            protected SyndFeed generateFeed(RequestCycle rc) {
                callOrder.append("1");
                return new SyndFeedImpl();
            }

            @Override
            protected Iterator<? extends Download> getDownloadEntries() {
                callOrder.append("2");
                return null;
            }

            @Override
            protected List<SyndEntry> generateFeedEntries(RequestCycle rc, Iterator<? extends Download> iterator) {
                callOrder.append("3");
                return entries;
            }

            @Override
            protected String getUrl(RequestCycle rc) {
                return "";
            }
        };
        impl.getFeed(null);
        assertEquals("123", callOrder.toString());
    }

    private Download createDownload() {
        Download download = new Download();
        download.setId(1);
        download.setTitle("hello");
        download.setDescription("world");
        download.setModifiedBy("maxpower");
        download.setModifiedAt(new Date());
        return download;
    }
}
