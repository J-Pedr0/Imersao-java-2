package br.com.alura.linguagens.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class LinguagemController {

    @Autowired
    private LinguagemRepository repositorio;

    // private List<Linguagem> linguagens = List.of(
    // new Linguagem("Java",
    // "https://raw.githubusercontent.com/abrahamcalf/programming-languages-logos/master/src/java/java_256x256.png",
    // 1),
    // new Linguagem("PHP",
    // "https://raw.githubusercontent.com/abrahamcalf/programming-languages-logos/master/src/php/php_256x256.png",
    // 2)
    // );

    @GetMapping(value = "/linguagem-preferida")
    public String processaLinguagemPreferida() {
        return "Oi, Java!";
    }

    @GetMapping("/linguagens")
    public List<Linguagem> obterLinguagens() {
        List<Linguagem> linguagens = repositorio.findByOrderByRanking();
        return linguagens;
    }

    @PostMapping("/linguagens")
    public ResponseEntity<Linguagem> cadastrarLinguagem(@RequestBody Linguagem lingagem) {
        Linguagem lingagemSalva = repositorio.save(lingagem);
        return new ResponseEntity<>(lingagemSalva, HttpStatus.CREATED) ;
    }

    @GetMapping("/linguagens/{id}")
    public Linguagem obterLinguagemPorId(@PathVariable String id) {
        return repositorio.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/linguagens/{id}")
    public Linguagem atualizarLinguagem(@PathVariable String id, @RequestBody Linguagem lingagem) {
        if (!repositorio.existsById(id)) {
            new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        lingagem.setId(id);
        Linguagem lingagemSalva = repositorio.save(lingagem);
        return lingagemSalva;
    }

    @DeleteMapping("/linguagens/{id}")
    public void excluirLinguagem(@PathVariable String id) {
        repositorio.deleteById(id);
    }

}
