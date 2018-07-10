package com.ruibiaozhong.lucene4.util;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.NumberTools;

import java.io.*;

public class File2DocumentUtils {


    /**
     *  文件name,content, size, path
     * @param path
     * @return
     */
    public static Document file2Document(String path) {
        File file = new File(path);
        Document document = new Document();
        document.add(new Field("name", file.getName(), Field.Store.YES, Field.Index.ANALYZED));
        document.add(new Field("content", readFileContent(file), Field.Store.YES, Field.Index.ANALYZED));
        document.add(new Field("size", NumberTools.longToString(file.length()), Field.Store.YES, Field.Index.NOT_ANALYZED));
        document.add(new Field("path", path, Field.Store.YES, Field.Index.NOT_ANALYZED));
        return document;
    }


    public static String readFileContent(File file) {
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
            StringBuilder stringBuilder = new StringBuilder();
            for (String line = null; null != (line = bufferedReader.readLine()); ) {
                stringBuilder.append(line).append("\n");
            }
            return stringBuilder.toString();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public static void printDocumentInfo(Document document) {
        System.out.println("---------------------------------");
        System.out.println("name   = " + document.get("name"));
        System.out.println("content   = " + document.get("content"));
        System.out.println("size   = " + document.get("size"));
        System.out.println("path   = " + document.get("path"));

    }






}
