package br.com.aeviagens.backend.domain;

public enum Local {
  CANOAS("Canoas"),
  PORTO_ALEGRE("Porto Alegre"),
  FLORIANOPOLIS("Florianópolis");

  public String nome;
  public String descricao;

  private Local(String nome) {
    this.nome = nome;
  }

  public Local comDescricao(String descricao) {
    this.descricao = descricao;
    return this;
  }

  public String mostrarConteudo() {
    if(descricao.isBlank()) {
      return nome;
    }

    return nome + " * "+ descricao;

  }

}
