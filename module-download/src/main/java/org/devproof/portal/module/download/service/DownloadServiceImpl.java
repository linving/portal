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

import org.devproof.portal.core.module.mount.service.MountService;
import org.devproof.portal.core.module.role.entity.Role;
import org.devproof.portal.module.download.DownloadConstants;
import org.devproof.portal.module.download.entity.Download;
import org.devproof.portal.module.download.repository.DownloadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Carsten Hufe
 */
@Service("downloadService")
public class DownloadServiceImpl implements DownloadService {
    private DownloadRepository downloadRepository;
    private DownloadTagService downloadTagService;
    private MountService mountService;

    @Override
    @Transactional(readOnly = true)
    public List<Download> findAllDownloadsForRoleOrderedByDateDesc(Role role, Integer firstResult, Integer maxResult) {
        return downloadRepository.findAllDownloadsForRoleOrderedByDateDesc(role, firstResult, maxResult);
    }

    @Override
    @Transactional
    public void incrementHits(Download download) {
        downloadRepository.incrementHits(download);
    }

    @Override
    @Transactional
    public void markBrokenDownload(Download download) {
        downloadRepository.markBrokenDownload(download);
    }

    @Override
    @Transactional
    public void markValidDownload(Download download) {
        downloadRepository.markValidDownload(download);
    }

    @Override
    @Transactional(readOnly = true)
    public Download newDownloadEntity() {
        Download download = new Download();
        download.setAllRights(downloadRepository.findLastSelectedRights());
        return download;
    }

    @Override
    @Transactional
    public void rateDownload(Integer rating, Download download) {
        downloadRepository.rateDownload(rating, download);
        downloadRepository.refresh(download);
    }

    @Override
    @Transactional
    public void delete(Download entity) {
        downloadRepository.delete(entity);
        downloadTagService.deleteUnusedTags();
        mountService.delete(entity.getId().toString(), DownloadConstants.HANDLER_KEY);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Download> findAll() {
        return downloadRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Download findById(Integer id) {
        return downloadRepository.findById(id);
    }

    @Override
    @Transactional
    public void save(Download entity) {
        downloadRepository.save(entity);
        downloadTagService.deleteUnusedTags();
    }

    @Autowired
    public void setDownloadRepository(DownloadRepository downloadRepository) {
        this.downloadRepository = downloadRepository;
    }

    @Autowired
    public void setDownloadTagService(DownloadTagService downloadTagService) {
        this.downloadTagService = downloadTagService;
    }

    @Autowired
    public void setMountService(MountService mountService) {
        this.mountService = mountService;
    }
}
