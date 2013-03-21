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
import java.io.FileFilter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;

@SuppressWarnings("serial")
public abstract class AbstractZipFileProxy extends File {

    /**
     * Construct an abstract proxy for ZipFile
     * 
     * @param p_Path
     *            the path of the file
     */
    public AbstractZipFileProxy(String p_Path) {
        super(p_Path);
    }

    @Override
    public String getName() {
        return super.getName();
    }

    @Override
    public abstract String getParent();

    @Override
    public abstract File getParentFile();

    @Override
    public String getPath() {
        return super.getPath();
    }

    /* -- Path operations -- */

    @Override
    public boolean isAbsolute() {
        return true;
    }

    @Override
    public String getAbsolutePath() {
        return getPath();
    }

    @Override
    public File getAbsoluteFile() {
        return this;
    }

    @Override
    public String getCanonicalPath() throws IOException {
        return getPath();
    }

    @Override
    public File getCanonicalFile() throws IOException {
        return this;
    }

    /* -- Attribute accessors -- */

    @Override
    public boolean canRead() {
        return true;
    }

    @Override
    public boolean canWrite() {
        return false;
    }

    @Override
    public boolean exists() {
        return true;
    }

    @Override
    public abstract boolean isDirectory();

    @Override
    public boolean isFile() {
        return true;
    }

    @Override
    public boolean isHidden() {
        throw new UnsupportedOperationException();
    }

    @Override
    public long lastModified() {
        throw new UnsupportedOperationException();
    }

    @Override
    public abstract long length();

    /* -- File operations -- */

    @Override
    public boolean createNewFile() throws IOException {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean delete() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void deleteOnExit() {
        throw new UnsupportedOperationException();
    }

    @Override
    public String[] list() {
        File[] listFiles = listFiles();
        String[] list = null;
        if (listFiles != null) {
            list = new String[listFiles.length];
            for (int i = 0; i < listFiles.length; i++) {
                list[i] = listFiles[i].getName();
            }
        }
        return list;
    }

    @Override
    public String[] list(FilenameFilter p_Filter) {
        File[] listFiles = listFiles(p_Filter);
        String[] list = null;
        if (listFiles != null) {
            list = new String[listFiles.length];
            for (int i = 0; i < listFiles.length; i++) {
                list[i] = listFiles[i].getName();
            }
        }
        return list;
    }

    @Override
    public abstract File[] listFiles();

    @Override
    public File[] listFiles(FilenameFilter p_Filter) {
        ArrayList<File> result = null;
        File[] files = listFiles();
        if (files != null) {
            result = new ArrayList<File>(files.length);
            for (File file : files) {
                if (p_Filter == null || p_Filter.accept(this, file.getName())) {
                    result.add(file);
                }
            }
        }
        return result.toArray(new File[0]);
    }

    @Override
    public File[] listFiles(FileFilter p_Filter) {
        ArrayList<File> result = null;
        File[] files = listFiles();
        if (files != null) {
            result = new ArrayList<File>(files.length);
            for (File file : files) {
                if ((p_Filter == null) || p_Filter.accept(file)) {
                    result.add(file);
                }
            }
        }
        return result.toArray(new File[0]);
    }

    @Override
    public boolean mkdir() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean mkdirs() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean renameTo(File p_Dest) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean setLastModified(long p_Time) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean setReadOnly() {
        throw new UnsupportedOperationException();
    }

    /* -- Basic infrastructure -- */

    @Override
    public int compareTo(File p_Pathname) {
        return super.compareTo(p_Pathname);
    }

    @Override
    public boolean equals(Object p_Object) {
        return super.equals(p_Object);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}