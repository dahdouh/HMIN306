package analyse;

import java.util.HashSet;
import java.util.Set;

import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.Assignment;
import org.eclipse.jdt.core.dom.Block;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.FieldAccess;
import org.eclipse.jdt.core.dom.FieldDeclaration;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.PrimitiveType;
import org.eclipse.jdt.core.dom.ReturnStatement;
import org.eclipse.jdt.core.dom.SimpleName;
import org.eclipse.jdt.core.dom.SingleVariableDeclaration;
import org.eclipse.jdt.core.dom.Type;
import org.eclipse.jdt.core.dom.TypeParameter;
import org.eclipse.jdt.core.dom.VariableDeclaration;
import org.eclipse.jdt.core.dom.VariableDeclarationFragment;

public class JDTMain {

public static void main(String[] args) {
        
        ASTParser parser = ASTParser.newParser(AST.JLS3); 
		parser.setKind(ASTParser.K_COMPILATION_UNIT);
		parser.setSource(ClassAsString.classAsString.toCharArray()); // set source
		parser.setResolveBindings(true); // we need bindings later on
		CompilationUnit cu = (CompilationUnit) parser.createAST(null); // parse // decommenter et commenter la dependance dans pom.xml pour les exos 1 , 2 , 3
		
		cu.accept(new ASTVisitor() {
			 
			Set names = new HashSet();
 
			public boolean visit(VariableDeclarationFragment node) {
				SimpleName name = node.getName(); 
				this.names.add(name.getIdentifier());
				System.out.println("\n==================================================================\n");
				System.out.println("Declaration of '"+name+"' at line"+cu.getLineNumber(name.getStartPosition())+"\n");
				
				// Ici on a declaré les Setter
				
				MethodDeclaration setMethods = cu.getAST().newMethodDeclaration();
				setMethods.setName(cu.getAST().newSimpleName("set"+name.getIdentifier().toUpperCase().charAt(0)+name.getIdentifier().substring(1, name.getIdentifier().length())));
				setMethods.setReturnType2(cu.getAST().newTypeDeclaration().getSuperclassType());
				
			    Type type = null;
				
				VariableDeclaration variableDeclaration = cu.getAST().newSingleVariableDeclaration();

			    if(node.getParent() instanceof FieldDeclaration){
	                FieldDeclaration declaration = ((FieldDeclaration) node.getParent());
                    
	                if(declaration.getType().isSimpleType()){
	                    String typeSimpleName = declaration.getType().toString();
	                    type = declaration.getType();   
	                    variableDeclaration.setStructuralProperty(SingleVariableDeclaration.TYPE_PROPERTY, cu.getAST().newSimpleType(cu.getAST().newName(typeSimpleName)));
	                } else {
	                    variableDeclaration.setStructuralProperty(SingleVariableDeclaration.TYPE_PROPERTY, cu.getAST().newPrimitiveType(PrimitiveType.INT));
	                } 
	            }
				
			    variableDeclaration.setName(cu.getAST().newSimpleName(name.getIdentifier()));
			    setMethods.parameters().add(variableDeclaration);
				
				Block bodySet = cu.getAST().newBlock();
				Assignment newAssignment = cu.getAST().newAssignment();

			    FieldAccess fieldAccessSet = cu.getAST().newFieldAccess();
			    fieldAccessSet.setExpression(cu.getAST().newThisExpression());
			    fieldAccessSet.setName(cu.getAST().newSimpleName(name.getIdentifier()));
			    newAssignment.setLeftHandSide(fieldAccessSet);
			    newAssignment.setRightHandSide(cu.getAST().newSimpleName(name.getIdentifier()));
			    
			    bodySet.statements().add(cu.getAST().newExpressionStatement(newAssignment));
			    setMethods.setBody(bodySet);

			    System.out.println(setMethods);
				
			    // Ici on a declaré les getter
			    
			    MethodDeclaration getMethods = cu.getAST().newMethodDeclaration();
				getMethods.setName(cu.getAST().newSimpleName("get"+name.getIdentifier().toUpperCase().charAt(0)+name.getIdentifier().substring(1, name.getIdentifier().length())));
				Block bodyGet = cu.getAST().newBlock();

			    ReturnStatement builderReturnStatement = cu.getAST().newReturnStatement();
			    FieldAccess fieldAccessGet = cu.getAST().newFieldAccess();
			    fieldAccessGet.setExpression(cu.getAST().newThisExpression());
			    fieldAccessGet.setName(cu.getAST().newSimpleName(name.getIdentifier()));
			    builderReturnStatement.setExpression(fieldAccessGet);
			    
			    if(node.getParent() instanceof FieldDeclaration){
	                FieldDeclaration declaration = ((FieldDeclaration) node.getParent());
	                if(declaration.getType().isSimpleType()){
	                    String typeSimpleName = declaration.getType().toString();
	                    type = declaration.getType();
	                    
	                	getMethods.setReturnType2(cu.getAST().newSimpleType(cu.getAST().newName(typeSimpleName)));
	                } else {
	                	getMethods.setReturnType2(cu.getAST().newPrimitiveType(PrimitiveType.INT));
	                }
	            }
			    
			    bodyGet.statements().add(builderReturnStatement);
			    getMethods.setBody(bodyGet);

			    System.out.println(getMethods);
			    
				return false;
			}
 
			public boolean visit(SimpleName node) {
				if (this.names.contains(node.getIdentifier())) {
				System.out.println("Usage of '" + node + "' at line " +	cu.getLineNumber(node.getStartPosition()));
				}
				return true;
			}
		});
	}
	
}