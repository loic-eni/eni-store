package tp.eni_store.rest.v1;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.Operation;
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
    @Operation(summary = "Récuperer tous les articles.")
    public ServiceResponse<List<Article>> getAllArticles() {
        return articleService.getAll();
    }

    @GetMapping("get/{id}")
    @Operation(summary = "Récuperer un article par son ID.")
    public ServiceResponse<Article> getArticleById(@PathVariable String id) {
        return articleService.getById(Integer.parseInt(id));
    }

    @PostMapping("save")
    @Operation(summary = "Enregistrer un article. Si ID existant, alors c'est une modification, sinon une création.")
    public ServiceResponse<Article> saveArticle(@RequestBody Article article) {
        return articleService.save(article);
    }

    @DeleteMapping("delete/{id}")
    @Operation(summary = "Effacer un article par son ID")
    public ServiceResponse<NullValue> deleteArticle(@PathVariable String id) {
        return articleService.delete(Integer.parseInt(id));
    }
}
