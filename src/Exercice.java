import java.io.PrintWriter;
import java.util.Scanner;

public class Exercice {
	Scanner sc;
	int langue;
	int typeLecon;
	int numeroExercice;
	public Exercice(Scanner sc, int langue, int typeLecon) {
		this.sc = sc;
		this.langue = langue;
		this.typeLecon = typeLecon;
	}
	
	public void print()
	{
		System.out.print("Vous avez fait l'exercice " + this.numeroExercice + " d'une leçon ");
		if (this.typeLecon == 1)
			System.out.print("de grammaire");
		else if (this.typeLecon == 2)
			System.out.print("de conjugaison");
		else if (this.typeLecon == 3)
			System.out.print("d'orthographe");
		else
			System.out.print("de vocabulaire");
		System.out.print(" dans la langue ");
		if (this.langue == 1)
			System.out.print("anglaise");
		else if (this.langue == 2)
			System.out.print("Espagnol");
		else if (this.langue == 3)
			System.out.print("Italienne");
		else
			System.out.print("Allemande");
		System.out.println(".");
	}
	
	public void setNumExo(int numeroExo)
	{
		this.numeroExercice = numeroExo;
	}
	
	public void writeExoInFile(PrintWriter out)
	{
		out.println(this.langue);
		out.println(this.typeLecon);
		out.println(this.numeroExercice);
	}
	
	public void demandeExercice()
	{
		System.out.print("Vous avez choisi de faire une leçon ");
		if (this.typeLecon == 1)
			System.out.print("de grammaire");
		else if (this.typeLecon == 2)
			System.out.print("de conjugaison");
		else if (this.typeLecon == 3)
			System.out.print("d'orthographe");
		else
			System.out.print("de vocabulaire");
		System.out.print(" dans la langue ");
		if (this.langue == 1)
			System.out.print("anglaise");
		else if (this.langue == 2)
			System.out.print("Espagnol");
		else if (this.langue == 3)
			System.out.print("Italienne");
		else
			System.out.print("Allemande");
		System.out.println(".");
		System.out.println("Choisissez un exercice en tapant un chiffre entre 1 et 10");
		
		boolean bonNum = false;
		while (!bonNum)
		{
			String age = this.sc.nextLine();
			if (age.isEmpty())
			{
				System.out.println("Merci de rentrer un numéro entre 1 et 10.");
			}
			else
			{
				try {
					int num = Integer.parseInt(age);
					if (num >= 1 && num <= 10)
					{
						this.numeroExercice = num;
						bonNum = true;
					}
					else
					{
						System.out.println("Merci de rentrer un numéro entre 1 et 10.");
					}
				}
				catch (Exception e)
				{
					System.out.println("Le numéro doit etre un chiffre.");
				}
			}
		}
		
	}

}
