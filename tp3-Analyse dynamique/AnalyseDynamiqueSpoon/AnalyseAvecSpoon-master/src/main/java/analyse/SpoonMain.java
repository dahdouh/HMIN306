package analyse;
 
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
 
import processor.LogProcessor;
import spoon.Launcher;
import spoon.MavenLauncher;
import spoon.compiler.Environment;
import spoon.reflect.CtModel;
import spoon.reflect.code.CtBlock;
import spoon.reflect.code.CtCodeSnippetExpression;
import spoon.reflect.code.CtCodeSnippetStatement;
import spoon.reflect.code.CtInvocation;
import spoon.reflect.code.CtStatement;
import spoon.reflect.code.CtStatementList;
import spoon.reflect.declaration.CtClass;
import spoon.reflect.declaration.CtMethod;
import spoon.reflect.declaration.CtPackage;
import spoon.reflect.declaration.CtType;
import spoon.reflect.reference.CtTypeReference;
import spoon.reflect.visitor.filter.TypeFilter;
import spoon.support.reflect.code.CtStatementImpl;
import spoon.support.reflect.code.CtStatementListImpl;
 
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
        Launcher launcher = null;
        if (arguments.isMavenProject()) {
            launcher = new MavenLauncher(experiment_source_code, MavenLauncher.SOURCE_TYPE.APP_SOURCE);
        }
 
        launcher = new Launcher();
        launcher.addInputResource(experiment_source_code + "/src");
        
        // Setting the environment for Spoon
        Environment environment = launcher.getEnvironment();
        environment.setCommentEnabled(true);
        environment.setAutoImports(true);
 
        System.out.println("Run Launcher and fetch model.");
        launcher.run(); // creates model of project
        CtModel model = launcher.getModel(); // returns the model of the project
 
        List<CtMethod> methodList = model.getElements(new TypeFilter<CtMethod>(CtMethod.class));
 
        for (CtMethod ctMethod : methodList) {
        	List<CtStatement> listrefs  = ctMethod.getElements(new TypeFilter<CtStatement>(CtStatement.class));
            List<CtInvocation> refs = ctMethod.getElements(new TypeFilter<CtInvocation>(CtInvocation.class));
            for (CtInvocation ctInvocation : refs) {
                String pattern = "[a-z|A-Z]+\\(";
                Pattern regex = Pattern.compile(pattern);
                Matcher matcher = regex.matcher(ctInvocation.toString());
 
                String ctInvocationName = "";
 
                if (matcher.find()) {
                    ctInvocationName = removeLastChar(matcher.group(0));
                }      
                CtBlock block = ctInvocation.getParent(CtBlock.class);
                CtCodeSnippetStatement stm = launcher.getFactory().createCodeSnippetStatement(
                        "com.supanadit.restsuite.logger.LogWriter.out(\"" + ctMethod.getSimpleName() + "\", \"" + ctInvocationName + "\")");
               block.addStatement(0,stm);
               
            }
        }
 
        launcher.setSourceOutputDirectory("/home/karim/Desktop/RestsuitInstrumentes");
        launcher.prettyprint();
    }
 
    public static String removeLastChar(String s) {
        if (s == null || s.length() == 0) {
            return s;
        }
        return s.substring(0, s.length() - 1);
    }
}