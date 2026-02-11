package main;

import service.UsuarioService;
import model.Usuario;
import model.Administrador; // IMPORTANTE: Criar esta classe antes!
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class main {
    public static void main(String[] args) {
        UsuarioService service = new UsuarioService();
        Scanner scanner = new Scanner(System.in);
        int opcao = -1;

        while (opcao != 0) {
            System.out.println("\n===== MENU CRUD USUÁRIO =====");
            System.out.println("1 - Criar Usuário");
            System.out.println("2 - Listar Todos");
            System.out.println("3 - Buscar por ID");
            System.out.println("4 - Atualizar Usuário");
            System.out.println("5 - Remover Usuário");
            System.out.println("6 - Criar um ADM"); // NOVA OPÇÃO
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");

            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    System.out.print("Nome: ");
                    String nome = scanner.nextLine();
                    System.out.print("Email: ");
                    String email = scanner.nextLine();
                    service.criarUsuario(nome, email);
                    break;

                case 2:
                    service.listar().forEach(u ->
                            System.out.println("ID: " + u.getId() + " | Nome: " + u.getNome()));
                    break;

                case 3:
                    System.out.print("ID para buscar: ");
                    Long idBusca = scanner.nextLong();
                    Usuario encontrado = service.buscarUsuario(idBusca);
                    if (encontrado != null) {
                        // Adicionamos o encontrado.getEmail() aqui embaixo:
                        System.out.println("Encontrado: " + encontrado.getNome() + " | Email: " + encontrado.getEmail());
                    } else {
                        System.out.println("Usuário não encontrado.");
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
                    break;

                case 5:
                    System.out.print("ID a remover: ");
                    Long idDel = scanner.nextLong();
                    service.excluirUsuario(idDel);
                    break;

                case 6:
                    System.out.println("\n--- Cadastrando um Administrador (Herança + Banco) ---");

                    System.out.print("Nome do Admin: ");
                    String nomeAdm = scanner.nextLine();

                    System.out.print("Email do Admin: ");
                    String emailAdm = scanner.nextLine();

                    System.out.print("Setor do Admin: ");
                    String setorAdm = scanner.nextLine();

                    // 1. Criamos o objeto na memória
                    Administrador novoAdmin = new Administrador(null, nomeAdm, emailAdm, setorAdm);

                    // 2. SALVAMOS NO BANCO (Isso faz com que o ID seja gerado e ele fique disponível na busca)
                    // O polimorfismo permite passar 'novoAdmin' onde se espera 'Usuario'
                    service.criarUsuario(novoAdmin.getNome(), novoAdmin.getEmail());

                    System.out.println("Administrador salvo no banco de dados com sucesso!");

                    // 3. Demonstração de Polimorfismo em Lista (apenas visual)
                    List<Usuario> listaPolimorfica = new ArrayList<>();
                    listaPolimorfica.add(new Usuario(99L, "Usuário Fixo", "fixo@email.com"));
                    listaPolimorfica.add(novoAdmin);

                    System.out.println("\n--- Percorrendo a Lista (Polimorfismo em Memória) ---");
                    for (Usuario u : listaPolimorfica) {
                        System.out.println("Processando: " + u.getNome());
                        if (u instanceof Administrador) {
                            System.out.println("   > SETOR: " + ((Administrador) u).getSetor());
                        }
                    }
                    break;

                case 0:
                    System.out.println("Saindo...");
                    break;

                default:
                    System.out.println("Opção inválida!");
            }
        }
        scanner.close();
    }
}


