package school.sptech.marketplaceresumido.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CalculadoraServiceTest {

    @Test
    @DisplayName("Teste correto se, ao passar 100.0 retorna 18.0")
    void cenarioCorreto1() {
        //given

        //var calculadoraService = new CalculadoraService();
        CalculadoraService service = new CalculadoraService();
        Double resltado = 18.0;
        Double valorInformado = 100.0;

        // then

        Double resultadoInformado = service.colculoIcms(valorInformado);
        //assert
        Assertions.assertEquals(resltado, resultadoInformado);
    }

    @Test
    @DisplayName("Teste incorreto se, passar null, deve retornar uma exception")
    void cenarioIncorreto1() {
        // given
        CalculadoraService service = new CalculadoraService();
        Double valorInformado = null;

        //then/assert
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> service.colculoIcms(valorInformado));

        //assert
        Assertions.assertEquals("O Valor não pode ser null", exception.getMessage());
    }

    @Test
    @DisplayName("Teste incorreto se, passar 0.0, deve retornar uma exception")
    void cenarioincorreto2() {
        //given
        CalculadoraService service = new CalculadoraService();
        Double valorInformado = 0.0;
        //then/assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> service.colculoIcms(valorInformado));
    }

    @Test
    @DisplayName("Teste incorreto se, passar valor negativo, deve retornar uma exception")
    void cenarioIncorreto3() {
        //given
        CalculadoraService service = new CalculadoraService();
        Double valorInformado = -100.0;
        //then/assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> service.colculoIcms(valorInformado));
    }
}
