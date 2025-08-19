package tp.eni_store.rest.v1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.support.NullValue;
import org.springframework.web.bind.annotation.*;
import tp.eni_store.bo.Article;
import tp.eni_store.service.ArticleService;
import tp.eni_store.service.ServiceResponse;

import java.util.List;

@RestController
@RequestMapping("api/articles/")
public class ArticleRestController {
    @Autowired
    ArticleService articleService;

    @GetMapping("all")
    public ServiceResponse<List<Article>> getAllArticles() {
        return articleService.getAll();
    }

    @GetMapping("get/{id}")
    public ServiceResponse<Article> getArticleById(@PathVariable String id) {
        return articleService.getById(Integer.parseInt(id));
    }

    @PostMapping("save")
    public ServiceResponse<Article> saveArticle(@RequestBody Article article) {
        return articleService.save(article);
    }

    @PostMapping("delete/{id}")
    public ServiceResponse<NullValue> deleteArticle(@PathVariable String id) {
        return articleService.delete(Integer.parseInt(id));
    }
}
