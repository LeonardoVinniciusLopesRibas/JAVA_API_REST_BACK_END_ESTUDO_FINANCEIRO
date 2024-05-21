package unipar.aluno.financeiro.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import unipar.aluno.financeiro.exception.ValidacaoException;
import unipar.aluno.financeiro.model.Categoria;
import unipar.aluno.financeiro.services.CategoriaService;

import javax.naming.NamingException;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin("*")
@RestController
@RequestMapping("/financeiro/categoria")
public class CategoriaController {

    private CategoriaService categoriaService;

    @Autowired
    public CategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    @GetMapping("/getAll")
    public List<Categoria> getAllCategorias() throws ValidacaoException, SQLException, NamingException, Exception {
        return categoriaService.findAll();
    }

    @PostMapping("/post")
    public ResponseEntity<?> postCategoria(@Valid @RequestBody Categoria categoria, BindingResult bindingResult) throws ValidacaoException, SQLException, NamingException, Exception {
        if (bindingResult.hasErrors()) {
            List<String> errors = bindingResult.getAllErrors().stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.toList());
            return ResponseEntity.badRequest().body(errors);
        }

        try {
            Categoria savedCategoria = categoriaService.save(categoria);
            return ResponseEntity.ok(savedCategoria);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao salvar a categoria");
        }
    }


    @GetMapping("/getById/{id}")
    public ResponseEntity<Categoria> getById(@PathVariable Long id) throws ValidacaoException, SQLException, NamingException, Exception {
        Categoria categoria = categoriaService.findById(id);
        if (categoria == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(categoria);
    }

    @PutMapping("/put/{id}")
    public ResponseEntity<Categoria> put(@PathVariable Long id, @Valid @RequestBody Categoria categoria) throws ValidacaoException, SQLException, NamingException, Exception {
        Categoria categoriaExistente = categoriaService.findById(id);
        if (categoriaExistente == null) {
            return ResponseEntity.notFound().build();
        }

        categoriaExistente.setCategoria(categoria.getCategoria());
        categoriaExistente.setTipo(categoria.getTipo());

        Categoria categoriaAtualizada = categoriaService.save(categoriaExistente);

        return ResponseEntity.ok(categoriaAtualizada);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Categoria> delete(@PathVariable Long id) throws ValidacaoException, SQLException, NamingException, Exception {
        Categoria categoria = categoriaService.findById(id);

        if (categoria == null) {
            return ResponseEntity.notFound().build();
        }

        categoriaService.delete(id);
        return ResponseEntity.ok(categoria);
    }
}