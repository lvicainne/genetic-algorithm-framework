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
package fr.isen.cir56.group3_genetic.Utils.Reflection;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.JarURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.jar.JarFile;

import fr.isen.cir56.group3_genetic.Utils.Zip.ZipFileProxy;

public final class PackageHelper {
    /** unique instance of this class */
    private final static PackageHelper INSTANCE = new PackageHelper();

    /**
     * Return the unique instance of this class
     * 
     * @return the unique instance of this class
     */
    public static PackageHelper getInstance() {
        return INSTANCE;
    }

    /**
     * Constructor
     */
    private PackageHelper() {
        // Do nothing
    }

    /**
     * List all classes in a package
     * 
     * @param p_PackageName
     *            Name of the package to list
     * @param p_Recursive
     *            if <code>true</code> list class in subpackage
     * @param p_ClassFilter
     *            A filter of classes
     * @return List of found classes
     * @throws ClassNotFoundException
     *             If an expected class is not found
     */
    public Collection<Class<?>> getClasses(String p_PackageName,
            boolean p_Recursive, ClassFilter p_ClassFilter)
            throws ClassNotFoundException {
        Set<Class<?>> result = new HashSet<Class<?>>();
        List<Class<?>> classes;
        List<File> directories = getPackageDirectory(p_PackageName);

        for (File directory : directories) {
            classes = getClasses(directory, p_PackageName, p_Recursive,
                    p_ClassFilter);
            result.addAll(classes);
        }
        return result;
    }

    /**
     * List all classes in a directory
     * 
     * @param p_Dir
     *            Directory to list
     * @param p_PackageName
     *            Name of the package to list
     * @param p_Recursive
     *            if <code>true</code> list classes in subdirectory
     * @param p_ClassFilter
     *            A filter of classes
     * @return List of found classes
     * @throws ClassNotFoundException
     *             If an expected class is not found
     */
    private List<Class<?>> getClasses(File p_Dir, String p_PackageName,
            boolean p_Recursive, ClassFilter p_ClassFilter)
            throws ClassNotFoundException {
        List<Class<?>> classes = new ArrayList<Class<?>>();
        Class<?> clazz = null;
        if (p_Dir.exists() && p_Dir.isDirectory()) {
            // Get the list of the files contained in the package
            File[] files = p_Dir.listFiles();
            for (File file : files) {
                // we are only interested in .class files
                if (file.getName().endsWith(".class")) {
                    // removes the .class extension
                    clazz = Class.forName(p_PackageName
                            + '.'
                            + file.getName().substring(0,
                                    file.getName().length() - 6));
                    if (p_ClassFilter == null || p_ClassFilter.accept(clazz)) {
                        classes.add(clazz);
                    }
                }
                if (p_Recursive && file.isDirectory()) {
                    classes.addAll(getClasses(
                            p_PackageName + '.' + file.getName(), p_Recursive,
                            p_ClassFilter));
                }
            }
        } else {
            throw new ClassNotFoundException(p_PackageName
                    + " does not appear to be a valid package. "
                    + p_Dir.getAbsolutePath() + " does not exist.");
        }

        return classes;
    }

    /**
     * Find Directory corresponding to a package in classpath
     * 
     * @param p_PackageName
     *            Name of the package to find
     * @return The directory corresponding to the package name
     * @throws ClassNotFoundException
     *             If an expected class is not found
     */
    private List<File> getPackageDirectory(String p_PackageName)
            throws ClassNotFoundException {
        List<File> directories = new ArrayList<File>();
        File directory = null;
        ClassLoader cld = ClassLoader.getSystemClassLoader();
        if (cld == null) {
            throw new ClassNotFoundException("Can't get class loader.");
        }
        String path = p_PackageName.replace('.', '/');
        Enumeration<URL> resources;
        try {
            resources = cld.getResources(path);
        } catch (IOException e) {
            resources = null;
        }
        while (resources != null && resources.hasMoreElements()) {
            URL resource = resources.nextElement();
            if (resource == null) {
                throw new ClassNotFoundException("No resource for " + path);
            }

            if ("jar".equals(resource.getProtocol())) {
                try {
                    JarURLConnection conn = (JarURLConnection) resource
                            .openConnection();
                    JarFile jarfile = conn.getJarFile();
                    if (!path.endsWith("/")) {
                        path += '/';
                    }
                    directory = new ZipFileProxy(jarfile).getFile(path);
                } catch (Exception e) {
                    directory = null;
                }
            } else {
                String file;
                try {
                    file = URLDecoder.decode(resource.getFile(), "UTF-8");
                } catch (UnsupportedEncodingException e1) {
                    file = resource.getFile();
                }

                directory = new File(file);
            }
            if (directory != null) {
                directories.add(directory);
            }
        }
        return directories;
    }
}