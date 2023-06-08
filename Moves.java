import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;


public abstract class Moves{
	protected Ergazomenos erg;
	protected DapanTypes dp;
	protected double amountcomp=0;
	
	public Moves(Ergazomenos erg, DapanTypes dp){
		this.erg= erg;
		this.dp= dp;
		
	}
	
	public void setErgazomenos(Ergazomenos erg){
		this.erg=erg;
	}
	
	public void setDapanType(DapanTypes dp){
		this.dp=dp;
	}
	
	public void setValue(double value){
		this.amountcomp=value;
    }
	
	public double getAmountcomp(){
		return amountcomp;
	}
	
	public DapanTypes getDapanType(){
		return dp;
	}
	
	public Ergazomenos getErgazomenos(){
		return erg;
	}
	
	public double getValue(){
		return amountcomp;
    }
	
	
	public String toString(){
		String x=Double.toString(amountcomp);
		return "Name: "+ erg.getCode()+ "\t\tType: " + getClass() + "\t\t Amount comp: "+ x;
	}
	
	
	abstract public boolean ReadFile(BufferedReader magnifient_glass,ArrayList <Ergazomenos> erglist, ArrayList <DapanTypes> dapantype); 
}



class Difference extends Moves{
	
	public Difference(Ergazomenos erg, DapanTypes dp, double amountcomp){
		super(erg,dp);
		this.amountcomp = amountcomp;
	}
	
	public boolean ReadFile(BufferedReader magnifient_glass,ArrayList <Ergazomenos> erglist, ArrayList <DapanTypes> dapantype){
		try {
            magnifient_glass.reset();
            StringTokenizer lineTokens;
            String token;
            String line;
			String expensetype="";
			String expensecode="";
            line = magnifient_glass.readLine();
            while ( !(line.trim().equals("}")) ) {
                lineTokens = new StringTokenizer(line);
                token = lineTokens.nextToken();  
                if (token.equals("EMPLOYEE_CODE")) {
                   token = lineTokens.nextToken(); 
                   for(Ergazomenos i : erglist){
						if(i.getCode().equals(token))
							erg= i;
					}
                }
                else if (token.equals("EXPENSE_TYPE")) {
                   token = lineTokens.nextToken(); 
                   expensetype = token;
                }
                else if (token.equals("EXPENSE_CODE")) {
                   token = lineTokens.nextToken(); 
                   expensecode = token;
                }
				else if (token.equals("VAL")) {
                   token = lineTokens.nextToken(); 
                   amountcomp = Double.parseDouble(token);
                }
                
                line = magnifient_glass.readLine();
                //System.out.println(line);                                
            }   
			
			if(expensetype.equals("1")){
				for(DapanTypes o : dapantype){
					if(o.getCode().equals(expensecode) && o instanceof AxiaxPososto)
						dp=o;
				}
			}else if(expensetype.equals("2")){
				for(DapanTypes o : dapantype){
					if(o.getCode().equals(expensecode) && o instanceof AmountXPrice)
						dp=o;
				}
			}
			
        } catch (IOException ex) {
             System.err.println("Error reading file.");
        }
        
        return true;
	}
	
}



class Advance extends Moves{
	public Advance(Ergazomenos erg, DapanTypes dp, double amountcomp){
		super(erg,dp);
		this.amountcomp = amountcomp;
	}
	
	public boolean ReadFile(BufferedReader magnifient_glass,ArrayList <Ergazomenos> erglist, ArrayList <DapanTypes> dapantype){
		try {
            magnifient_glass.reset();
            StringTokenizer lineTokens;
            String token;
            String line;
            line = magnifient_glass.readLine();
            while ( !(line.trim().equals("}")) ) {
                lineTokens = new StringTokenizer(line);
                token = lineTokens.nextToken();  
                if (token.equals("EMPLOYEE_CODE")) {
                   token = lineTokens.nextToken(); 
                   for(Ergazomenos i : erglist){
						if(i.getCode().equals(token))
							erg= i;
					}
                }
				else if (token.equals("VAL")) {
                   token = lineTokens.nextToken(); 
                   amountcomp = Double.parseDouble(token);
                }
                
                line = magnifient_glass.readLine();
                //System.out.println(line);                                
            }
			
        } catch (IOException ex) {
             System.err.println("Error reading file.");
        }
        
        return true;
	}

}


class Compensation extends Moves{
	public Compensation(Ergazomenos erg, DapanTypes dp, double amountcomp){
		super(erg,dp);
		this.amountcomp = amountcomp;
	}	
	public String toString(){
		String x=Double.toString(amountcomp);
		return "Name: "+ erg.getCode()+ "\t\tType: " + getClass() +"\t\tExpense Type: "+ dp.getDescription()+ "\t\t Amount comp: "+ x;
	}
	
	public boolean ReadFile(BufferedReader magnifient_glass,ArrayList <Ergazomenos> erglist, ArrayList <DapanTypes> dapantype){
		try {
            magnifient_glass.reset();
            StringTokenizer lineTokens;
            String token;
            String line;
            line = magnifient_glass.readLine();
            while ( !(line.trim().equals("}")) ) {
                lineTokens = new StringTokenizer(line);
                token = lineTokens.nextToken();  
                if (token.equals("EMPLOYEE_CODE")) {
                   token = lineTokens.nextToken(); 
                   for(Ergazomenos i : erglist){
						if(i.getCode().equals(token))
							erg= i;
					}
                }
				else if (token.equals("VAL")) {
                   token = lineTokens.nextToken(); 
                   amountcomp = Double.parseDouble(token);
                }
                
                line = magnifient_glass.readLine();
                //System.out.println(line);                                
            }
			
        } catch (IOException ex) {
             System.err.println("Error reading file.");
        }
        
        return true;
	}
	
}


class Clearence extends Moves{
	public Clearence(Ergazomenos erg, DapanTypes dp, double amountcomp){
		super(erg,dp);
		this.amountcomp = amountcomp;
	}
	
	public boolean ReadFile(BufferedReader magnifient_glass,ArrayList <Ergazomenos> erglist, ArrayList <DapanTypes> dapantype){
		try {
            magnifient_glass.reset();
            StringTokenizer lineTokens;
            String token;
            String line;
            line = magnifient_glass.readLine();
            while ( !(line.trim().equals("}")) ) {
                lineTokens = new StringTokenizer(line);
                token = lineTokens.nextToken();  
                if (token.equals("EMPLOYEE_CODE")) {
                   token = lineTokens.nextToken(); 
                   for(Ergazomenos i : erglist){
						if(i.getCode().equals(token))
							erg= i;
					}
                }
				else if (token.equals("VAL")) {
                   token = lineTokens.nextToken(); 
                   amountcomp = Double.parseDouble(token);
                }
                
                line = magnifient_glass.readLine();
                //System.out.println(line);                                
            }
			
        } catch (IOException ex) {
             System.err.println("Error reading file.");
        }
        
        return true;
	}
	
}
