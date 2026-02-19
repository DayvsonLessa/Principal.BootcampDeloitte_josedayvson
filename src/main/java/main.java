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
            System.out.println("6 - Cadastrar um ADM"); // NOVA OPÇÃO
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
                    System.out.println("\n--- Cadastrando um Administrador (JPA + Herança) ---");


                    System.out.print("Nome do Admin: ");
                    String nomeAdm = scanner.nextLine();

                    System.out.print("Email do Admin: ");
                    String emailAdm = scanner.nextLine();

                    System.out.print("Setor do Admin: ");
                    String setorAdm = scanner.nextLine();


                    Administrador novoAdmin = new Administrador(null, nomeAdm, emailAdm, setorAdm);

                    service.salvarAdmin(novoAdmin);

                    System.out.println("Admin salvo com sucesso! ID gerado: " + novoAdmin.getId());
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


