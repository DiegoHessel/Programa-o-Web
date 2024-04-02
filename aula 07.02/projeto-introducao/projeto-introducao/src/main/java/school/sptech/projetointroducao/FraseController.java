package school.sptech.projetointroducao;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
//anotação = meta informação
@RestController
@RequestMapping("/frases")
public class FraseController {

    @GetMapping
    public String olaMundo (){
        return "Olá mundo";
    }
    @GetMapping("/tudo-bem")
    public String tudoBem (){
        return "Tudo bem?";
    }
}
