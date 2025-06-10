package br.com.aeviagens.backend.domain;

public enum Local {
  CANOAS("Canoas"),
  ROTA_80_PORTO_ALEGRE("Graal Rota 80, Porto Alegre"),
  PORTO_ALEGRE("Porto Alegre"),
  FLORIANOPOLIS("Florianópolis");

  public final String nome;
  public String descricao;

  private Local(String nome) {
    this.nome = nome;
  }

  public Local comDescricao(String descricao) {
    this.descricao = descricao;
    return this;
  }

  public String mostrarConteudo() {
    if(descricao == null || descricao.isBlank()) {
      return nome;
    }

    return nome + " * "+ descricao;

  }

}
