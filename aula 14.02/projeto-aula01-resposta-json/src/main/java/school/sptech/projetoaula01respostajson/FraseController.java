package school.sptech.projetoaula01respostajson;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController //
@RequestMapping("/frases") //
public class FraseController {
    @GetMapping //
    public String fraseBacana(){
        return "Frase super alto astral criativa";
    }

    @GetMapping("/personalizada/{nome}")
    public String personalizada(@PathVariable String nome){
return "Olá "+ nome;
    }
    @GetMapping("/personalizada/{nome}/{sobrenome}")
    public String personalizada2(@PathVariable String nome,@PathVariable String sobrenome){
        return "Olá "+ nome + " "+ sobrenome;
    }
}
