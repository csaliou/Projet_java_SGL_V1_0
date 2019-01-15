import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Scanner;

public class Session {
	static final String dirSession = "/session/";
	Scanner sc;
	Utilisateur user;
	ArrayList<Exercice> listExercice;
	long dateStart;
	long dateEnd;
	public Session(Scanner sc, Utilisateur user) {
		this.sc = sc;
		this.user = user;
		this.listExercice = new ArrayList<>();
	}
	
	public void startSession() {
		this.dateStart = System.currentTimeMillis();;
		this.loop();
	}
	
	public void loop() {
		boolean finish = false;
		while (!finish)
		{
			System.out.println("Bienvenue sur votre menu session choisissez une option : ");
			System.out.println("1 - lecon de grammaire");
			System.out.println("2 - lecon de conjugaison");
			System.out.println("3 - lecon d'orthographe");
			System.out.println("4 - lecon de vocabulaire");
			System.out.println("5 - deconnection");
			if (this.user != null)
			{
				System.out.println("6 - historique");
				System.out.println("7 - mettre à jour votre profil");
			}
			String numStr = this.sc.nextLine();
			try {
				int num = Integer.parseInt(numStr);
				if (1 <= num && num <= 4)
				{
					int langue = 1;
					if (user != null)
						langue = user.getLangue();
					Exercice exo = new Exercice(sc, langue, num);
					exo.demandeExercice();
					this.listExercice.add(exo);
				}
				else if (num == 5)
				{
					System.out.println("Vous venez de vous deconnecter");
					this.endSession();
					finish = true;
				}
				else if (user != null && num == 6)
				{
					this.afficheHistorique();
				}
				else if (user != null && num == 7)
				{
					user.update();
				}
				else
				{
					System.out.println("Cette commande n'est pas encore gérer");
				}
				
			}
			catch (Exception e)
			{
				System.out.print("Vous devez rentrer un chiffre entre 1 et ");
				if (user == null)
					System.out.println("5");
				else
					System.out.println("7");
			}
		}
	}
	
	public void afficheHistorique() throws IOException
	{
		String [] listefichiers;
		String dirSessionUser = Utilisateur.dirUser + this.user.identifiant + Session.dirSession;
		File dirUser = new File(dirSessionUser);
		int i; 
		listefichiers = dirUser.list(); 
		if (listefichiers != null)
		{
			System.out.println("Il y a " + listefichiers.length + " session d'enregistré");
			for (i = 0; i < listefichiers.length; i++)
			{
				String fileSessionUser = dirSessionUser + listefichiers[i];
				FileInputStream fstream = new FileInputStream(fileSessionUser);
				BufferedReader br = new BufferedReader(new InputStreamReader(fstream));

				String strLine;
				int n = 0;
				long date = 0;
				long duree = 0;
				int langue = 1;
				int typeLecon = 1;
				int numeroExercice;
				ArrayList<Exercice> listExo = new ArrayList<>();
				//Read File Line By Line
				try {
					while ((strLine = br.readLine()) != null)   {
						if (n == 0)
							date = Long.parseLong(strLine);
						else if (n == 1)
							duree = Long.parseLong(strLine);
						else if (n - 2 >= 0)
						{
							if ((n - 2) % 3 == 0)
								langue = Integer.parseInt(strLine);
							else if ((n - 2) % 3 == 1)
								typeLecon = Integer.parseInt(strLine);
							else
							{
								numeroExercice = Integer.parseInt(strLine);
								Exercice exo = new Exercice(sc, langue, typeLecon);
								exo.setNumExo(numeroExercice);
								listExo.add(exo);
							}
						}
						n++;
					}
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				//Close the input stream
				br.close();
				Timestamp stamp = new Timestamp(date);
				  Date dated = new Date(stamp.getTime());
				System.out.println("La session daté du " + dated + " a durée " + duree / 1000 + " secondes");
				if (listExo.size() == 0)
				{
					System.out.println("Il y a eu aucun d'exercice de fait");
				}
				else
				{
					System.out.println("Il y a eu " + listExo.size() + " d'exercice de fait");
					for(n = 0; n < listExo.size(); n++)
					{
						listExo.get(n).print();
					}
				}
			}
		}
		else
		{
			System.out.println("Il y a aucune session d'enregistré.");
		}
		System.out.println("Appuyez sur une touche pour revenir au menu session");
		this.sc.nextLine();
	}
	
	public void endSession() {
		this.dateEnd = System.currentTimeMillis();;
		if (this.user != null)
			this.save();
	}
	
	public void save() {
		String dirSession = Utilisateur.dirUser + this.user.getIdentifiant() + Session.dirSession;
		File file = new File(dirSession);
		if (!file.exists())
		{
			file.mkdirs();
		}
		boolean find = false;
		int i = 1;
		while (!find)
		{
			String nameFile = dirSession + "session-" + i + ".txt";
			File fileData = new File(nameFile);
			if (!fileData.exists())
			{
				try 
				{
					fileData.createNewFile();
					find = true;
					try(  PrintWriter out = new PrintWriter( nameFile )  )
					{
						long duree = this.dateEnd - this.dateStart;
						out.println(this.dateStart);
						out.println(duree);
						int n = 0;
						while (n < this.listExercice.size())
						{
							System.out.println("Exo ajouté");
							this.listExercice.get(n).writeExoInFile(out);
							n++;
						}
				    
					}
					catch (Exception e)
					{
					
					}
				}
				catch (IOException e)
				{
			
				}
			}
			i++;
		}
	}

}
