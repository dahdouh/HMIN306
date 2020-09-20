package analyse;

import java.io.File;
import java.io.IOException;
import java.util.List;

import guru.nidi.graphviz.attribute.Color;
import guru.nidi.graphviz.attribute.Font;
import guru.nidi.graphviz.attribute.Rank;
import guru.nidi.graphviz.attribute.Rank.RankDir;
import guru.nidi.graphviz.attribute.Style;
import guru.nidi.graphviz.engine.Format;
import guru.nidi.graphviz.engine.Graphviz;
import guru.nidi.graphviz.model.Graph;
import guru.nidi.graphviz.model.MutableGraph;
import spoon.Launcher;
import spoon.MavenLauncher;
import spoon.compiler.Environment;
import spoon.reflect.CtModel;
import spoon.reflect.declaration.CtMethod;
import spoon.reflect.visitor.filter.TypeFilter;
import static guru.nidi.graphviz.model.Factory.*;


public class GraphVizMain {


		public static void main(String[] args) {
			try {
				String batCode = System.getProperty("user.dir");
				String OS = System.getProperty("os.name").toLowerCase();
				System.out.println(batCode);
				 if(OS.indexOf("nix") >= 0 || OS.indexOf("nux") >= 0 || OS.indexOf("aix") > 0) {
					 Runtime.
					   getRuntime().
					   exec(batCode+"/exec.bat");
					 System.out.println("Le graph a été généré, rafraichissez votre projet.");
					
				 }else if (OS.indexOf("win") >= 0) {
					 Runtime.getRuntime().exec("cmd /c dot -Tpng graph.dot > graph.png");
					 System.out.println("Le graph a été généré, rafraichissez votre projet.");
				 }
				 
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
}