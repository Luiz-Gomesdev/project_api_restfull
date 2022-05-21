package com.luizgomes.projeto_backend.services;

import java.util.List;
import java.util.Optional;

import com.luizgomes.projeto_backend.model.Produto;
import com.luizgomes.projeto_backend.repository.ProdutoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProdutoService {
    
    @Autowired
    private ProdutoRepository produtoRepository;

    /**
     * Metodo para retornar uma lista de produtos
     * @return Lista de produtos.
     */
    public List<Produto> obterTodos(){
        //ProdutoService depende de produtoRepository para obterTodos
        return produtoRepository.obterTodos();
    }

    /**
     * Metodo que retorna o produto encontrado pelo seu ID.
     * @param id do produto que será localziado.
     * @return Retorna um produto caso seja encontrado.
     */
    public Optional<Produto> obterPorId(Integer id){
        return produtoRepository.obterPorId(id);
    }

    /**
     * Metodo para adicionar produto na lista.
     * @param produto que será adicionado.
     * @return Retorna o produto que foi adicionado na lista.
     */
    public Produto adicionar(Produto produto){
        return produtoRepository.adicionar(produto);
    }

    /**
     * Metodo para deletar o produto por id
     * @param id do produto a ser deletado
     */
    public void deletar(Integer id){
        produtoRepository.deletar(id);
    }

    /**
     * Método para atualizar o produto na lista.
     * @param produto que será atualizado.
     * @param id do produto.
     * @return Retorna o produto após atualizar a lista.
     */
    public Produto atualizar(Integer id, Produto produto){

        // Ter alguma validação no ID.
        produto.setId(id);

        return produtoRepository.atualizar(produto);
    }

}