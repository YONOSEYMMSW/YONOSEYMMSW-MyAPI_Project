package BSU.EDU.cs;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ArticleDataFetcherTest {

    private static final String VALID_ARTICLE = "Zappa";
    private static final String INVALID_ARTICLE = "Null";
    private ArticleDataFetcher dataFetcher;

    @BeforeEach
    void setUp() {
        dataFetcher = new ArticleDataFetcher(); // Initialize the object before each test
    }

    @Test
    void testFetchArticleData_ValidArticle() {
        // Test fetching valid article data
        String result = assertDoesNotThrow(() -> dataFetcher.fetchArticleData(VALID_ARTICLE),
                "Fetching data for a valid article should not throw exceptions");
        assertNotNull(result, "Article data should not be null for a valid article");
        assertFalse(result.isEmpty(), "Article data should not be empty for a valid article");
    }

    @Test
    void testFetchArticleData_InvalidArticle() {
        // Test fetching invalid article data
        assertThrows(RuntimeException.class,
                () -> dataFetcher.fetchArticleData(INVALID_ARTICLE),
                "Fetching data for an invalid article should throw a RuntimeException");
    }
}
