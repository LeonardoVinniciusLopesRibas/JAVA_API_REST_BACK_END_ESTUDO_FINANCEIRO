package unipar.aluno.financeiro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
@RequestMapping("/financeiro")
public class CategoriaController {

    private CategoriaService categoriaService;

    @Autowired
    public CategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    @GetMapping("/getAll")
    public List<Categoria> getAllCategorias() throws NamingException, ValidacaoException, SQLException {
        return categoriaService.findAll();
    }

    @PostMapping("/post")
    public Categoria postCategoria(@RequestBody Categoria categoria) throws NamingException, ValidacaoException, SQLException {
        return categoriaService.save(categoria);
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<Categoria> getById(@PathVariable Long id) throws NamingException, ValidacaoException, SQLException {
        Categoria categoria = categoriaService.findById(id);
        if (categoria == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(categoria);
    }

}
