package com.ruibiaozhong.lucene4.demo1;

import com.ruibiaozhong.lucene4.util.File2DocumentUtils;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.queryParser.MultiFieldQueryParser;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.junit.Test;

import java.io.IOException;

public class HelloWorld {


    String dataPath = "E:\\programCode\\idea\\lucene_study\\data\\content.txt";

    String indexPath = "E:\\programCode\\idea\\lucene_study\\index";


    Analyzer analyzer = new StandardAnalyzer();

    @Test
    public void testIndex() throws IOException {
        // file -> doc
        Document document = File2DocumentUtils.file2Document(dataPath);

        //建立索引
        IndexWriter indexWriter = new IndexWriter(indexPath, analyzer, true, IndexWriter.MaxFieldLength.UNLIMITED);
        indexWriter.addDocument(document);
        indexWriter.close();
    }

    @Test
    public void search() throws Exception {
        String queryString = "document";
        queryString = "document";
        String[] fields = {"name", "content"};
        //要搜索的文本解析为Query
        QueryParser queryParser = new MultiFieldQueryParser(fields, analyzer);

        Query query = queryParser.parse(queryString);

        //进行查询
        IndexSearcher indexSearcher = new IndexSearcher(indexPath);

        TopDocs topDocs = indexSearcher.search(query, 10000);
        System.out.println("总共有【" + topDocs.totalHits + "】条匹配结果");

        //打印结果
        for (ScoreDoc scoreDocs : topDocs.scoreDocs) {
            int docSn = scoreDocs.doc; //文档的内部编号
            Document document = indexSearcher.doc(docSn); //根据编号文档找出对应的文档
            File2DocumentUtils.printDocumentInfo(document);
        }





    }






}
















































