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
package org.devproof.portal.core.module.historization.page;

import org.apache.wicket.Component;
import org.apache.wicket.PageParameters;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.devproof.portal.core.module.common.page.TemplatePage;
import org.devproof.portal.core.module.configuration.service.ConfigurationService;

/**
 * @author Carsten Hufe
 */
// TODO unit test
    // TODO comment
public abstract class RestoreViewPage extends TemplatePage {

    private static final long serialVersionUID = 1L;

    @SpringBean(name = "configurationService")
    private ConfigurationService configurationService;

    public RestoreViewPage() {
        super(new PageParameters());
        add(newHistorizedView("blog"));
    }

    protected abstract Component newHistorizedView(String markupId);
}
