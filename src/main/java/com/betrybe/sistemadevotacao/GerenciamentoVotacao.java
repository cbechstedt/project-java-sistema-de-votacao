package com.betrybe.sistemadevotacao;

import java.util.ArrayList;

/**
 * Define a classe GerenciamentoVotacao.
 */
public class GerenciamentoVotacao implements GerenciamentoVotacaoInterface {

  private ArrayList<PessoaCandidata> pessoasCandidatas = new ArrayList<PessoaCandidata>();
  private ArrayList<PessoaEleitora> pessoasEleitoras = new ArrayList<PessoaEleitora>();
  private ArrayList<String> cpfsComputados = new ArrayList<String>();

  @Override
  public void cadastrarPessoaCandidata(String nome, int numero) {
    for (PessoaCandidata pessoa : pessoasCandidatas) {
      if (pessoa.getNumero() == numero) {
        System.out.println("Número da pessoa candidata já utilizado!");
        return;
      }
    }
    PessoaCandidata newPerson = new PessoaCandidata(nome, numero);
    pessoasCandidatas.add(newPerson);
  }

  @Override
  public void cadastrarPessoaEleitora(String nome, String cpf) {
    for (PessoaEleitora pessoa : pessoasEleitoras) {
      if (pessoa.getCpf() == cpf) {
        System.out.println("Pessoa eleitora já cadastrada!");
        return;
      }
    }
    PessoaEleitora newPerson = new PessoaEleitora(nome, cpf);
    pessoasEleitoras.add(newPerson);
  }

  @Override
  public void votar(String cpfPessoaEleitora, int numeroPessoaCandidata) {
    for (String cpf : cpfsComputados) {
      if (cpf == cpfPessoaEleitora) {
        System.out.println("Pessoa eleitora já votou!");
        return;
      }
    }

    for (PessoaCandidata pessoa : pessoasCandidatas) {
      if (pessoa.getNumero() == numeroPessoaCandidata) {
        pessoa.receberVoto();
        break;
      }
    }
    cpfsComputados.add(cpfPessoaEleitora);
  }

  @Override
  public void mostrarResultado() {
    int totalVotes = cpfsComputados.size();

    if (totalVotes == 0) {
      System.out.println("É preciso ter pelo menos um voto para mostrar o resultado.");
      return;
    }

    for (PessoaCandidata pessoa : pessoasCandidatas) {
      int votesReceived = pessoa.getVotos();
      double votesPercentage = (double) (votesReceived / totalVotes) * 100;
      int votesRounded = (int) Math.round(votesPercentage);

      System.out.printf("Nome: %s - %s votos ( %s%% )", pessoa.getNome(), votesReceived,
          votesRounded);
    }
    System.out.println("Total de votos: " + totalVotes);
  }
}
