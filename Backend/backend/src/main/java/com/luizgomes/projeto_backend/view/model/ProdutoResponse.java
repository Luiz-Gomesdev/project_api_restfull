package com.luizgomes.projeto_backend.view.model;

public class ProdutoResponse {

      //#region Atributos
      private Integer id;
  
      private String nome;
  
      private Integer quantidade;
  
      private Double valor;
      
    //   Caso na response eu não quizesse receber os dados da observação 
    //   private String observacao;
      //#endregion
  
      //#region GEtters and Setters
      public Integer getId() {
          return id;
      }
  
      public void setId(Integer id) {
          this.id = id;
      }
  
      public String getNome() {
          return nome;
      }
  
      public void setNome(String nome) {
          this.nome = nome;
      }
  
      public Integer getQuantidade() {
          return quantidade;
      }
  
      public void setQuantidade(Integer quantidade) {
          this.quantidade = quantidade;
      }
  
      public Double getValor() {
          return valor;
      }
  
      public void setValor(Double valor) {
          this.valor = valor;
      }
  
    //   public String getObservacao() {
    //       return observacao;
    //   }
  
    //   public void setObservacao(String observacao) {
    //       this.observacao = observacao;
    //   }
      //#endregion
    
}
