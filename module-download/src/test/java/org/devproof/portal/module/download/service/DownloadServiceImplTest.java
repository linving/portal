/*
 * Copyright 2009-2010 Carsten Hufe devproof.org
 *
 * Licensed under the Apache License, Version 2.0 (the "License")
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.devproof.portal.module.download.service;

import org.devproof.portal.core.module.role.entity.RoleEntity;
import org.devproof.portal.core.module.tag.service.TagService;
import org.devproof.portal.module.download.dao.DownloadDao;
import org.devproof.portal.module.download.entity.DownloadEntity;
import org.devproof.portal.module.download.entity.DownloadTagEntity;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.easymock.EasyMock.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Checks the delegating functionality of the DownloadServiceImpl
 *
 * @author Carsten Hufe
 */
public class DownloadServiceImplTest {
    private DownloadServiceImpl impl;
    private DownloadDao mock;
    private DownloadTagService mockTag;

    @Before
    @SuppressWarnings("unchecked")
    public void setUp() throws Exception {
        mock = createStrictMock(DownloadDao.class);
        mockTag = createStrictMock(DownloadTagService.class);
        impl = new DownloadServiceImpl();
        impl.setDownloadDao(mock);
        impl.setDownloadTagService(mockTag);
    }

    @Test
    public void testSave() {
        DownloadEntity e = createDownloadEntity();
        expect(mock.save(e)).andReturn(e);
        mockTag.deleteUnusedTags();
        replay(mock);
        replay(mockTag);
        impl.save(e);
        verify(mock);
        verify(mockTag);
    }

    @Test
    public void testDelete() {
        DownloadEntity e = createDownloadEntity();
        mock.delete(e);
        mockTag.deleteUnusedTags();
        replay(mock);
        replay(mockTag);
        impl.delete(e);
        verify(mock);
        verify(mockTag);
    }

    @Test
    public void testFindAll() {
        List<DownloadEntity> list = new ArrayList<DownloadEntity>();
        list.add(createDownloadEntity());
        list.add(createDownloadEntity());
        expect(mock.findAll()).andReturn(list);
        replay(mock);
        assertEquals(list, impl.findAll());
        verify(mock);
    }

    @Test
    public void testFindById() {
        DownloadEntity e = createDownloadEntity();
        expect(mock.findById(1)).andReturn(e);
        replay(mock);
        assertEquals(impl.findById(1), e);
        verify(mock);
    }

    @Test
    public void testNewDownloadEntity() {
        assertNotNull(impl.newDownloadEntity());
    }

    @Test
    public void testFindAllDownloadsForRoleOrderedByDateDesc() {
        List<DownloadEntity> list = new ArrayList<DownloadEntity>();
        list.add(createDownloadEntity());
        list.add(createDownloadEntity());
        RoleEntity role = new RoleEntity();
        role.setId(1);
        expect(mock.findAllDownloadsForRoleOrderedByDateDesc(role, 0, 2)).andReturn(list);
        replay(mock);
        impl.findAllDownloadsForRoleOrderedByDateDesc(role, 0, 2);
        verify(mock);
    }

    @Test
    public void testIncrementHits() {
        DownloadEntity e = createDownloadEntity();
        mock.incrementHits(e);
        replay(mock);
        impl.incrementHits(e);
        verify(mock);
    }

    @Test
    public void testMarkBrokenDownload() {
        DownloadEntity e = createDownloadEntity();
        mock.markBrokenDownload(e);
        replay(mock);
        impl.markBrokenDownload(e);
        verify(mock);
    }

    @Test
    public void testMarkValidDownload() {
        DownloadEntity e = createDownloadEntity();
        mock.markValidDownload(e);
        replay(mock);
        impl.markValidDownload(e);
        verify(mock);
    }

    @Test
    public void testRateDownload() {
        DownloadEntity e = createDownloadEntity();
        mock.rateDownload(5, e);
        mock.refresh(e);
        replay(mock);
        impl.rateDownload(5, e);
        verify(mock);
    }

    private DownloadEntity createDownloadEntity() {
        DownloadEntity download = new DownloadEntity();
        download.setId(1);
        return download;
    }
}
