package br.com.empresa.sgt.teste;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class GeraTabelas {

  public static void main(String[] args) {
    EntityManagerFactory factory = Persistence.
          createEntityManagerFactory("SGTDB");

    factory.close();
  }	
}