package analyse;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import spoon.Launcher;
import spoon.MavenLauncher;
import spoon.compiler.Environment;
import spoon.reflect.CtModel;
import spoon.reflect.code.CtStatement;
import spoon.reflect.declaration.CtClass;
import spoon.reflect.declaration.CtMethod;
import spoon.reflect.declaration.CtPackage;
import spoon.reflect.declaration.CtType;
import spoon.reflect.reference.CtTypeReference;
import spoon.reflect.visitor.filter.TypeFilter;

public class SpoonMain {

	public static void main(String[] args) {

		System.out.println("Begin Analysis");

		// Parsing arguments using JCommander
		Arguments arguments = new Arguments();
		boolean isParsed = arguments.parseArguments(args);

		// if there was a problem parsing the arguments then the program is terminated.
		if (!isParsed)
			return;

		// Parsed Arguments
		String experiment_source_code = arguments.getSource();
		String experiment_output_filepath = arguments.getTarget();

		// Load project (APP_SOURCE only, no TEST_SOURCE for now)
		Launcher launcher = null; // commenter la dependance dans pom.xml si une erreur est lancer
		if (arguments.isMavenProject()) {
			launcher = new MavenLauncher(experiment_source_code, MavenLauncher.SOURCE_TYPE.APP_SOURCE); // requires
																										// M2_HOME
																										// environment
																										// variable
		} else {
			launcher = new Launcher();
			launcher.addInputResource(experiment_source_code + "/src");
		}

		// Setting the environment for Spoon
		Environment environment = launcher.getEnvironment();
		environment.setCommentEnabled(true); // represent the comments from the source code in the AST
		environment.setAutoImports(true); // add the imports dynamically based on the typeReferences inside the AST
											// nodes.
//		environment.setComplianceLevel(0); // sets the java compliance level.

		System.out.println("Run Launcher and fetch model.");
		launcher.run(); // creates model of project
		CtModel model = launcher.getModel(); // returns the model of the project

		// basic type filter to retrive all methods in your model

		// Nombre de methode
		int sommeLigneCode = 0;

		List<CtMethod> methodList = model.getElements(new TypeFilter<CtMethod>(CtMethod.class));
		ArrayList<String> myListClassName = new ArrayList<String>();
		ArrayList<Integer> myListMethodNumber = new ArrayList<Integer>();
		ArrayList<Integer> myListAttribue = new ArrayList<Integer>();
		ArrayList<String> myListClassAttribue = new ArrayList<String>();
		ArrayList<String> twoCategorieClass = new ArrayList<String>();
		ArrayList<String> twoCategorie = new ArrayList<String>();
		ArrayList<String> Xmethodeplus = new ArrayList<String>();
		ArrayList<String> methodCounterList = new ArrayList<String>();
		ArrayList<Integer> methodeCounterNumber = new ArrayList<Integer>();
		int X = 8; // Classe avec plus de X methode ; a modifier ici

		int max = 0;
		int maxAttribute = 0;
		int maxLigne = 0;
		int nbParametermax = 0;

		int compteMethod = 0;
		for (CtMethod method : methodList) {
			compteMethod++;
			nbParametermax = nbParametermax + method.getParameters().size();
			sommeLigneCode = sommeLigneCode + method.toString().split("\n").length;
			methodCounterList.add(method.getSimpleName());
			methodeCounterNumber.add(method.toString().split("\n").length);

			if (maxLigne < method.toString().split("\n").length) {
				maxLigne = method.toString().split("\n").length;
			}
		}
		System.out.println("Le nombre de methode est : " + compteMethod);

		/********************************************************/
		// Nombre de classe
		List<CtClass> classList = model.getElements(new TypeFilter<CtClass>(CtClass.class));
		int compteurClass = 0;
		int sommeAttribue = 0;
		int sommeMethod = 0;
		for (CtClass myclass : classList) {
			sommeMethod = sommeMethod + myclass.getMethods().size();
			compteurClass++;
			sommeAttribue = sommeAttribue + myclass.getFields().size();

			myListClassName.add(myclass.getSimpleName());
			myListMethodNumber.add(myclass.getMethods().size());
			myListAttribue.add(myclass.getFields().size());
			if (max < myclass.getMethods().size()) {
				max = myclass.getMethods().size();
			}
			if (maxAttribute < myclass.getFields().size()) {
				maxAttribute = myclass.getAllFields().size();
			}
			if (myclass.getMethods().size() > X) {
				Xmethodeplus.add(myclass.getSimpleName());
			}

		}
		System.out.println("Le nombre de classe est : " + compteurClass);

		// Nombre de Ligne

		List<CtStatement> statementList = model.getElements(new TypeFilter<CtStatement>(CtStatement.class));
		int compteur = 0;

		for (CtStatement statement : statementList) {
			compteur++;
		}
		System.out.println("Le nombre de Ligne est : " + compteur);

		// Nombre de Package

		List<CtPackage> MyPackageList = model.getElements(new TypeFilter<CtPackage>(CtPackage.class));
		int compteurPakckage = 0;

		for (CtPackage pack : MyPackageList) {

			compteurPakckage++;
		}
		System.out.println("Le nombre de Package est : " + compteurPakckage);

		// NOmbre moyen de methode par classe


		double moyen = (double) sommeMethod / compteurClass;

		System.out.println("le nombre moyen de methode par classe :" + moyen);

		// NOmbre moyen de ligne de code par methode

		double moyenneligne = (double) sommeLigneCode / sommeMethod;

		System.out.println("le nombre moyen de Ligne par par methode :" + moyenneligne);

		// Nombre moyen d'attribue par classe

		double moyenneAttribue = (double) sommeAttribue / compteurClass;

		System.out.println("le nombre moyen d'attribue par classe :" + moyenneAttribue);

		/*******************************************/

		// Les 10% des classes qui possedent le plus grand nombre de m ́ethodes.
		System.out.println("\n Les 10% des classes qui possedent le plus grand nombre de methodes : ");

		int tenpercent = (int) (myListClassName.size() * 0.10);
		int tenpercentAt = tenpercent;
		int tenpercentLigne = (int) (methodCounterList.size() * 0.10);

		for (int i = max; i > 0; i--) {
			if (tenpercent > 0) {
				for (int j = 0; j < myListClassName.size(); j++) {

					if (myListMethodNumber.get(j) == i) {
						twoCategorieClass.add(myListClassName.get(j));

						System.out.println("Classe : " + myListClassName.get(j) + " et Nombre de methode :"
								+ myListMethodNumber.get(j));
						tenpercent--;
					}
				}
			} else {
				break;
			}

		}

		System.out.println("\n Les 10% des classes qui possedent le plus grand nombre d’attributs sont : \n");

		for (int i = maxAttribute; i > 0; i--) {
			if (tenpercentAt > 0) {
				for (int j = 0; j < myListClassName.size(); j++) {

					if (myListAttribue.get(j) == i) {
						System.out.println("Classe : " + myListClassName.get(j) + " et Nombre d'attribue  :"
								+ myListAttribue.get(j));
						tenpercentAt--;
						for (int k = 0; k < twoCategorieClass.size(); k++) {
							if (twoCategorieClass.get(k).equals(myListClassName.get(j))) {
								twoCategorie.add(myListClassName.get(j));
							}
						}

					}
				}
			} else {
				break;
			}

		}

		System.out.println("\n Les classes qui font partie en meme temps des deux categories precedentes :\n");
		for (int i = 0; i < twoCategorie.size(); i++) {
			System.out.println(twoCategorie.get(i));

		}

		System.out.println("\nLes classes qui ont plus que X (modifiable) = " + X + " methodes sont : \n ");
		for (int i = 0; i < Xmethodeplus.size(); i++) {
			System.out.println(Xmethodeplus.get(i));
		}

		System.out.println("\n Les 10% des méthodes qui possedent le plus grand nombre de lignes de code :  \n");
		for (int i = maxLigne; i > 0; i--) {
			if (tenpercentLigne > 0) {
				for (int j = 0; j < methodCounterList.size(); j++) {

					if (methodeCounterNumber.get(j) == i) {

						System.out.println("Methode : " + methodCounterList.get(j) + " et Nombre de Ligne de code :"
								+ methodeCounterNumber.get(j));
						tenpercentLigne--;
					}
				}
			} else {
				break;
			}

		}

		System.out.println("\n Le nombre maximal de parametre par rapport a toutes les methodes de l'applis est : "
				+ nbParametermax);
		
		List<String> allRefs = new ArrayList<String>();
        for(CtType<?> s : model.getAllTypes()) {
        	allRefs.add(s.getSimpleName());
        }
		
		try (Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("graph.dot"), "utf-8"))) {

			writer.write(" graph mon_graphe {\n");
			for (CtType<?> s : model.getAllTypes()) {
				List<CtTypeReference> refs = s.getElements(new TypeFilter<CtTypeReference>(CtTypeReference.class));
				List<String> foreignRefs = new ArrayList<String>();

				for (CtTypeReference ref : refs) {
					if (!foreignRefs.contains(ref.getSimpleName()) && allRefs.contains(ref.getSimpleName())) {
						foreignRefs.add(ref.getSimpleName());
					}
				}
				for (String classname : foreignRefs) {
					writer.write(s.getSimpleName() + "--" + classname + "; \n");
				}
			}
			writer.write(" }\n");

		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		

	}

}