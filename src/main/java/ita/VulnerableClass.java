package ita;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class VulnerableClass {
  /**
   * Reads or write to a file.
   * @param filename a full path to a file
   * @return if the operation was successful
  */
  public boolean vulnerableMethod(String filename) {
    boolean cont = true;
    Scanner console = new Scanner(System.in);
    while (cont) {
      System.out.print("Digite a operacao desejada para realizar no arquivo "
          + "<R para ler um arquivo, W para escrever em um arquivo> ou Q para sair");

      String opr = console.nextLine();

      if (opr.toUpperCase().equals("R")) {
        BufferedReader br;
        try {
          String currentLine;
          br = new BufferedReader(new FileReader(filename), 2048);
          while ((currentLine = br.readLine()) != null) {
            System.out.println(currentLine);
          }
          br.close();
        } catch (IOException e) {
          e.printStackTrace();
          return false;
        }
      } else if (opr.toUpperCase().equals("W")) {
        BufferedWriter buffWrite;
        try {
          buffWrite = new BufferedWriter(new FileWriter(filename, true), 2048);
          System.out.println("Escreva algo: ");
          String linha = console.nextLine();
          buffWrite.write(linha);
          buffWrite.write("\n");
          buffWrite.close();
        } catch (IOException e) {
          e.printStackTrace();
          return false;
        }
      } else if (opr.toUpperCase().equals("Q")) {
        cont = false;
      } else {
        System.out.println("Please input a valid value");
      }
    }
    return true;
  }
}
