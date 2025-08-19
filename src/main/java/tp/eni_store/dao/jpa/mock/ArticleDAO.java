package tp.eni_store.dao.jpa.mock;

import org.springframework.stereotype.Component;
import tp.eni_store.bo.Article;

import java.util.ArrayList;
import java.util.List;

@Component
public class ArticleDAO {
    List<Article> articles;

    ArticleDAO() {
        articles = new ArrayList<>();
        for(int i = 0; i < 10; i++) {
            Article article = new Article(i, "article num " + i);
            articles.add(article);
        }
    }

    public List<Article> getArticles() {
        return articles;
    }

    public Article getArticle(int id) {
        return articles.stream().filter(article -> article.id == id).findFirst().orElse(null);
    }

}
