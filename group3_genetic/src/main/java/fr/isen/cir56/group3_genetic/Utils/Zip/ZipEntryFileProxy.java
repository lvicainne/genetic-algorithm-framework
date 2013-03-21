/*
 * This file is part of ldapbeans
 *
 * Released under LGPL
 *
 * ldapbeans is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * ldapbeans is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with ldapbeans.  If not, see <http://www.gnu.org/licenses/>.
 *
 * Copyright 2010 Bruno Macherel
 */
package fr.isen.cir56.group3_genetic.Utils.Zip;

import java.io.File;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

@SuppressWarnings("serial")
public class ZipEntryFileProxy extends AbstractZipFileProxy {
    private final ZipFileProxy m_ZipFileProxy;
    private final String m_ZipEntryName;

    /**
     * Construct a file proxy for {@link ZipEntry}
     * 
     * @param p_ZipFileProxy
     *            Proxy of the {@link ZipFile} of this entry
     * @param p_EntryName
     *            The name of the entry
     */
    public ZipEntryFileProxy(ZipFileProxy p_ZipFileProxy, String p_EntryName) {
        super(p_ZipFileProxy.getPath() + '!' + p_EntryName);
        m_ZipFileProxy = p_ZipFileProxy;
        m_ZipEntryName = p_EntryName.endsWith("/") ? p_EntryName.substring(0,
                p_EntryName.length() - 1) : p_EntryName;
    }

    @Override
    public boolean isDirectory() {
        return m_ZipFileProxy.isDirectory(m_ZipEntryName);
    }

    @Override
    public File getParentFile() {
        int index = m_ZipEntryName.lastIndexOf("/");
        File parentFile;
        if (index != -1) {
            String parent = m_ZipEntryName.substring(0, index + 1);
            parentFile = m_ZipFileProxy.getFile(parent);
        } else {
            parentFile = m_ZipFileProxy;
        }
        return parentFile;
    }

    @Override
    public String getParent() {
        return getParentFile().getName();
    }

    @Override
    public long length() {
        final ZipFile zipFile = m_ZipFileProxy.getZipFile();
        final ZipEntry zipEntry = zipFile.getEntry(m_ZipEntryName);
        return zipEntry.getSize();
    }

    @Override
    public File[] listFiles() {
        return m_ZipFileProxy.getFiles(m_ZipEntryName);
    }
}