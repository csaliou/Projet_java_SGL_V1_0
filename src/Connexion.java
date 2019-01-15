import java.util.Scanner;

public class Connexion {
	Scanner sc;
	public Connexion(Scanner sc) {
		this.sc = sc;
	}
	public void connexion()
	{
		boolean bonId = false;
		System.out.println("Veuillez indiquer votre identifiant :");
		Utilisateur user = new Utilisateur(this.sc);
		int nbError = 0;
		while (!bonId)
		{
			String id = this.sc.nextLine();
			if (id.isEmpty())
			{
				System.out.println("Votre identifiant ne peut être vide.");
			}
			else
			{
				if (user.load(id) == true)
				{
					Session session = new Session(this.sc, user);
					session.startSession();
					bonId = true;
				}
				else
				{
					System.out.println("Cet identifiant n'existe pas.");
					nbError += 1;
					if (nbError >= 3)
					{
						System.out.println("Vous avez raté 3 fois vous êtes deconnecté.");
						return ;
					}
				}
			}
		}
	}
}
