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
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests {@link FileUtil}.
 *
 */
public class FileUtilTest {
    /**
     * The temporary directory that is used for the I/O tests.
     *
     */
    private File tmpFile;

    /**
     * Creates the temp dir.
     *
     * @throws IOException
     *             If an I/O error occurres.
     */
    @Before
    public void setup() throws IOException {
        this.tmpFile = File.createTempFile("ipm-test", null);
        this.tmpFile.delete();
        this.tmpFile.mkdirs();
    }

    /**
     * Deletes the temp dir.
     *
     */
    @After
    public void teardown() {
        this.tmpFile.delete();
    }

    /**
     * Tests {@link FileUtil#zip(File, File)} and
     * {@link FileUtil#unzip(File, File)}.
     *
     * @throws IOException
     *             If an I/O error occurres.
     */
    @Test
    public void testZipAndUnzip() throws IOException {
        final File dir = new File(this.tmpFile, "zip-content");
        dir.mkdirs();
        new File(dir, "t0").createNewFile();
        new File(dir, "t1").createNewFile();
        new File(dir, "t2").createNewFile();
        new File(dir, "t3").createNewFile();
        new File(dir, "t4").createNewFile();
        new File(dir, "t5").createNewFile();
        new File(dir, "com/blockhaus2000/test").mkdirs();
        new File(dir, "com/blockhaus2000/test/test123").createNewFile();
        new File(dir, "com/blockhaus2000/test/nothing/in/here").mkdirs();

        final File out = new File(this.tmpFile, "zipped.zip");

        FileUtil.zip(out, dir);

        final File outDir = new File(this.tmpFile, "zip-content-out");
        outDir.mkdirs();

        FileUtil.unzip(out, outDir);

        Assert.assertTrue(new File(outDir, "t0").exists());
        Assert.assertTrue(new File(outDir, "t1").exists());
        Assert.assertTrue(new File(outDir, "t2").exists());
        Assert.assertTrue(new File(outDir, "t3").exists());
        Assert.assertTrue(new File(outDir, "t4").exists());
        Assert.assertTrue(new File(outDir, "t5").exists());
        Assert.assertTrue(new File(outDir, "com/blockhaus2000/test").exists());
        Assert.assertTrue(new File(outDir, "com/blockhaus2000/test/test123").exists());

        Assert.assertFalse(new File(outDir, "com/blockhaus2000/test/nothing/in/here").exists());
    }

    /**
     * Tests {@link FileUtil#copy(File, File)} and
     * {@link FileUtil#copy(InputStream, OutputStream)}.
     *
     * @throws IOException
     *             If an I/O error occurres.
     */
    @Test
    public void testCopy() throws IOException {
        final byte[] testText = "This is a test!".getBytes();

        final File from = new File(this.tmpFile, "t0");
        final OutputStream out = new FileOutputStream(from);
        out.write(testText);
        out.close();

        final File to = new File(this.tmpFile, "t1");

        FileUtil.copy(from, to);

        final InputStream in = new FileInputStream(to);
        final byte[] buffer = new byte[testText.length];
        in.read(buffer);
        in.close();

        Assert.assertArrayEquals(testText, buffer);
    }

    /**
     * Tests {@link FileUtil#getFilesRecursive(File, FileFilter)}.
     *
     * @throws IOException
     *             If an I/O error occurres.
     */
    @Test
    public void testGetFilesRecursive() throws IOException {
        final File t0 = new File(this.tmpFile, "t0");
        final File t1 = new File(this.tmpFile, "t1");
        final File t2 = new File(this.tmpFile, "t2");
        final File cbt0 = new File(this.tmpFile, "com/blockhaus2000/t0");
        final File t3 = new File(this.tmpFile, "t3");
        final File hello = new File(this.tmpFile, "hello");

        t0.getParentFile().mkdirs();
        t1.getParentFile().mkdirs();
        t2.getParentFile().mkdirs();
        cbt0.getParentFile().mkdirs();
        t3.getParentFile().mkdirs();
        hello.getParentFile().mkdirs();

        t0.createNewFile();
        t1.createNewFile();
        t2.createNewFile();
        cbt0.createNewFile();
        t3.createNewFile();
        hello.createNewFile();

        final List<File> foundFiles = FileUtil.getFilesRecursive(this.tmpFile, new FileFilter() {
            @Override
            public boolean accept(final File testFile) {
                return testFile.getName().startsWith("t");
            }
        });

        Assert.assertTrue(foundFiles.contains(t0));
        Assert.assertTrue(foundFiles.contains(t1));
        Assert.assertTrue(foundFiles.contains(t2));
        Assert.assertTrue(foundFiles.contains(cbt0));
        Assert.assertTrue(foundFiles.contains(t3));

        Assert.assertFalse(foundFiles.contains(hello));
    }

    /**
     * Tests {@link FileUtil#createTempDir()}.
     *
     * @throws IOException
     *             If an I/O error occurres.
     */
    @Test
    public void testCreateTempDir() throws IOException {
        final File file = FileUtil.createTempDir();
        Assert.assertTrue(file.exists());
    }

    /**
     * Tests {@link FileUtil#createTempFile()}.
     *
     * @throws IOException
     *             If an I/O error occurres.
     */
    @Test
    public void testCreateTempFile() throws IOException {
        final File file = FileUtil.createTempFile();
        Assert.assertTrue(file.exists());
    }

    /**
     * Tests {@link FileUtil#retrievePathAbsoluteTo(File, File)}.
     *
     * @throws IOException
     *             If an I/O error occurres.
     */
    @Test
    public void testRetrievePathAbsoluteTo() throws IOException {
        Assert.assertEquals("/" + this.tmpFile.getName(),
                FileUtil.retrievePathAbsoluteTo(this.tmpFile.getParentFile(), this.tmpFile));
    }

    /**
     * Tests {@link FileUtil#normalizeForZip(String)} and
     * {@link FileUtil#normalizeForZip(String, boolean)}.
     *
     */
    @Test
    public void testNormalizeForZip() {
        Assert.assertEquals("correct/path/", FileUtil.normalizeForZip("correct/path/", true));
        Assert.assertEquals("has/to/be/normalized/", FileUtil.normalizeForZip("////has/to/be/normalized////", true));
        Assert.assertEquals("has/to/be/normalized", FileUtil.normalizeForZip("/has/to/be/normalized", false));
        Assert.assertEquals("has/to/be/normalized", FileUtil.normalizeForZip("/has/to/be/normalized"));
    }
}
