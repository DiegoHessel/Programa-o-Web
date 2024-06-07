package school.sptech.marketplaceresumido.service;

public class CalculadoraService {
    public Double colculoIcms(Double preco) {
        if (preco == null) {
            throw new IllegalArgumentException("O Valor não pode ser null");
        }
        if (preco == 0) {
            throw new IllegalArgumentException("O Valor não pode ser 0");
        }
        if (preco < 0) {
            throw new IllegalArgumentException("O Valor não pode ser negativo");
        }
        return preco * 0.18;
    }
}
