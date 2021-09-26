
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.ReifiedStatement;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.Statement;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.ReifiedStatement;
import com.hp.hpl.jena.rdf.model.Resource;
import virtuoso.jena.driver.*;
import com.hp.hpl.jena.query.*;
import com.hp.hpl.jena.rdf.model.RDFNode;
import java.io.*; 
import java.io.FileWriter;  
//import static org.apache.jena.rdf.model.ModelFactory.createDefaultModel;

import com.opencsv.*;

import virtuoso.jena.driver.VirtModel; 


		 
public class Onto

{
	 
	public static void main(String args[]) throws IOException
	{ 
		
		 
		 CSVReader dataprop = new CSVReader(new FileReader("C:\\Users\\HP\\Downloads\\Dataproperty.csv"));
		 CSVReader objectprop = new CSVReader(new FileReader("C:\\Users\\HP\\Downloads\\Objectproperty.csv"));
		  final String NS = "http://www.iiitd.ac.in/winter2019/swebproject/terrosristnetwork";
         Model model = ModelFactory.createDefaultModel();
      // Creating a File object that represents the disk file. 
         PrintStream o = new PrintStream(new File("C:\\Users\\HP\\eclipse-workspace\\Ontology\\turtle5.ttl")); 
   
		 String [] nextLine;
		 int count=0;
		 
		 
	      int lineNumber = 0;
	      while((nextLine=dataprop.readNext())!=null) //reading subject of data propert
	      {
	    	 // System.out.println(nextLine[1]);
	    	  
	    	  String dp=nextLine[0]; //propert value
	    	  String classdomain=nextLine[1];  //datapropert subject
	    	  String dprange=nextLine[2];
	    	  
	    	  CSVReader classes = new CSVReader(new FileReader("C:\\Users\\HP\\Downloads\\MappingFile - Sheet1 (1).csv"));
	    	  String [] nextLine2;
	    	  int flag=0;
	    	  
	    	  while((nextLine2=classes.readNext())!=null)
	    	  {
	    		  
	    		  if(classdomain.equals(nextLine2[0]))
	    		  {
	    			  
	    			  String column=nextLine2[1];
	    			  int colno=Integer.valueOf(nextLine2[2]);
	    			  
	    			  CSVReader dataset = new CSVReader(new FileReader("C:\\Users\\HP\\Downloads\\FINALSWEBDATASET.csv"));
	    			  String [] nextLinedata;
	    			  
	    			  
	    			  
	    			  
    			  while((nextLinedata = dataset.readNext()) != null)
	    			  {
	    				
	   			   Resource subject = model.createResource( NS+"#"+classdomain+"/"+nextLinedata[colno]);
	    			   Resource object = model.createResource( NS+"/#/"+dprange);
	    	           Property property  = model.createProperty( NS+"/"+dp);
	    	        
	    	          Statement stmt = model.createStatement( subject, property, object );
	            
	    	           model.add(stmt);
	    	         
	    	           System.setOut(o); 
	    	           model.write( System.out, "TTL", null ); // or "RDF/XML", etc.
	    	             count++;
	    	                
	    	             //fw.write("Welcome to javaTpoint.");    
	    	      	
	    			  }
		    		  
	    		  }
	    	  }
	    	  
	      }
	  }

}




 

