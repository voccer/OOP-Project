package com.virtuoso.ConnectDB;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.openrdf.model.Value;
import org.openrdf.query.BindingSet;
import org.openrdf.query.QueryLanguage;
import org.openrdf.query.TupleQuery;
import org.openrdf.query.TupleQueryResult;
import org.openrdf.repository.Repository;
import org.openrdf.repository.RepositoryConnection;
import org.openrdf.repository.RepositoryException;
import org.openrdf.rio.RDFFormat;
import org.openrdf.rio.RDFParseException;

import virtuoso.jdbc4.VirtuosoConnection;
import virtuoso.rdf4j.driver.*;
import virtuoso.sesame2.driver.VirtuosoRepository;

import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.rdf.model.Statement;

public class Test {
	public static void main(String[] args) throws SQLException {
		Repository myRepository = new VirtuosoRepository("jdbc:virtuoso://localhost:1111/", "dba", "dba");
		

//		String sql = "SELECT ?x ?y WHERE { ?x ?p ?y }";
//		TupleQuery tupleQuery = conn.prepareTupleQuery(QueryLanguage.SPARQL, sql);
//		TupleQueryResult result = tupleQuery.evaluate();
//		List bindingNames = result.getBindingNames();
		File file =new File("/home/vudat1710/Downloads/RDF-Model-Syntax_1.0/myfoaf.rdf");
		String baseURI = "http://www.example.com/jose/foaf.rdf#";
		try {
			RepositoryConnection conn = myRepository.getConnection();
			try {
				conn.add(file, baseURI, RDFFormat.RDFXML);
			} catch (RDFParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (RepositoryException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				conn.close();
			}
//			while (result.hasNext()) {
//				try {
//				BindingSet bindingSet = result.next();
//				Value valueX = bindingSet.getValue((String) bindingNames.get(0));
//				Value valueY = bindingSet.getValue((String) bindingNames.get(1));
//				System.out.println(valueX + "\t" + valueY);
//				} finally {
//				result.close();
//				}
//			}
		
		
	} finally {
		
	}
	}
}
