package school.sptech.marketplaceresumido.service.produto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.junit.jupiter.MockitoExtension;
import school.sptech.marketplaceresumido.data.ProdutoRepository;
import school.sptech.marketplaceresumido.entity.Produto;
import school.sptech.marketplaceresumido.exception.EntidadeNaoEncontradaException;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class ProdutoServiceTest {

    @Mock
    private ProdutoRepository repository;
    @InjectMocks
    private ProdutoService service;

    @Test
    @DisplayName("Teste correto se, ao chamar o listar () retorna uma lista de 2 produtos")
    void cenarioCorreto1() {
        // given

        List<Produto> produtos = List.of(
                new Produto(1, "Bolacha", "Bolacha boa", "123", 10.0),
                new Produto(2, "Bolacha", "Bolacha botima", "321", 5.0)
        );

        // when
        when(repository.findAll()).thenReturn(produtos);

        // then
        List<Produto> resposta = service.listar();
        // assert
        assertEquals(2, resposta.size());
        verify(repository, times(1)).findAll();
    }

    @Test
    @DisplayName("Teste incorreto se, ao chamar o listar () retorna uma lista vazia")
    void cenarioIncorreto1() {
        // given
        var produtos = new ArrayList<Produto>();

        // when
        when(repository.findAll()).thenReturn(produtos);
        // then
        List<Produto> resposta = service.listar();
        // assert
        assertTrue(resposta.isEmpty());

    }

    @Test
    @DisplayName("teste correto se, ao chamar o buscaPorId retorna um produto")
    void cenarioCorreto2() {
        // given
        Produto produto = new Produto(1, "Bergamota", "Mexerica do sul", "123", 4.0);
        int idInformado = 1;
        // when
        when(repository.findById(idInformado)).thenReturn(java.util.Optional.of(produto));
        // then
        Produto resposta = service.buscaPorId(idInformado);
        // assert
        assertEquals(produto, resposta);
        verify(repository, times(1)).findById(1);
        verify(repository, times(0)).findAll();
    }

    @Test
    @DisplayName("teste incorreto se, ao chamar o buscaPorId retorna uma exception")
    void cenarioIncorreto2() {
        // given
        // int idInformado = 1; casso não queira deixar explicito
        // when
        // add any() para "literalmente" qualquer coisa
        when(repository.findById(any())).thenReturn(java.util.Optional.empty());

        // then// assert
        assertThrows(EntidadeNaoEncontradaException.class, () -> service.buscaPorId(1));

        // assert
        verify(repository, times(1)).findById(any());
        verify(repository, times(0)).findAll();
    }

    @Test
    @DisplayName("Dado que, vou salvar no banco e retorna o objeto com o id")
    void cenarioSavaCorreto() {
        // given
        Produto produtoParaSalvar = new Produto(null, "Bergamota", "Mexerica do suldeste", "123", 30.0);
        Produto produtoSalvo = new Produto(1, "Bergamota", "Mexerica do suldeste", "123", 30.0);
        // when
        when(repository.save(produtoParaSalvar)).thenReturn(produtoSalvo);
        // then
        Produto produtoResposta = service.salvar(produtoParaSalvar);
        // assert
        assertNotNull(produtoResposta.getId());
        assertEquals(produtoParaSalvar.getNome(), produtoResposta.getNome());
        assertEquals(produtoParaSalvar.getDescricao(), produtoResposta.getDescricao());
        verify(repository, times(1)).save(produtoParaSalvar);
        verify(repository, times(0)).findById(any());
    }

    @Test
    @DisplayName("Dado que, passei um id valido, salve o objeto adualizado e retorne o objeto atualizado")
    void cenarioAtualizaCorreto() {
        // given
        Produto produtoParaAtualizar = new Produto(null, "Robson", "era para ser algo ", "123", 25.0);
        Produto produtoAtualizado = new Produto(1, "Robson", "era para ser algo ", "123", 25.0);
        Integer id = 1;

        // when
        when(repository.existsById(id)).thenReturn(Boolean.TRUE);
        when(repository.save(produtoParaAtualizar)).thenReturn(produtoAtualizado);
        // then
        Produto produtoResposta = service.atualizar(id, produtoParaAtualizar);

        // assert
        assertEquals(produtoParaAtualizar.getNome(), produtoResposta.getNome());
        assertEquals(produtoParaAtualizar.getPreco(), produtoResposta.getPreco());

        verify(repository, times(1)).existsById(id);
        verify(repository, times(0)).findById(id);
        verify(repository, times(0)).findAll();
        verify(repository, times(1)).save(produtoParaAtualizar);
    }

    @Test
    @DisplayName("Dado que, tenha um id, ao atualizar, não existe o id no banco")
    void cenarioAtualizaIncorreto() {
        when(repository.existsById(any())).thenReturn(Boolean.FALSE);
        assertThrows(EntidadeNaoEncontradaException.class, () -> service.atualizar(1, null));
        verify(repository, times(1)).existsById(any());
        verify(repository, times(0)).findById(any());
        verify(repository, times(0)).findAll();
    }
}