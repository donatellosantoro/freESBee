package org.apache.torque.engine.platform;

/*
 * Copyright 2001-2005 The Apache Software Foundation.
 *
 * Licensed under the Apache License, Version 2.0 (the "License")
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import org.apache.torque.engine.database.model.Domain;
import org.apache.torque.engine.database.model.SchemaType;

/**
 * Sybase Platform implementation.
 *
 * @author <a href="mailto:mpoeschl@marmot.at">Martin Poeschl</a>
 * @version $Id: PlatformSybaseImpl.java 239626 2005-08-24 12:19:51Z henning $
 */
public class PlatformSybaseImpl extends PlatformDefaultImpl
{
    /**
     * Default constructor.
     */
    public PlatformSybaseImpl()
    {
        super();
        initialize();
    }

    /**
     * Initializes db specific domain mapping.
     */
    private void initialize()
    {
        setSchemaDomainMapping(new Domain(SchemaType.INTEGER, "INT"));
        setSchemaDomainMapping(new Domain(SchemaType.BOOLEANINT, "INT"));
        setSchemaDomainMapping(new Domain(SchemaType.DOUBLE, "FLOAT"));
        setSchemaDomainMapping(new Domain(SchemaType.LONGVARCHAR, "TEXT"));
        setSchemaDomainMapping(new Domain(SchemaType.DATE, "DATETIME"));
        setSchemaDomainMapping(new Domain(SchemaType.TIME, "TIMESTAMP"));
        setSchemaDomainMapping(new Domain(SchemaType.TIMESTAMP, "DATETIME"));
        setSchemaDomainMapping(new Domain(SchemaType.VARBINARY, "IMAGE"));
        setSchemaDomainMapping(new Domain(SchemaType.LONGVARBINARY, "IMAGE"));
    }

    /**
     * @see Platform#getMaxColumnNameLength()
     */
    public int getMaxColumnNameLength()
    {
        return 30;
    }

}
