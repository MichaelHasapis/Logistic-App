import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;


public class DapanErg{
	private Ergazomenos erg;
	private DapanTypes dp;
	private double value;
	private String reason;
	private double worthofcomp = 0;
	
	public DapanErg(Ergazomenos erg, DapanTypes dp,double value,String reason){
		this.erg= erg;
		this.dp=dp;
		this.value=value;
		this.reason=reason;
		this.worthofcomp = value*dp.getHelper();
	}
	
	public void setErgazomenos(Ergazomenos erg){
		this.erg=erg;
	}
	
	public void setDapanType(DapanTypes dp){
		this.dp=dp;
	}
	
	public void setReason(String reason){
		this.reason=reason;
	}
	
	public void setValue(double value){
		this.value=value;
    }
	
	public Ergazomenos getErgazomenos(){
		return erg;
	}
	
	public DapanTypes getDapanType(){
		return dp;
	}
	
	public String getReason(){
		return reason;
	}
	
	public double getValue(){
		return value;
    }
	
	
	public double getWorthofcomp(){
		return worthofcomp;
	}
	
	public String toString() {
		String l = Double.toString(value);
		String x = Double.toString(worthofcomp);
        return "Employee Code: "+erg.getCode() + "\t\tDapan Type: " + dp.getDescription() + "\t\tQuantity: "+l +"\t\tReason: "+ getReason() +"\t\tWorth: "+x;
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
                   value = Double.parseDouble(token);
                }
				else if (token.equals("REASON")) {
                   token = lineTokens.nextToken(); 
                   reason = token;
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
			worthofcomp= value*dp.getHelper();
        } catch (IOException ex) {
             System.err.println("Error reading file.");
        }
        
        return true;
    };
	
	
	
}