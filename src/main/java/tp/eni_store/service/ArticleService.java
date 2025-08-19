package tp.eni_store.service;

import org.springframework.cache.support.NullValue;
import org.springframework.stereotype.Service;
import tp.eni_store.bo.Article;
import tp.eni_store.dao.jpa.mock.ArticleDAO;

import java.util.List;

@Service
public class ArticleService {
    private final ArticleDAO articleDAO;

    public ArticleService(ArticleDAO articleDAO) {
        this.articleDAO = articleDAO;
    }

    public ServiceResponse<List<Article>> getAll(){
        return new ServiceResponse<>(ServiceCodes.SUCCESS, articleDAO.getArticles());
    }

    public ServiceResponse<Article> getById(int id){
        Article article = articleDAO.getArticles().stream().filter(art -> art.id == id).findFirst().orElse(null);

        if(article == null){
            return new ServiceResponse<>(ServiceCodes.NOT_FOUND, null);
        }
        return new ServiceResponse<>(ServiceCodes.SUCCESS, article);
    }

    public ServiceResponse<NullValue> delete(int id){
        Article article = articleDAO.getArticle(id);

        if(article == null) {
            return new ServiceResponse<>(ServiceCodes.NOT_FOUND, null);
        }

        List<Article> articles = articleDAO.getArticles();
        articles.removeIf(articl -> articl.id == id);
        return new ServiceResponse<>(ServiceCodes.SUCCESS, null);
    }

    public ServiceResponse<Article> save(Article article){
        List<Article> articles = articleDAO.getArticles();
        Article match = articleDAO.getArticle(article.id);

        if(match != null) {
            articles.set(match.id, article);
            return new ServiceResponse<>(ServiceCodes.UPDATED, article);
        }

        articles.add(article);
        return new ServiceResponse<>(ServiceCodes.SUCCESS, article);
    }
}
