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
package org.devproof.portal.module.comment.config;

import java.io.Serializable;

/**
 * @author Carsten Hufe
 */
public interface CommentConfiguration extends Serializable {
    /**
     * Returns the module name
     */
    String getModuleName();

    /**
     * Returns the module content id
     */
    String getModuleContentId();

    /**
     * returns true if the user is allowed to view the comments
     */
    boolean isAllowedToView();

    /**
     * returns true if the user is allowed to compose comments
     */
    boolean isAllowedToWrite();
}
