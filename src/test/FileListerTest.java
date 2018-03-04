/*
 * $Id: FileListerTest.java,v 1.5 2008/01/29 22:23:37 bragheb Exp $
 *
 * Copyright 2000-2008, TransactTools, Inc.
 * 22 Cortlandt Street, New York NY 10007, U.S.A.
 * All Rights Reserved.
 * Patent Pending.
 *
 * This software is the confidential and proprietary information
 * of TransactTools, Inc. ("Confidential Information").  You
 * may not disclose such Confidential Information and may use
 * it only in accordance with the terms of the license agreement
 * you entered into with TransactTools.
 *
 * www.nysetransacttools.com
 *
 */

import junit.framework.TestCase;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

public class FileListerTest extends TestCase {

    /**
     * The test directory structure.  Paths ending with a slash character (/)
     * are directories.  The other paths are files. 
     */
    private final String[] paths = {
        "x/",
        "x/a",
        "x/b",
        "x/y/",
        "x/y/c",
        "x/y/z/",
        "x/y/z/d",
        "x/y/z/e",
        "x/q/"
    };

    private File tmpDir;

    @Override
    protected void setUp() throws Exception {
        tmpDir = File.createTempFile("filelister", "test");
        System.out.println("writing temporary files to " +
                           tmpDir.getAbsolutePath());
        assertTrue("delete temp file", tmpDir.delete());
        assertTrue("create data directory", tmpDir.mkdir());
        for (String path : paths) {
            File file = new File(tmpDir, path);
            if (path.endsWith("/")) {
                assertTrue("create dir " + path, file.mkdirs());
            } else {
                assertTrue("create file " + path, file.createNewFile());
            }
        }
    }

    @Override
    protected void tearDown() throws Exception {
        List<String> pathList = new ArrayList<String>(Arrays.asList(paths));
        Collections.reverse(pathList);
        for (String path : pathList) {
            File file = new File(tmpDir, path);
            assertTrue("deleting " + path, file.delete());
        }
        assertTrue("deleting data directory", tmpDir.delete());
    }

    public void testFindFilesFromRoot() throws Exception {
        List<File> fileList = FileLister.findFiles(tmpDir);

        assertEquals("file count", 5, fileList.size());
        assertTrue(fileList.contains(new File(tmpDir, "x/a")));
        assertTrue(fileList.contains(new File(tmpDir, "x/b")));
        assertTrue(fileList.contains(new File(tmpDir, "x/y/c")));
        assertTrue(fileList.contains(new File(tmpDir, "x/y/z/d")));
        assertTrue(fileList.contains(new File(tmpDir, "x/y/z/e")));
    }

    public void testFindFilesFromX() throws Exception {
        List<File> fileList = FileLister.findFiles(new File(tmpDir, "x"));

        assertEquals("file count", 5, fileList.size());
        assertTrue(fileList.contains(new File(tmpDir, "x/a")));
        assertTrue(fileList.contains(new File(tmpDir, "x/b")));
        assertTrue(fileList.contains(new File(tmpDir, "x/y/c")));
        assertTrue(fileList.contains(new File(tmpDir, "x/y/z/d")));
        assertTrue(fileList.contains(new File(tmpDir, "x/y/z/e")));
    }

    public void testFindFilesFromY() throws Exception {
        List<File> fileList = FileLister.findFiles(new File(tmpDir, "x/y"));

        assertEquals("file count", 3, fileList.size());
        assertTrue(fileList.contains(new File(tmpDir, "x/y/c")));
        assertTrue(fileList.contains(new File(tmpDir, "x/y/z/d")));
        assertTrue(fileList.contains(new File(tmpDir, "x/y/z/e")));
    }

    public void testFindFilesFromZ() throws Exception {
        List<File> fileList = FileLister.findFiles(new File(tmpDir, "x/y/z"));

        assertEquals("file count", 2, fileList.size());
        assertTrue(fileList.contains(new File(tmpDir, "x/y/z/d")));
        assertTrue(fileList.contains(new File(tmpDir, "x/y/z/e")));
    }

    public void testFindFilesFromQ() throws Exception {
        List<File> fileList = FileLister.findFiles(new File(tmpDir, "x/q"));

        assertEquals("file count", 0, fileList.size());
    }

    public void testMutateList() throws Exception {
        File rootDir = new File(tmpDir, "x/y/z");

        List<File> fileListA = FileLister.findFiles(rootDir);
        assertEquals("file count", 2, fileListA.size());
        assertTrue(fileListA.contains(new File(tmpDir, "x/y/z/d")));
        assertTrue(fileListA.contains(new File(tmpDir, "x/y/z/e")));

        fileListA.clear();

        List<File> fileListB = FileLister.findFiles(rootDir);
        assertEquals("file count", 2, fileListB.size());
        assertTrue(fileListB.contains(new File(tmpDir, "x/y/z/d")));
        assertTrue(fileListB.contains(new File(tmpDir, "x/y/z/e")));
    }

    public void testDeleteFile() throws Exception {
        File rootDir = new File(tmpDir, "x/y/z");

        List<File> fileListA = FileLister.findFiles(rootDir);
        assertEquals("file count", 2, fileListA.size());
        assertTrue(fileListA.contains(new File(tmpDir, "x/y/z/d")));
        assertTrue(fileListA.contains(new File(tmpDir, "x/y/z/e")));

        assertTrue("delete file", new File(tmpDir, "x/y/z/d").delete());

        List<File> fileListB = FileLister.findFiles(rootDir);
        assertEquals("file count", 1, fileListB.size());
        assertTrue(fileListB.contains(new File(tmpDir, "x/y/z/e")));

        // we have to clean up here or tearDown will fail
        assertTrue("recreate file", new File(tmpDir, "x/y/z/d").createNewFile());
    }

    public void testCreateFile() throws Exception {
        File rootDir = new File(tmpDir, "x/y/z");

        List<File> fileListA = FileLister.findFiles(rootDir);
        assertEquals("file count", 2, fileListA.size());
        assertTrue(fileListA.contains(new File(tmpDir, "x/y/z/d")));
        assertTrue(fileListA.contains(new File(tmpDir, "x/y/z/e")));

        assertTrue("create file", new File(tmpDir, "x/y/z/f").createNewFile());

        List<File> fileListB = FileLister.findFiles(rootDir);
        assertEquals("file count", 3, fileListB.size());
        assertTrue(fileListB.contains(new File(tmpDir, "x/y/z/d")));
        assertTrue(fileListB.contains(new File(tmpDir, "x/y/z/e")));
        assertTrue(fileListB.contains(new File(tmpDir, "x/y/z/f")));

        // we have to clean up here or tearDown will fail
        assertTrue("delete file", new File(tmpDir, "x/y/z/f").delete());
    }

    public void testNullRoot() throws Exception {
        try {
            FileLister.findFiles(null);
            fail("throw IllegalArgumentException if root is null");
        } catch (IllegalArgumentException e) {
            // success
        }
    }

    public void testNonExistentRoot() throws Exception {
        File rootDir = new File(tmpDir, "not/there");
        assertFalse(rootDir.exists());
        try {
            FileLister.findFiles(rootDir);
            fail("throw FileNotFoundException if the root does not exist");
        } catch (FileNotFoundException e) {
            // make sure the error message contains the missing file's path
            assertTrue(e.getMessage().contains(rootDir.getAbsolutePath()));
        }
    }

    public void testFileAsRoot() throws Exception {
        File rootDir = new File(tmpDir, "x/b");
        try {
            FileLister.findFiles(rootDir);
            fail("throw IllegalArgumentException if the root is a plain file");
        } catch (IllegalArgumentException e) {
            // make sure the error message contains the file's path
            assertTrue(e.getMessage().contains(rootDir.getAbsolutePath()));
        }
    }

}
