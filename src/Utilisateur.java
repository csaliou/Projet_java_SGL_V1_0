import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Scanner;

public class Utilisateur {
	static final String dirUser = "user/";
	Scanner sc;
	String identifiant;
	int age;
	int genre;
	int langue;
	public Utilisateur(Scanner sc) {
		// TODO Auto-generated constructor stub
		this.sc = sc;
	}
	
	public void creer() {
		this.demandeIdentifiant();
		this.demandeAge();
		this.demandeGenre();
		this.demandeLangue();
		this.save();
		this.redirigeFinCreation();
	}
	
	public void redirigeFinCreation()
	{
		boolean finish = false;
		System.out.println("Votre compte a bien été créé voulez vous ouvrir une session(1) ou revenir au menu principal(2) ?");
		
		while (!finish)
		{
			String str = this.sc.nextLine();
			switch(str)
			{
			case "1":
				Session session = new Session(this.sc, this);
				session.startSession();
				finish = true;
				break;			
			case "2":
				finish = true;
				break;			
			default:
				System.out.println("Il faut taper un chiffre entre 1 et 2");
			}
		}
	}
	
	public void update() {
		this.demandeAge();
		this.demandeGenre();
		this.demandeLangue();
		this.save();
	}
	
	public boolean load(String id)
	{
		this.identifiant = id;
		String fileUserData = Utilisateur.dirUser + this.identifiant + "/data.txt";
		File fileData = new File(fileUserData);
		if (!fileData.exists())
		{
			return false;
		}
		try 
		{
			FileInputStream fstream = new FileInputStream(fileUserData);
			BufferedReader br = new BufferedReader(new InputStreamReader(fstream));

			String strLine;
			int i = 0;
			//Read File Line By Line
			while ((strLine = br.readLine()) != null)   {
				if (i == 0)
					this.age = Integer.parseInt(strLine);
				else if (i == 1)
					this.genre = Integer.parseInt(strLine);
				else if (i == 2)
					this.langue = Integer.parseInt(strLine);
				i++;
			}
			//Close the input stream
			br.close();
		}
		catch (IOException e)
		{
			
		}
		return true;
	}
	
	public void save() {
		String dirUser = Utilisateur.dirUser + this.identifiant;
		File file = new File(dirUser);
		if (!file.exists())
		{
			file.mkdirs();
		}
		String fileUserData = dirUser + "/data.txt";
		File fileData = new File(fileUserData);
		if (!fileData.exists())
		{
			try {
				fileData.createNewFile();
			}
			catch (IOException e){
			}
		}
		try(  PrintWriter out = new PrintWriter( fileUserData )  ){
			out.println(this.age);
			out.println(this.genre);
			out.println(this.langue);
		    
		}
		catch (Exception e)
		{
			
		}
	}
	
	public void print() {
		
		System.out.print("L'utilisateur " + this.identifiant + " est un");
		if (this.genre == 1)
			System.out.print(" homme");
		else
			System.out.print("e femme");
		System.out.print(" qui a " + this.age + " ans et qui étudie l'");
		if (this.langue == 1)
			System.out.print("anglais");
		else if (this.langue == 2)
			System.out.print("espagnol");
		else if (this.langue == 3)
			System.out.print("italien");
		else if (this.langue == 4)
			System.out.print("allemand");
		System.out.println("");
	}
	
	public void demandeIdentifiant() {
		boolean bonId = false;
		System.out.println("Veuillez indiquer votre identifiant :");
		while (!bonId)
		{
			String id = this.sc.nextLine();
			if (id.isEmpty())
			{
				System.out.println("Votre identifiant ne peut être vide.");
			}
			else if (checkUtilisateurExist(id) == true)
			{
				this.identifiant = id;
				bonId = true;
			}
			else
			{
				System.out.println("Cet identifiant existe déjà merci d'en choisir un autre.");		
			}
		}
	}
	
	public void demandeAge()
	{
		boolean bonAge = false;
		System.out.println("Veuillez indiquer votre age :");
		while (!bonAge)
		{
			String age = this.sc.nextLine();
			if (age.isEmpty())
			{
				System.out.println("Votre age ne peut être vide.");
			}
			else
			{
				try {
					this.age = Integer.parseInt(age);
					bonAge = true;
				}
				catch (Exception e)
				{
					System.out.println("Votre age doit etre un chiffre.");
				}
			}
		}
	}

	public void demandeGenre()
	{
		boolean bonGenre = false;
		System.out.println("Veuillez indiquer votre genre (1 pour homme, 2 pour femme) :");
		while (!bonGenre)
		{
			String genreStr = this.sc.nextLine();
			try {
				int genre = Integer.parseInt(genreStr);
				if (genre == 1 || genre == 2)
				{
					this.genre = genre;
					bonGenre = true;
				}
				else
				{
					System.out.println("Vous devez rentrez 1 pour homme ou 2 pour femme");
				}
			}
			catch (Exception e)
			{
				System.out.println("Vous devez rentrez 1 pour homme ou 2 pour femme");			
			}
		}
	}

	public void demandeLangue()
	{
		boolean bonLangue = false;
		System.out.println("Veuillez indiquer votre langue (1 pour anglais, 2 pour espagnol, 3 pour italien ou 4 pour allemand) :");
		while (!bonLangue)
		{
			String langueStr = this.sc.nextLine();
			try {
				int langue = Integer.parseInt(langueStr);
				if (langue == 1 || langue == 2 || langue == 3 || langue == 4)
				{
					this.langue = langue;
					bonLangue = true;
				}
				else
				{
					System.out.println("Vous devez rentrez 1 pour anglais, 2 pour espagnol, 3 pour italien ou 4 pour allemand");
				}
			}
			catch (Exception e)
			{
				System.out.println("Vous devez rentrez 1 pour anglais, 2 pour espagnol, 3 pour italien ou 4 pour allemand");
			}
		}
	}
	
	public boolean checkUtilisateurExist(String id) {
		String fileUserData = Utilisateur.dirUser + id + "/data.txt";
		File fileData = new File(fileUserData);
		if (fileData.exists())
		{
			return false;
		}
		return true;
	}
	public String getIdentifiant()
	{
		return this.identifiant;
	}
	public int getLangue()
	{
		return this.langue;
	}
}
