import java.util.Scanner;

public class MenuPrincipal {
	Scanner sc;
	public MenuPrincipal(Scanner sc) {
		// TODO Auto-generated constructor stub
		this.sc = sc;
		boolean finish = false;
		while (!finish) {
			System.out.println("Bienvenue sur le menu principal, voici vos options :");
			System.out.println("1 - Créer un compte");
			System.out.println("2 - Se connecter");
			System.out.println("3 - Connexion annonyme");
			System.out.println("4 - Quitter le programme");
			System.out.println("5 - Liste de tous les utilisateurs");
			String str = this.sc.nextLine();
			switch(str)
			{
			case "1":
				Utilisateur util = new Utilisateur(this.sc);
				util.creer();
				break;			
			case "2":
				Connexion con = new Connexion(this.sc);
				con.connexion();
				break;			
			case "3":
				Session session = new Session(this.sc, null);
				session.startSession();
				break;			
			case "4":
				System.out.println("A la prochaine.");
				finish = true;
				break;			
			case "5":
				ListUser listUser = new ListUser(this.sc);
				listUser.showList();
				break;
			default:
				System.out.println("Il faut taper un chiffre entre 1 et 5");
			}
//			System.out.println("mot taper : " + str);
		}
	}
}
