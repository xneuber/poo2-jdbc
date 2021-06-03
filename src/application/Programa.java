package application;

import java.io.IOException;
import java.util.Date;
import java.util.Scanner;
import entities.Aluno;
import jdbc.AlunoJDBC;

public class Programa {

	public static void main(String[] args) {
		
        try {
        	
            int opcao = 0;
            Scanner console = new Scanner(System.in);
            
            do {
            	clrscr();
                System.out.print("######## Menu ########"
                                 + "\n1- Cadastrar"
                                 + "\n2- Listar"
                                 + "\n3- Alterar"
                                 + "\n4- Excluir"
                                 + "\n5- Sair");
                System.out.print("\n\tOpção: ");
                opcao = Integer.parseInt(console.nextLine());
                System.out.println("\n");
                
                if (opcao == 1){
                    
                    Aluno a = new Aluno();
                    AlunoJDBC acao = new AlunoJDBC();
                    
                    System.out.print("\n*** Cadastrar Aluno ***\n\r");
                    System.out.print("Nome: ");
                    a.setNome(console.nextLine());
                    System.out.print("Sexo: ");
                    a.setSexo(console.nextLine());
                    System.out.print("Data de nascimento: ");
                    a.setDt_nasc(new Date(console.nextLine()));
                    
                    acao.salvar(a);
                    console.nextLine();
                    System.out.println("\n");
                }
                
                if (opcao == 2) {
                	System.out.print("\n*** Listar Aluno ***\n\r");
                	AlunoJDBC acao = new AlunoJDBC();
                	acao.listar();
                	console.nextLine();
                	 System.out.println("\n");
                }
               
                if (opcao == 3) { 
                	 Aluno a = new Aluno();
                     AlunoJDBC acao = new AlunoJDBC();
                     
                     System.out.print("\n*** Alterar Aluno ***\n\r");
                     System.out.print("ID: ");
                     a.setId(Integer.parseInt(console.nextLine()));
                     System.out.print("Nome: ");
                     a.setNome(console.nextLine());
                     System.out.print("Sexo: ");
                     a.setSexo(console.nextLine());
                     System.out.print("Data de nascimento: ");
                     a.setDt_nasc(new Date(console.nextLine()));
                     
                     acao.alterar(a);
                     console.nextLine();
                     System.out.println("\n");
                }
                
                if (opcao == 4) {
                	AlunoJDBC acao = new AlunoJDBC();
                	System.out.print("\n*** Excluir Aluno ***\n\r");
                    System.out.print("ID: ");
                    acao.apagar(Integer.parseInt(console.nextLine()));
                    console.nextLine();
                    System.out.println("\n");
                }
            } while(opcao != 5);
        } catch (Exception e){
            System.out.println("Erro: " + e);
        }
        
	}
	
	public static void clrscr(){
	    //Clears Screen in java
		System.out.print("\b") ;
	}
}