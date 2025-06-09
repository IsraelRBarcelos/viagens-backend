package br.com.aeviagens.backend.domain;

import java.util.ArrayList;

public class Participantes {
  public ArrayList<String> lista;

  public Participantes(ArrayList<String> lista) {
    this.lista = lista;
  }

  public ArrayList<String> getLista() {
    return this.getLista();
  }

  public boolean isNotEmpty() {
    return !this.lista.isEmpty();
  }
}
