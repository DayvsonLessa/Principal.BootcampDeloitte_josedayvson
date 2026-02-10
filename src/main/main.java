package main;

import service.UsuarioService;
import model.Usuario;
import java.util.Scanner; // Import necessário para ler o teclado

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
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");

            opcao = scanner.nextInt();
            scanner.nextLine(); // Limpa o buffer

            switch (opcao) {
                case 1:
                    System.out.print("Nome: ");
                    String nome = scanner.nextLine();
                    System.out.print("Email: ");
                    String email = scanner.nextLine();
                    service.criarUsuario(nome, email);
                    System.out.println("Usuário criado com sucesso!");
                    break;

                case 2:
                    System.out.println("\n--- Lista de Usuários ---");
                    service.listar().forEach(u ->
                            System.out.println("ID: " + u.getId() + " | Nome: " + u.getNome() + " | Email: " + u.getEmail()));
                    break;

                case 3:
                    System.out.print("Digite o ID para buscar: ");
                    Long idBusca = scanner.nextLong();
                    Usuario encontrado = service.buscarUsuario(idBusca);
                    if (encontrado != null) {
                        System.out.println("Encontrado: " + encontrado.getNome() + " (" + encontrado.getEmail() + ")");
                    } else {
                        System.out.println("Usuário não encontrado.");
                    }
                    break;

                case 4:
                    System.out.print("ID do usuário a atualizar: ");
                    Long idUpd = scanner.nextLong();
                    scanner.nextLine();
                    System.out.print("Novo Nome: ");
                    String novoNome = scanner.nextLine();
                    System.out.print("Novo Email: ");
                    String novoEmail = scanner.nextLine();
                    service.atualizarUsuario(idUpd, novoNome, novoEmail);
                    System.out.println("Usuário atualizado!");
                    break;

                case 5:
                    System.out.print("ID do usuário a remover: ");
                    Long idDel = scanner.nextLong();
                    service.excluirUsuario(idDel);
                    System.out.println("Usuário removido!");
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


