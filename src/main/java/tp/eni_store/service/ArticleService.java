package tp.eni_store.service;

import org.springframework.cache.support.NullValue;
import org.springframework.stereotype.Service;
import tp.eni_store.bo.Article;
import tp.eni_store.dao.jpa.mock.ArticleDAO;
import tp.eni_store.locale.LocaleHelper;

import java.util.List;

@Service
public class ArticleService {
    private final LocaleHelper localeHelper;
    private final ArticleDAO articleDAO;

    public ArticleService(ArticleDAO articleDAO, LocaleHelper localeHelper) {
        this.articleDAO = articleDAO;
        this.localeHelper = localeHelper;
    }

    public ServiceResponse<List<Article>> getAll(){
        return new ServiceResponse<>(ServiceCodes.SUCCESS, localeHelper.i18n("article.service.getAll.success"), articleDAO.getArticles());
    }

    public ServiceResponse<Article> getById(int id){
        Article article = articleDAO.getArticles().stream().filter(art -> art.id == id).findFirst().orElse(null);

        if(article == null){
            return new ServiceResponse<>(ServiceCodes.NOT_FOUND, localeHelper.i18n("article.service.getById.error"), null);
        }
        return new ServiceResponse<>(ServiceCodes.SUCCESS, localeHelper.i18n("article.service.getById.success"), article);
    }

    public ServiceResponse<NullValue> delete(int id){
        Article article = articleDAO.getArticle(id);

        if(article == null) {
            return new ServiceResponse<>(ServiceCodes.NOT_FOUND,localeHelper.i18n("article.service.delete.error"), null);
        }

        List<Article> articles = articleDAO.getArticles();
        articles.removeIf(articl -> articl.id == id);
        return new ServiceResponse<>(ServiceCodes.SUCCESS, localeHelper.i18n("article.service.delete.success"), null);
    }

    public ServiceResponse<Article> save(Article article){
        List<Article> articles = articleDAO.getArticles();
        Article match = articleDAO.getArticle(article.id);

        if(match != null) {
            articles.set(match.id, article);
            return new ServiceResponse<>(ServiceCodes.UPDATED,localeHelper.i18n("article.service.save.update"), article);
        }

        articles.add(article);
        return new ServiceResponse<>(ServiceCodes.SUCCESS, localeHelper.i18n("article.service.save.create"), article);
    }
}
