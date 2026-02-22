package com.deloitte;

import model.Administrador;
import model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import service.UsuarioService;
import java.util.Scanner;

@SpringBootApplication
@ComponentScan(basePackages = {"service", "repository", "com.deloitte"})
@EntityScan(basePackages = {"model"})
public class BootcampApplication implements CommandLineRunner {

    @Autowired
    private UsuarioService service;

    public static void main(String[] args) {
        // Isso garante que o Spring Boot rode como uma aplicação de console, sem tentar abrir um site
        SpringApplication app = new SpringApplication(BootcampApplication.class);
        app.setWebApplicationType(org.springframework.boot.WebApplicationType.NONE);
        app.run(args);
    }

    @Override
    public void run(String... args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        int opcao = -1;

        while (opcao != 0) {
            System.out.println("\n===== MENU CRUD DELOITTE (SPRING BOOT + JPA MANUAL) =====");
            System.out.println("1 - Criar Usuário");
            System.out.println("2 - Listar Todos");
            System.out.println("3 - Buscar por ID");
            System.out.println("4 - Atualizar Usuário");
            System.out.println("5 - Remover Usuário");
            System.out.println("6 - Cadastrar um ADM");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");

            try {
                if (!scanner.hasNextInt()) {
                    System.out.println("Por favor, digite um número válido.");
                    scanner.next();
                    continue;
                }

                opcao = scanner.nextInt();
                scanner.nextLine();

                switch (opcao) {
                    case 1:
                        System.out.print("Nome: ");
                        String nome = scanner.nextLine();
                        System.out.print("Email: ");
                        String email = scanner.nextLine();
                        service.criarUsuario(nome, email);
                        System.out.println(">>> Usuário criado com sucesso!");
                        break;

                    case 2:
                        System.out.println("\n--- Lista de Usuários ---");
                        service.listar().forEach(u ->
                                System.out.println("ID: " + u.getId() + " | Nome: " + u.getNome() + " | Email: " + u.getEmail()));
                        break;

                    case 3:
                        System.out.print("ID para buscar: ");
                        Long idBusca = scanner.nextLong();
                        Usuario encontrado = service.buscarUsuario(idBusca);
                        if (encontrado != null) {
                            System.out.println(">>> Encontrado: " + encontrado.getNome() + " (Email: " + encontrado.getEmail() + ")");
                        } else {
                            System.out.println(">>> Usuário não encontrado.");
                        }
                        break;

                    case 4:
                        System.out.print("ID a atualizar: ");
                        Long idUpd = scanner.nextLong();
                        scanner.nextLine();
                        System.out.print("Novo Nome: ");
                        String novoNome = scanner.nextLine();
                        System.out.print("Novo Email: ");
                        String novoEmail = scanner.nextLine();
                        service.atualizarUsuario(idUpd, novoNome, novoEmail);
                        System.out.println(">>> Usuário atualizado com sucesso!");
                        break;

                    case 5:
                        System.out.print("ID a remover: ");
                        Long idDel = scanner.nextLong();
                        service.excluirUsuario(idDel);
                        System.out.println(">>> Operação de remoção finalizada.");
                        break;

                    case 6:
                        System.out.println("\n--- Cadastro de Administrador ---");
                        System.out.print("Nome do Admin: ");
                        String nomeAdm = scanner.nextLine();
                        System.out.print("Email do Admin: ");
                        String emailAdm = scanner.nextLine();
                        System.out.print("Setor do Admin: ");
                        String setorAdm = scanner.nextLine();

                        Administrador novoAdmin = new Administrador(null, nomeAdm, emailAdm, setorAdm);
                        service.salvarAdmin(novoAdmin);
                        System.out.println(">>> Administrador salvo com sucesso!");
                        break;

                    case 0:
                        System.out.println("Encerrando o sistema... Até logo!");
                        break;

                    default:
                        System.out.println("Opção inválida! Tente novamente.");
                }
            } catch (Exception e) {
                System.err.println("Ocorreu um erro: " + e.getMessage());
            }
        }
        scanner.close();
    }
}