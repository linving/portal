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
package org.devproof.portal.core.module.mount.service;

import org.apache.wicket.IRequestTarget;
import org.apache.wicket.Page;
import org.apache.wicket.PageParameters;
import org.devproof.portal.core.module.common.repository.CrudRepository;
import org.devproof.portal.core.module.common.service.CrudService;
import org.devproof.portal.core.module.mount.entity.MountPoint;

import java.util.List;

/**
 * @author Carsten Hufe
 */
// TODO comment
public interface MountService {
    IRequestTarget resolveRequestTarget(String requestedUrl);
    MountPoint findFirstMountPoint(String relatedContentId, String handlerKey);
    boolean existsPath(String requestedUrl);
    String urlFor(Class<? extends Page> pageClazz, PageParameters params);
    boolean canHandlePageClass(Class<? extends Page> pageClazz, PageParameters pageParameters);
    void moveUp(MountPoint mountPoint);
    void moveDown(MountPoint mountPoint);
    void addMountPoint(String path, String relatedContentId, String handlerKey);
    void removeMountPoint(String path);
    List<MountPoint> findMountPoints(String relatedContentId, String handlerKey);
    boolean existsMountPoint(String relatedContentId, String handlerKey);
}