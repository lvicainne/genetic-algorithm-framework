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
import java.util.Collection;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

@SuppressWarnings("serial")
public class ZipFileProxy extends AbstractZipFileProxy {
    private final ZipFile m_ZipFile;
    private final Map<String, Collection<String>> m_Children;

    /**
     * Construct a file proxy for a {@link ZipFile}
     * 
     * @param p_ZipFile
     *            The {@link ZipFile}
     */
    @SuppressWarnings("unchecked")
    public ZipFileProxy(ZipFile p_ZipFile) {
        super(p_ZipFile.getName());
        m_ZipFile = p_ZipFile;
        m_Children = new HashMap<String, Collection<String>>();
        Enumeration<ZipEntry> en = (Enumeration<ZipEntry>) p_ZipFile.entries();
        parse(en);
    }

    /**
     * create a map of the entries and their paths
     * 
     * @param p_ZipEntries
     *            Enumeration of {@link ZipEntry}
     */
    private void parse(Enumeration<ZipEntry> p_ZipEntries) {
        while (p_ZipEntries.hasMoreElements()) {
            ZipEntry zipEntry = p_ZipEntries.nextElement();
            String fullName = zipEntry.getName();
            String name;

            if (zipEntry.isDirectory()) {
                name = fullName.substring(0, fullName.length() - 1);
            } else {
                name = fullName;
            }

            addChild(name);
        }
    }

    /**
     * Add a new child of the {@link ZipFile}
     * 
     * @param p_Name
     *            Name of the entry
     */
    private void addChild(String p_Name) {
        String name = p_Name;
        int index;
        String parent;

        index = p_Name.lastIndexOf("/");
        if (index != -1) {
            parent = p_Name.substring(0, index);
            // name = p_Name.substring(index + 1);
            addChild(parent);
        } else {
            parent = "";
            // name = p_Name;
        }

        Collection<String> parentChildren = m_Children.get(parent);
        if (parentChildren == null) {
            parentChildren = new HashSet<String>();
            m_Children.put(parent, parentChildren);
        }
        parentChildren.add(name);
    }

    /**
     * Get the original {@link ZipFile}
     * 
     * @return The original {@link ZipFile}
     */
    public ZipFile getZipFile() {
        return m_ZipFile;
    }

    /**
     * Return a File corresponding to a {@link ZipEntry}
     * 
     * @param p_Name
     *            The name of the {@link ZipEntry}
     * @return A file corresponding to the {@link ZipEntry}
     */
    public File getFile(String p_Name) {
        return new ZipEntryFileProxy(this, p_Name);
    }

    /**
     * Return an array of File corresponding to the entries in a directory
     * 
     * @param p_Dir
     *            the directory to lookup
     * @return An array of File
     */
    public File[] getFiles(String p_Dir) {
        Collection<String> children = m_Children.get(p_Dir);
        File[] files = new File[children.size()];
        int count = 0;
        for (String zipEntryName : children) {
            files[count] = new ZipEntryFileProxy(this, zipEntryName);
            count++;
        }
        return files;
    }

    /**
     * @param p_EntryName
     *            The name of the entry to lookup
     * @return <code>true</code> if the entry is a directory, false otherwise
     */
    public boolean isDirectory(String p_EntryName) {
        return m_Children.get(p_EntryName) != null;
    }

    @Override
    public String getParent() {
        return getParentFile().getPath();
    }

    @Override
    public File getParentFile() {
        return new File(m_ZipFile.getName()).getParentFile();
    }

    @Override
    public boolean isDirectory() {
        return true;
    }

    @Override
    public long length() {
        return new File(m_ZipFile.getName()).length();
    }

    @Override
    public File[] listFiles() {
        return getFiles("");
    }
}