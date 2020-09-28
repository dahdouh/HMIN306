package analyse;

import static guru.nidi.graphviz.model.Factory.mutGraph;
import static guru.nidi.graphviz.model.Factory.node;
import static guru.nidi.graphviz.model.Factory.to;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import com.kitfox.svg.Path;

import guru.nidi.graphviz.engine.Format;
import guru.nidi.graphviz.engine.Graphviz;
import guru.nidi.graphviz.engine.GraphvizJdkEngine;
import guru.nidi.graphviz.model.LinkSource;
import guru.nidi.graphviz.model.MutableGraph;

public class GraphVizMain {

	public static void main(String[] args) {
		generateGraph("graph.dot", "graph", "graph.png");
	}

	public static void generateGraph(String path, String nameGraphe, String output) {
		try {
			
			BufferedReader br = Files.newBufferedReader(Paths.get(path));
			String line = "";
			ArrayList<String> arrayRelations = new ArrayList<String>();
			while ((line = br.readLine()) != null) {
				if (!(line.contains("}") || line.contains("{"))) {
					arrayRelations.add(line.replace("; ", ""));
				}
			}
			
			List<LinkSource> linkSource = new ArrayList<LinkSource>();
			for (int i = 0; i < arrayRelations.size(); i++) {
				String classes[] = arrayRelations.get(i).split("->");
				linkSource.add(node(classes[0]).link(to(node(classes[1]))));
			}
			
			String OS = System.getProperty("os.name").toLowerCase();


			if( OS.indexOf("mac") >= 0) 
				{
				Graphviz.useEngine(new GraphvizJdkEngine());
			    }
			
			MutableGraph graph = mutGraph(nameGraphe).setDirected(true).add(linkSource);
			Graphviz.fromGraph(graph).width(8000).height(4000).render(Format.PNG).toFile(new File(output));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
}