/* This file is part of the InternalPluginManager.
 *
 * Copyright (C) 2014-2015 Fabian Damken
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.blockhaus2000.ipm.base;

import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This class simplifies the usage of files (or the file system in general).
 *
 */
public class FileUtil {
    /**
     * The {@link TempFileDeleter}.
     *
     */
    private static final TempFileDeleter FILE_DELETER = new TempFileDeleter();

    /**
     * Zips the content of the given directory into the given file.
     *
     * @param zipFile
     *            The file to zip in.
     * @param inputDir
     *            The directory that contains the content that should be zipped.
     * @throws IOException
     *             If an I/O error occurres.
     */
    public static void zip(final File zipFile, final File inputDir) throws IOException {
        assert zipFile != null : "ZipFile cannot be null!";
        assert !zipFile.exists() : "ZipFile must not exist!";
        assert inputDir != null : "InputDir cannot be null!";
        assert inputDir.exists() && inputDir.isDirectory() : "InputDir must be a directory!";

        zipFile.getParentFile().mkdirs();
        zipFile.createNewFile();

        final ZipOutputStream out = new ZipOutputStream(new FileOutputStream(zipFile));
        for (final File file : FileUtil.getFilesRecursive(inputDir, null)) {
            final ZipEntry entry = new ZipEntry(FileUtil.normalizeForZip(FileUtil.retrievePathAbsoluteTo(inputDir, file)));
            out.putNextEntry(entry);
            final FileInputStream in = new FileInputStream(file);
            FileUtil.copy(in, out);
            in.close();
        }
        out.close();
    }

    /**
     * Unzips the given zip file into the given output directory.
     *
     * @param zipFile
     *            The ZIP file to unzip.
     * @param outputDir
     *            The output directory for the ZIP content.
     * @throws IOException
     *             If an I/O error occurres.
     */
    public static void unzip(final File zipFile, final File outputDir) throws IOException {
        assert zipFile != null : "ZipFile cannot be null!";
        assert zipFile.exists() && zipFile.isFile() : "ZipFile has to be a file!";
        assert outputDir != null : "OutputDir cannot be null!";
        assert outputDir.exists() && outputDir.isDirectory() : "OutputDir has to be a directory!";

        final ZipInputStream in = new ZipInputStream(new FileInputStream(zipFile));

        ZipEntry entry = in.getNextEntry();
        while (entry != null) {
            final File file = new File(outputDir, entry.getName());
            file.getParentFile().mkdirs();
            if (!entry.isDirectory()) {
                file.createNewFile();

                final FileOutputStream out = new FileOutputStream(file);
                FileUtil.copy(in, out);
                out.close();
            }
            entry = in.getNextEntry();
        }

        in.close();
    }

    /**
     * Copies the given file to the other file.
     *
     * <p>
     * <b> NOTE: This only works for real files (no directories, etc.). </b>
     * </p>
     *
     * @param from
     *            The file to copy.
     * @param to
     *            The file to copy to.
     * @throws IOException
     *             If an I/O error occurres.
     */
    public static void copy(final File from, final File to) throws IOException {
        assert from != null : "From cannot be null!";
        assert from.exists() : "From has to exist!";
        assert from.isFile() : "From must be a file!";
        assert to != null : "To cannot be null!";
        assert !to.exists() : "To must not exist!";

        to.getParentFile().mkdirs();
        to.createNewFile();

        final FileOutputStream out = new FileOutputStream(to);
        final FileInputStream in = new FileInputStream(from);
        FileUtil.copy(in, out);
        out.close();
        in.close();
    }

    /**
     * Copies the content of the given input stream into the given output
     * stream.
     *
     * @param from
     *            The {@link InputStream} to copy from.
     * @param to
     *            The {@link OutputStream} to copy to.
     * @throws IOException
     *             If an I/O error occurres.
     */
    public static void copy(final InputStream from, final OutputStream to) throws IOException {
        final byte[] buffer = new byte[1024];

        int length = 0;
        while ((length = from.read(buffer)) > 0) {
            to.write(buffer, 0, length);
        }
    }

    /**
     * Lists all files in the given directory that match the given
     * {@link FileFilter}, but without directories.
     *
     * @param file
     *            The directory to search through.
     * @param rawFileFilter
     *            The {@link FileFilter} to use for filtering. If this is
     *            <code>null</code>, a all-accepting {@link FileFilter} will be
     *            used.
     * @return A {@link List} of the found files.
     */
    public static List<File> getFilesRecursive(final File file, final FileFilter rawFileFilter) {
        assert file != null : "File cannot be null!";
        assert file.exists() : "File has to exist!";

        final FileFilter fileFilter;
        if (rawFileFilter == null) {
            fileFilter = new FileFilter() {
                @Override
                public boolean accept(final File testFile) {
                    return true;
                }
            };
        } else {
            fileFilter = rawFileFilter;
        }

        final List<File> result = new ArrayList<File>();
        if (file.isFile()) {
            if (fileFilter.accept(file)) {
                result.add(file);
            }
        } else if (file.isDirectory()) {
            for (final File curFile : file.listFiles()) {
                result.addAll(FileUtil.getFilesRecursive(curFile, fileFilter));
            }
        }
        return result;
    }

    /**
     * Creates a temporary directory.
     *
     * <p>
     * <b> NOTE: This just invokes {@link File#createTempFile(String, String)}
     * with <code>prefix = ipm</code> and <code>suffix = null</code>, deletes
     * the file and creates a directory with the same name. </b>
     * </p>
     *
     * @return The newly created temporary directory.
     * @throws IOException
     *             If an I/O error occurres.
     */
    public static File createTempDir() throws IOException {
        final File tmpDir = File.createTempFile("ipm", null);
        tmpDir.delete();
        tmpDir.mkdir();
        FileUtil.FILE_DELETER.addFileToDelete(tmpDir);
        return tmpDir;
    }

    /**
     * Creates a temporary file.
     *
     * @return The newly created temp file.
     * @throws IOException
     *             If an I/O error occurres.
     */
    public static File createTempFile() throws IOException {
        final File tmpFile = File.createTempFile("ipm", null);
        FileUtil.FILE_DELETER.addFileToDelete(tmpFile);
        return tmpFile;
    }

    /**
     * Retrieves the absolute path of the given file, but chrooted to the given
     * root dir.
     *
     * @param root
     *            The root dir to use.
     * @param file
     *            The file to retrieve the path from.
     * @return The path, if found.
     * @throws IOException
     *             If the given file is not a subfile of the root file.
     */
    public static String retrievePathAbsoluteTo(final File root, final File file) throws IOException {
        assert root != null : "Root cannot be null!";
        assert root.isDirectory() : "Root has to be a directory!";
        assert file != null : "File cannot be null!";
        assert file.isFile() || file.isDirectory() : "File has to be a file or a directory!";

        if (!file.getAbsolutePath().startsWith(root.getAbsolutePath())) {
            throw new IOException(file + " has to be a subfile of " + root + "!");
        }

        return file.getAbsolutePath().replace(root.getAbsolutePath(), "");
    }

    /**
     * Normalizes the given path to be a valid ZIP path (no slash at the front
     * and a slash at the end of directories).
     *
     * @param path
     *            The path to normalize.
     * @param isDir
     *            Whether the given path should be handled as a directory or as
     *            a file.
     * @return The normalized path.
     */
    public static String normalizeForZip(final String path, final boolean isDir) {
        assert path != null : "Path cannot be null!";

        return path.replaceAll("^/*(.*?)/*$", "$1") + (isDir ? "/" : "");
    }

    /**
     * Invokes {@link FileUtil#normalizeForZip(String, boolean)} with
     * <code>isDir = false</code>.
     *
     * @param path
     *            Is directly passed to
     *            {@link FileUtil#normalizeForZip(String, boolean)}.
     * @return The result of {@link FileUtil#normalizeForZip(String, boolean)}.
     * @see com.blockhaus2000.ipm.base.FileUtil#normalizeForZip(java.lang.String,
     *      boolean)
     */
    public static String normalizeForZip(final String path) {
        return FileUtil.normalizeForZip(path, false);
    }

    /**
     * The temp file delete is used to manage that listed files are deleted if
     * the JVM exits.
     *
     */
    private static class TempFileDeleter {
        /**
         * The logger for this class.
         *
         */
        private static final Logger LOGGER = LoggerFactory.getLogger(TempFileDeleter.class);

        /**
         * Contains the files that will be deleted if the JVM shuts down.
         *
         */
        private final List<File> filesToDelete = new ArrayList<File>();

        /**
         * Constructor of TempFileDeleter.
         *
         */
        public TempFileDeleter() {
            Runtime.getRuntime().addShutdownHook(new Thread() {
                /**
                 * {@inheritDoc}
                 *
                 * @see java.lang.Thread#run()
                 */
                @Override
                public void run() {
                    for (final File file : TempFileDeleter.this.filesToDelete) {
                        try {
                            file.delete();
                        } catch (final Exception cause) {
                            TempFileDeleter.LOGGER.error("An error occurred whilest deleting temp file " + file + "!", cause);
                        }
                    }
                }
            });
        }

        /**
         * Adds the given {@link File} to the list of files to delete.
         *
         * @param file
         *            The {@link File} to delete on exit/shutdown of the JVM.
         */
        public void addFileToDelete(final File file) {
            this.filesToDelete.add(file);
        }
    }
}
