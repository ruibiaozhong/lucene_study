package com.ruibiaozhong.lucene4.demo2;

import com.ruibiaozhong.lucene4.util.File2DocumentUtils;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.store.RAMDirectory;
import org.junit.Test;

import java.io.IOException;

public class DirectoryTest {

    String dataPath = "E:\\programCode\\idea\\lucene_study\\data\\content.txt";

    String indexPath = "E:\\programCode\\idea\\lucene_study\\index";


    Analyzer analyzer = new StandardAnalyzer();

    @Test
    public void test1() throws IOException {
        Directory directory = FSDirectory.getDirectory(indexPath);

        Document document = File2DocumentUtils.file2Document(dataPath);
        IndexWriter indexWriter = new IndexWriter(directory, analyzer, false, IndexWriter.MaxFieldLength.LIMITED);
        indexWriter.addDocument(document);
        indexWriter.close();
    }

    @Test
    public void test2() throws IOException {

        Directory fsDir = FSDirectory.getDirectory(indexPath);

        //启动时读取
        Directory ramDir = new RAMDirectory(fsDir);

        //添加document
        IndexWriter indexWriter = new IndexWriter(ramDir, analyzer, false, IndexWriter.MaxFieldLength.LIMITED);
        Document document = File2DocumentUtils.file2Document(dataPath);
        indexWriter.addDocument(document);
        indexWriter.close();

        //推出时保存
        IndexWriter fsIndexWriter = new IndexWriter(fsDir, analyzer, false, IndexWriter.MaxFieldLength.LIMITED);
        fsIndexWriter.addIndexesNoOptimize(new Directory[]{ramDir});
        fsIndexWriter.close();



    }


}
