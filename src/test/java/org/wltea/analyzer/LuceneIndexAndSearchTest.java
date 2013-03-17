package org.wltea.analyzer;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.RAMDirectory;
import org.apache.lucene.util.Version;
import org.junit.Before;
import org.junit.Test;
import org.wltea.analyzer.lucene.IKAnalyzer;

import java.io.IOException;

import static junit.framework.Assert.assertEquals;

/**
 * @author leegorous
 */
public class LuceneIndexAndSearchTest {

    String fieldName = "text";
    String text = "IK Analyzer是一个结合词典分词和文法分词的中文分词开源工具包。它使用了全新的正向迭代最细粒度切分算法。";

    Analyzer analyzer = null;
    Directory directory = null;

    @Before
    public void setup() throws IOException {
        analyzer = new IKAnalyzer(true);
        directory = new RAMDirectory();

        IndexWriter iWriter = new IndexWriter(directory, new IndexWriterConfig(Version.LUCENE_41, analyzer));

        Document doc = new Document();
        doc.add(new StringField("id", "10000", Field.Store.YES));
        doc.add(new TextField(fieldName, text, Field.Store.YES));
        iWriter.addDocument(doc);

        iWriter.close();
    }

    @Test
    public void testSearch() throws IOException, ParseException {
        IndexReader iReader = DirectoryReader.open(directory);
        IndexSearcher iSearcher = new IndexSearcher(iReader);

        QueryParser parser = new QueryParser(Version.LUCENE_41, fieldName, analyzer);
        parser.setDefaultOperator(QueryParser.Operator.AND);

        String keyword = "中文分词工具包";

        Query query = parser.parse(keyword);


        TopDocs topDocs = iSearcher.search(query, 5);
        assertEquals(1, topDocs.totalHits);

        for(ScoreDoc doc: topDocs.scoreDocs) {
            Document d = iSearcher.doc(doc.doc);
            System.out.println(String.format("%s, %s", d.get("id"), d.get(fieldName)));
        }
    }
}
