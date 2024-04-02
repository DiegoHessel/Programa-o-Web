package school.sptech.atividadeheroi;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/herois")
public class HeroiController {
    private List<Heroi> herois = new ArrayList<>();

    //serializer transforma o objeto em json
    //deserializer transforma de json para objeto

    //http://localhost:8080/herois/favorito
    @GetMapping("/favorito")
    public Heroi favorito() {
        Heroi heroiFavorito = new Heroi("Homem Sereia", 6000, "nada", 500, true);
        herois.add(heroiFavorito);
        return heroiFavorito;
    }

    //http://localhost:8080/herois
    @GetMapping
    public List<Heroi> herois() {
        return herois;
    }

    //http://localhost:8080/herois/heroi/0
    @GetMapping("/heroi/{indice}")
    public Heroi heroi(@PathVariable int indice) {
        return herois.get(indice);
    }
    //http://localhost:8080/herois/cadastrar/Homem aranha/6000/nada/500/true
    @GetMapping("/cadastrar/{nome}/{forca}/{habilidade}/{idade}/{vivo}")
    public String cadastrar(@PathVariable String nome, @PathVariable int forca, @PathVariable String habilidade, @PathVariable int idade, @PathVariable boolean vivo) {
        Heroi heroi = new Heroi(nome, forca, habilidade, idade, vivo);
        herois.add(heroi);
        return "Herói cadastrado";
    }
    //http://localhost:8080/herois/remover/0
    @GetMapping("/remover/{indice}")
    public String remover(@PathVariable int indice) {
        if (indice >= 0 && indice < herois.size()) {
            herois.remove(indice);
            return "Herói removido";
        }
        return "Herói não encontrado";
    }
//http://localhost:8080/herois/atualizar/0/Homem aranha/nada/50/20/true
    @GetMapping("/atualizar/{indice}/{nome}/{habilidade}/{idade}/{forca}/{vivo}")
    public String atualizar(@PathVariable int indice, @PathVariable String nome, @PathVariable int forca, @PathVariable String habilidade, @PathVariable int idade, @PathVariable boolean vivo) {
        if (indice >= 0 && indice < herois.size()) {
            Heroi heroi = new Heroi(nome, forca, habilidade, idade, vivo);
            herois.set(indice, heroi);
            return "Herói atualizado";
        }
        return "Herói não encontrado";
    }
//
//    @GetMapping("/contagem")
//    public String contagem() {
//        return "Total de heróis cadastrados: " + herois.size();
//    }
//
//    @GetMapping("/existe/{indice}")
//    public String existe(@PathVariable int indice) {
//        if (indice >= 0 && indice < herois.size()) {
//            return "Herói encontrado";
//        }
//        return "Herói não encontrado";
//    }
//
//    @GetMapping("/limpar")
//    public String limpar() {
//        herois.clear();
//        return "Lista de heróis limpa";
//    }
//
//    @GetMapping("/heroi/{indice}/forca")
//    public int forca(@PathVariable int indice) {
//        if (indice >= 0 && indice < herois.size()) {
//            return herois.get(indice).getForca();
//        }
//        return 0;
//    }
//
//    @GetMapping("/heroi/{indice}/habilidade")
//    public String habilidade(@PathVariable int indice) {
//        return null;
//    }
}