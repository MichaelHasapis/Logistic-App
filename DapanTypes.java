import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class DapanTypes{
	protected String code;
	protected String description;
	protected double maxapoz;
	
	
	public DapanTypes(String code, String description, double maxapoz){
		this.code= code;
		this.description=description;
		this.maxapoz=maxapoz;
	}
	
	public void setCode(String code){
		this.code=code;
	}
	
	public void setDescription(String descr){
		this.description=descr;
	}
	
	public void setMaxapoz(double maxapoz){
		this.maxapoz=maxapoz;
	}
	
	public String getCode()  {
        return code;
    }
	
	public String getDescription(){
		return description;
	}
	
	public double  getMaxapoz() {
        return maxapoz;
    }
		
	public abstract String toString();
	
	public abstract double getHelper();
	
	abstract public boolean ReadFile(BufferedReader br); 
}//DapanTypes//


class AxiaxPososto extends DapanTypes{
	
	
	private double pososto_apoz;
	
	public AxiaxPososto(String code , String description ,double maxapoz ,double pososto_apoz){
		super(code,description,maxapoz);
		this.pososto_apoz=pososto_apoz/100;
	}
	
	
	public void setPosostoapoz(double pososto_apoz){
		this.pososto_apoz=pososto_apoz;
	}
	
	public double getHelper() {
        return pososto_apoz;
    }
	
	public String toString(){
		String x = Double.toString(maxapoz);
		String y = Double.toString(pososto_apoz);
		return "Code: "+ getCode() + "\t\tType: " + getDescription() + "\t\tMax Compensation/worker: "+ x + "\t\tRate compensation: " +y; 
		
	}
	
	public boolean ReadFile(BufferedReader magnifient_glass){
        try {
            magnifient_glass.reset();
            StringTokenizer lineTokens;
            String token;
            String line;    
            line = magnifient_glass.readLine();             
            while ( !(line.trim().equals("}")) ) {
                lineTokens = new StringTokenizer(line);
                token = lineTokens.nextToken();  
                if (token.equals("CODE")) {
                   token = lineTokens.nextToken();
                   code = token;
				   
                }
                else if (token.equals("DESCR")) {
                   token = lineTokens.nextToken(); 
                   description= token;

                }
                else if (token.equals("MAX-COMP")) {
                   token = lineTokens.nextToken(); 
                   maxapoz= Double.parseDouble(token);
                }
				else if (token.equals("RATE-COMPENSATION")) {
                   token = lineTokens.nextToken(); 
                   pososto_apoz= (Double.parseDouble(token));
                }
                
                line = magnifient_glass.readLine();
                //System.out.println(line);                                
            }   
        } catch (IOException ex) {
             System.err.println("Error reading file.");
        }
        
        return true;
    };
	
}//AxiaxPososto//


class AmountXPrice extends DapanTypes{
	private double pricemonmetr;
	private String monmetr;
	
	
	public AmountXPrice(String code, String description, double maxapoz, double pricemonmetr,String monmetr){
		super(code,description,maxapoz);
		this.pricemonmetr=pricemonmetr;
		this.monmetr=monmetr;
	}
	
	public void setPricmonmetrhsh(double pricemonmetr){
		this.pricemonmetr=pricemonmetr;
	}
	
	public void setMonmetr(String monmetr){
		this.monmetr=monmetr;
	}
	
	public double getHelper() {
        return pricemonmetr;
    }
	
	public String getMonmetr(){
		return monmetr;
	}
	
	public String toString(){
		String l = Double.toString(maxapoz);
		String k = Double.toString(pricemonmetr);
		return "Code: "+ getCode() + "\t\tType: " + getDescription() + "\t\tMax Compensation/worker: "+ l + "\t\tPrice/unit: "+ k +"\t\tUnit: " + getMonmetr();
	}
	
	public boolean ReadFile(BufferedReader magnifient_glass){
        try {
            magnifient_glass.reset();
            StringTokenizer lineTokens;
            String token;
            String line;    
            line = magnifient_glass.readLine();             
            while ( !(line.trim().equals("}")) ) {
                lineTokens = new StringTokenizer(line);
                token = lineTokens.nextToken();  
                if (token.equals("CODE")) {
                   token = lineTokens.nextToken(); 
                   code = token;
                }
                else if (token.equals("DESCR")) {
                   token = lineTokens.nextToken(); 
                   description = token;
                }
                else if (token.equals("MAX-COMP")) {
                   token = lineTokens.nextToken(); 
                   maxapoz = Double.parseDouble(token);
                }
				else if (token.equals("PRICE/UNIT")) {
                   token = lineTokens.nextToken(); 
                   pricemonmetr = Double.parseDouble(token);
                }
				else if (token.equals("UNIT")) {
                   token = lineTokens.nextToken(); 
                   monmetr = token;
                }
                
                line = magnifient_glass.readLine();
                //System.out.println(line);                                
            }   
        } catch (IOException ex) {
             System.err.println("Error reading file.");
        }
        
        return true;
    };
	
	
	
	
}//AmountXPrice//

class StructuredFile {
 
    static ArrayList <DapanTypes> dapanlist = new ArrayList <DapanTypes>();
    
    public static void parseDapanTypesList(String aFileName) {
       try {
           FileReader fw = new FileReader(aFileName);           
           BufferedReader magnifient_glass=new BufferedReader(fw);
           StringTokenizer lineTokens;
           String token;
           String line;
           DapanTypes r=null;
           boolean eof = false;
           boolean ok;
           while (!eof) {
               line = magnifient_glass.readLine();               
               if (line == null)
                   eof = true;
               else {
                  if (line.trim().equals("EXPENSE_TYPE")) {
                     line = magnifient_glass.readLine();  
                     if (line.trim().equals("{")) {
                       magnifient_glass.mark(2048);
                       while ( !(line.trim().equals("}")) && (!eof) ) {
                          line = magnifient_glass.readLine();
                          lineTokens = new StringTokenizer(line);
                          token = lineTokens.nextToken();  
                          if (token.toUpperCase().equals("TYPE")) {
                              token = lineTokens.nextToken(); 
                              if (token.equals("1")) 
                                 r = new AxiaxPososto("","",0,0);
                              else if (token.equals("2")) 
                                 r = new AmountXPrice("","",0,0,"");                                     
                              ok=r.ReadFile(magnifient_glass);
                              if (ok) 
                                  dapanlist.add(r);                                  
                              break;                               
                          }
                       }    
                     }
                  }
               }    
           }           
           
           magnifient_glass.close();
       }
       catch (IOException ex) {
            System.err.println("Error reading file.");
       }       
    }
}








