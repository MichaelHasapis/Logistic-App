import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Ergazomenos{
	
	private String code;
	private String firstname;
	private String surname;
	private double maxmonthapoz;
	
	public Ergazomenos(String c, String f ,String s, double ma){
		this.code=c;
		this.firstname=f;
		this.surname=s;
		this.maxmonthapoz=ma;
	}
	
	public void setCode(String c)  {
        this.code=c;
    }
	
	public void setFirstName(String f)  {
        this.firstname=f;
    }
	
	public void setSurname(String s)  {
        this.surname=s;
    }
	
	public void setMaxmonthlyval(double ma) {
        this.maxmonthapoz=ma;
    }
	
	public String getCode()  {
        return code;
    }
	
	public String getFirstName()  {
        return firstname;
    }
	
	public String getSurname()  {
        return surname;
    }
	
	public double getMaxcomp() {
        return maxmonthapoz;
    }
	
	
	public String toString() {
		String x = Double.toString(maxmonthapoz);
        return "First Name: "+getFirstName() +"\t\t Surname: "+getSurname() +"\t\tMax Compensation/month " + x;
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
                else if (token.equals("FIRSTNAME")) {
                   token = lineTokens.nextToken(); 
                   firstname = token;
                }
                else if (token.equals("SURNAME")) {
                   token = lineTokens.nextToken(); 
                   surname = token;
                }
				else if (token.equals("MAX_MONTHLY_VAL")) {
                   token = lineTokens.nextToken(); 
                   maxmonthapoz = Double.parseDouble(token);
                }
                
                line = magnifient_glass.readLine();
                //System.out.println(line);                                
            }   
        } catch (IOException ex) {
             System.err.println("Error reading file.");
        }
        
        return true;
    };
	
	
}