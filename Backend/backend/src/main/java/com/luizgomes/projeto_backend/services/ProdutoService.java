package com.luizgomes.projeto_backend.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.luizgomes.projeto_backend.model.Produto;
import com.luizgomes.projeto_backend.model.exception.ResourceNotFoundException;
import com.luizgomes.projeto_backend.repository.ProdutoRepository;
import com.luizgomes.projeto_backend.shared.ProdutoDTO;

import org.modelmapper.ModelMapper;
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
    public List<ProdutoDTO> obterTodos(){

        // Retorna uma lista de produto model.
        List<Produto> produtos = produtoRepository.findAll();

        return produtos.stream()
            .map(produto -> new ModelMapper().map(produto, ProdutoDTO.class))
            .collect(Collectors.toList());
    }

    /**
     * Metodo que retorna o produto encontrado pelo seu ID.
     * @param id do produto que será localziado.
     * @return Retorna um produto caso seja encontrado.
     */
    public Optional<ProdutoDTO> obterPorId(Integer id){
        // Obtendo optional de produto pelo id.
        Optional<Produto> produto = produtoRepository.findById(id);

        // Se não encontrar, lança exception (isEmpty = está vazia)
        if (produto.isEmpty()){
            throw new ResourceNotFoundException("Produto com id: " + id + " não encontrado");
        }

        // Convertendo o meu optional de produto em um produtoDTO
        ProdutoDTO dto = new ModelMapper().map(produto.get(), ProdutoDTO.class);

        // Criando e retornando um optional de produtoDto.
        return Optional.of(dto);
    }

    /**
     * Metodo para adicionar produto na lista.
     * @param produto que será adicionado.
     * @return Retorna o produto que foi adicionado na lista.
     */
    public ProdutoDTO adicionar(ProdutoDTO produtoDto){
        produto.setId(null);
        return produtoRepository.save(produto);
    }

    /**
     * Metodo para deletar o produto por id
     * @param id do produto a ser deletado
     */
    public void deletar(Integer id){
        produtoRepository.deleteById(id);
    }

    /**
     * Método para atualizar o produto na lista.
     * @param produto que será atualizado.
     * @param id do produto.
     * @return Retorna o produto após atualizar a lista.
     */
    public ProdutoDTO atualizar(Integer id, Produto produtoDTO){

        // Ter alguma validação no ID.
        produto.setId(id);

        return produtoRepository.save(produto);
    }

}