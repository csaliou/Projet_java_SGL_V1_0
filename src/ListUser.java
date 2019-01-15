import java.io.File;
import java.util.Scanner;

public class ListUser {
	Scanner sc;
	public ListUser(Scanner sc) {
		this.sc = sc;
	}
	
	public void showList()
	{
		String [] listefichiers;
		File dirUser = new File(Utilisateur.dirUser);
		int i; 
		listefichiers = dirUser.list(); 
		if (listefichiers == null)
		{
			System.out.println("Il n'y a aucun utilisateur enregistré.");
		}
		else
		{
			for(i = 0; i < listefichiers.length; i++){ 
				Utilisateur user = new Utilisateur(this.sc);
				user.load(listefichiers[i]);
				user.print();
			}
		}
		System.out.println("Appuyez sur une touche pour revenir au menu principal");
		this.sc.nextLine();
	}
}
