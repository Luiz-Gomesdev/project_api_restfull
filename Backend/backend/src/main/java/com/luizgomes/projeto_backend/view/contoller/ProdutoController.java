package com.luizgomes.projeto_backend.view.contoller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.luizgomes.projeto_backend.model.Produto;
import com.luizgomes.projeto_backend.services.ProdutoService;
import com.luizgomes.projeto_backend.shared.ProdutoDTO;
import com.luizgomes.projeto_backend.view.model.ProdutoRequest;
import com.luizgomes.projeto_backend.view.model.ProdutoResponse;

import org.apache.catalina.mapper.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @GetMapping
    public ResponseEntity<List<ProdutoResponse>> obterTodos(){
        List<ProdutoDTO> produtos = produtoService.obterTodos();

        ModelMapper mapper = new ModelMapper();

        List<ProdutoResponse> resposta = produtos.stream()
            .map(produtoDto -> mapper.map(produtoDto, ProdutoResponse.class))
            .collect(Collectors.toList());

            return new ResponseEntity<>(resposta, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<ProdutoResponse>> obterPorId(@PathVariable Integer id){

        Optional<ProdutoDTO> dto = produtoService.obterPorId(id);

        ProdutoResponse produto = new ModelMapper().map(dto.get(), ProdutoResponse.class);

        return new ResponseEntity<>(Optional.of(produto), HttpStatus.OK);
        
    }

    @PostMapping
    public ResponseEntity<ProdutoResponse> adicionar(@RequestBody ProdutoRequest produtoReq){
        ModelMapper mapper = new ModelMapper();

        // Recebo um ProdutoRequest e converto em um ProdutoDTO
        ProdutoDTO produtoDto = mapper.map(produtoReq, ProdutoDTO.class);
        
        // Depois mando o meu servi??o salvar o produtoDto
        produtoDto = produtoService.adicionar(produtoDto);

        // Converto por fim o produtoDto em um ProdutoResponse com o Status Code
        return new ResponseEntity<>(mapper.map(produtoDto, ProdutoResponse.class), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable Integer id){
        produtoService.deletar(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProdutoResponse> atualizar(@RequestBody ProdutoRequest produtoReq, @PathVariable Integer id){

        ModelMapper mapper = new ModelMapper();
        // Atualizar recebe um ProdutoRequest e converto em um ProdutoDTO
        ProdutoDTO produtoDto = mapper.map(produtoReq, ProdutoDTO.class);

        // Depois mando o meu servi??o atualizar j?? com o id correto
        produtoDto = produtoService.atualizar(id, produtoDto);

        // Converto por fim o produtoDto em um ProdutoResponse
        return new ResponseEntity<>(
            mapper.map(produtoDto, ProdutoResponse.class),
            HttpStatus.OK);
    }
    
}
