package school.sptech.projetoaula01respostajson;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/pokemon")
public class PokemonController {
   private List <String> pokemon =new ArrayList<>();
   @GetMapping
   public String contagem(){
       return "Total de pokemon cadastrado: "+pokemon.size();
   }

    public boolean existePokemon (int indice){
        if(indice>=0 && indice<pokemon.size()){
            return true;
        }
        return false;
    }
    @GetMapping("/cadastrar/{nome}")
    public String cadastrar(@PathVariable String nome){
       pokemon.add(nome);
       return "Pokemon cadastrado";
    }
    @GetMapping("/recuperar/{indice}")
    public String recuperar(@PathVariable int indice) {
        if (existePokemon(indice)) {
            return pokemon.get(indice);
        }
        return "Pokemon não encontrado";
    }
    @GetMapping("/excluir/{indice}")
    public String excluir (@PathVariable int indice){
         if(existePokemon(indice)){
              pokemon.remove(indice);
              return "Pokemon excluido";
         }
       return "Pokemon não encontrado";
    }
    @GetMapping("atualizar/{indice}/{novoNome}")
    public String atualizar(@PathVariable int indice, @PathVariable String novoNome){
       if(!existePokemon(indice)){
           return "Pokemon não encontrado";
       }
       pokemon.set(indice,novoNome);
       return "Pokemon atualizado";
    }

}

