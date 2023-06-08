import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.*;
import java.io.*;


public class AllStructuredFile {
 
    static ArrayList <DapanTypes> dapanlist = new ArrayList <DapanTypes>();
	static ArrayList <Ergazomenos> erglist= new ArrayList <Ergazomenos>();
	static ArrayList <DapanErg> daperg = new ArrayList <DapanErg>();
	static ArrayList <Moves> moves = new ArrayList <Moves>();
    
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
	
	public static void parseErglistList(String aFileName) {
       try {
           FileReader fw = new FileReader(aFileName);           
           BufferedReader magnifient_glass=new BufferedReader(fw);
           StringTokenizer lineTokens;
           String token;
           String line;
           Ergazomenos r=null;
           boolean eof = false;
           boolean ok;
           while (!eof) {
               line = magnifient_glass.readLine();               
               if (line == null)
                   eof = true;
               else {
                  if (line.trim().equals("EMPLOYEE")) {
                     line = magnifient_glass.readLine();  
                     if (line.trim().equals("{")) {
                       magnifient_glass.mark(2048);
                       while ( !(line.trim().equals("}")) && (!eof) ) {
							r= new  Ergazomenos("","","",0);
                            ok=r.ReadFile(magnifient_glass);
                            if (ok) 
								erglist.add(r);                                  
                            break;                               
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
	
	
	public static void parseExpenseList(String aFileName) {
       try {
           FileReader fw = new FileReader(aFileName);           
           BufferedReader magnifient_glass=new BufferedReader(fw);
           StringTokenizer lineTokens;
           String token;
           String line;
           DapanErg r=null;
		   Ergazomenos employee = null;
		   DapanTypes typos_dapanh = null;
           boolean eof = false;
           boolean ok;
           while (!eof) {
            line = magnifient_glass.readLine();               
            if (line == null)
                eof = true;
            else {
                if (line.trim().equals("EXPENSE")) {
                     line = magnifient_glass.readLine();  
                    if (line.trim().equals("{")) {
                       magnifient_glass.mark(2048);
                       while ( !(line.trim().equals("}")) && (!eof) ) {
						    line = magnifient_glass.readLine();
							lineTokens = new StringTokenizer(line);
							token = lineTokens.nextToken();
						    if (token.toUpperCase().equals("EXPENSE_TYPE")) {
                                token = lineTokens.nextToken(); 
                                if (token.equals("1")){ 
									typos_dapanh = new AxiaxPososto("","",0,0);
									r= new  DapanErg(employee,typos_dapanh,0,"");
									ok=r.ReadFile(magnifient_glass,erglist,dapanlist);
									if (ok) 
									daperg.add(r);                                  
									break;    
                                }else if (token.equals("2")){ 
									typos_dapanh = new AmountXPrice("","",0,0,"");   
									r= new  DapanErg(employee,typos_dapanh,0,"");
									ok=r.ReadFile(magnifient_glass,erglist,dapanlist);
									if (ok) 
									daperg.add(r);                                  
									break;   
								}
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
	
	public static void parseTransactionList(String aFileName) {
        try {
           FileReader fw = new FileReader(aFileName);           
           BufferedReader magnifient_glass=new BufferedReader(fw);
           StringTokenizer lineTokens;
           String token;
           String line;
           Moves r=null;
		   Ergazomenos employee = null;
		   DapanTypes typos_dapanh = null;
           boolean eof = false;
           boolean ok;
           while (!eof) {
            line = magnifient_glass.readLine();  
            if (line == null)
                eof = true;
            else {
                if (line.trim().equals("TRN")) {
                    line = magnifient_glass.readLine(); 
                    if (line.trim().equals("{")) {
                       magnifient_glass.mark(2048);
                       while ( !(line.trim().equals("}")) && (!eof) ) {
						    line = magnifient_glass.readLine();
							lineTokens = new StringTokenizer(line);
							token = lineTokens.nextToken();
						    if (token.toUpperCase().equals("EXPENSE_TYPE")) {
                                token = lineTokens.nextToken(); 
                                if (token.equals("1")){ 
									typos_dapanh = new AxiaxPososto("","",0,0);
									r = new Compensation(employee, typos_dapanh, 0);
									ok=r.ReadFile(magnifient_glass,erglist,dapanlist);
									if (ok) 
										moves.add(r);                                  
										break;  
                                }else if (token.equals("2")){ 
									typos_dapanh = new AmountXPrice("","",0,0,"");
									r = new Compensation(employee, typos_dapanh, 0);
									ok=r.ReadFile(magnifient_glass,erglist,dapanlist);
									if (ok) 
										moves.add(r);                                  
										break;  
								}
							}else if(token.toUpperCase().equals("TYPE")) {
								typos_dapanh = new  AxiaxPososto("","",0,0);
								token = lineTokens.nextToken(); 
								if (token.equals("PROKATAVOLI")){ 
									r = new Advance(employee, typos_dapanh, 0);
									ok=r.ReadFile(magnifient_glass,erglist,dapanlist);
									if (ok) 
										moves.add(r);                                  
										break;  
								}else if (token.equals("DIAFORA")){ 
									r = new Difference(employee, typos_dapanh, 0);
									ok=r.ReadFile(magnifient_glass,erglist,dapanlist);
									if (ok) 
										moves.add(r);                                  
										break;  
								}else if (token.equals("FINAL_APOZIMIOSI")){ 
									r = new Clearence(employee, typos_dapanh, 0);
									ok=r.ReadFile(magnifient_glass,erglist,dapanlist);
									if (ok) 
										moves.add(r);                                  
										break;  
								} 
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
	
	public static void transferthedata(ListMaker1 Worker, ListMaker2 SpendingType, ListMaker3 Spending, ListMaker4 transactions){
		for (Ergazomenos i : erglist){
			Worker.addErgazomenos(i);
		}
		for (DapanTypes i : dapanlist){
			SpendingType.addDapanType(i);
		}
		for (DapanErg i : daperg){
			Spending.addDapanErg(i);
		}
		for (Moves i : moves){
			transactions.addMoves(i);
		}
		
	}
	
	public static void reversetransfer(ListMaker1 Worker, ListMaker2 SpendingType, ListMaker3 Spending, ListMaker4 transactions){
		erglist.clear();
		dapanlist.clear();
		daperg.clear();
		moves.clear();
		for (int i=0; i<Worker.getsize();i++){
			erglist.add(Worker.getErgazomeno(i));
		}
		for (int i=0; i<SpendingType.getsize();i++){
			dapanlist.add(SpendingType.getDapanType(i));
		}
		for (int i=0; i<Spending.getsize();i++){
			daperg.add(Spending.getDapanErg(i));
		}
		for (int i=0; i<transactions.getsize();i++){
			moves.add(transactions.getTransaction(i));
		}
	}
    

	public void CreateFileExpesnseType (String theFileName) {
		
		System.out.println(" >>>>>>> Write data from ARRAYLIST to FILE...");
		
		FileWriter writer = null;
		
		try	{
			writer = new FileWriter(new File(theFileName));
			
			writer.write ("EXPENSE_TYPE_LIST"+"\n"+"{"+"\n");
			
			for (DapanTypes i:dapanlist){
				
				writer.write ("\t"+"EXPENSE_TYPE"+"\n"+"\t"+"{"+"\n"+"\t"+"\t");
				
					if (i instanceof AxiaxPososto ){
						writer.write ("TYPE "+ "1"
								+"\n"+"\t"+"\t"+"CODE "+ ((AxiaxPososto)i).getCode()
								+ "\n"+"\t"+"\t"+"DESCR "	+ ((AxiaxPososto)i).getDescription()
								+ "\n"+"\t"+"\t"+"MAX-COMP "	+ ((AxiaxPososto)i).getMaxapoz()
								+ "\n"+"\t"+"\t"+"RATE-COMPENSATION "	+ ((AxiaxPososto)i).getHelper()
								+"\n"+"\t"+"}"+"\n");
					}
								
					else if (i instanceof AmountXPrice){
						writer.write ("TYPE "+ "2"
								+"\n"+"\t"+"\t"+"CODE "+ ((AmountXPrice)i).getCode()
								+ "\n"+"\t"+"\t"+"DESCR "	+ ((AmountXPrice)i).getDescription()
								+ "\n"+"\t"+"\t"+"MAX-COMP "	+ ((AmountXPrice)i).getMaxapoz()
								+ "\n"+"\t"+"\t"+"PRICE/UNIT "	+ ((AmountXPrice)i).getHelper()
								+ "\n"+"\t"+"\t"+"UNIT "	+ ((AmountXPrice)i).getMonmetr()
								+"\n"+"\t"+"}"+"\n");
	
					}
					
					}
					writer.write("}");
					writer.close();
				}//try
				
				catch (IOException e) {
				System.err.println("Error writing file.");
				}
	}//CreateFileExpenseType
	
	
	public void CreateFileEmployeeList (String theFileName) {
		
		System.out.println(" >>>>>>> Write data from ARRAYLIST to FILE...");
		
		FileWriter writer = null;
		
		try	{
			writer = new FileWriter(new File(theFileName));
			
			writer.write ("EMPLOYEE_LIST"+"\n"+"{"+"\n");
			
			for (Ergazomenos i:erglist){
				
				writer.write ("\t"+"EMPLOYEE"+"\n"+"\t"+"{"+"\n"+"\t"+"\t");
				
					
						writer.write ("CODE "+ i.getCode()
								+ "\n"+"\t"+"\t"+"SURNAME "	+ i.getSurname()
								+ "\n"+"\t"+"\t"+"FIRSTNAME  "	+ i.getFirstName()
								+ "\n"+"\t"+"\t"+"MAX_MONTHLY_VAL "	+ i.getMaxcomp()
								+"\n"+"\t"+"}"+"\n");
					
								
					
					
			}
			writer.write("}");
			writer.close();
				}//try
				
				catch (IOException e) {
				System.err.println("Error writing file.");
				}
	}//CreateFileEmployeeList
	
	public void CreateFileExpense (String theFileName) {
		
		System.out.println(" >>>>>>> Write data from ARRAYLIST to FILE...");
		
		FileWriter writer = null;
		
		try	{
			writer = new FileWriter(new File(theFileName));
			
			writer.write ("EXPENSE_LIST"+"\n"+"{"+"\n");
			
			for (DapanErg i:daperg){
				
				writer.write ("\t"+"EXPENSE"+"\n"+"\t"+"{"+"\n"+"\t"+"\t");
				
					
				if (i.getDapanType() instanceof AxiaxPososto ){
						writer.write ("EMPLOYEE_CODE "+ (i.getErgazomenos()).getCode()
						+"\n"+"\t"+"\t"+"EXPENSE_TYPE  "+ "1"
						+ "\n"+"\t"+"\t"+"EXPENSE_CODE "	+ (i.getDapanType()).getCode()
						+ "\n"+"\t"+"\t"+"VAL "	+ i.getValue());
						if(i.getReason().equals("")){
						
						}else{
						writer.write("\n"+"\t"+"\t"+"REASON "	+ i.getReason());}
						
						writer.write("\n"+"\t"+"}"+"\n");
					}
								
					else if (i.getDapanType() instanceof AmountXPrice){
						writer.write ("EMPLOYEE_CODE "+(i.getErgazomenos()).getCode()
						+"\n"+"\t"+"\t"+"EXPENSE_TYPE  "+ "2"
						+ "\n"+"\t"+"\t"+"EXPENSE_CODE "	+(i.getDapanType()).getCode()
						+ "\n"+"\t"+"\t"+"VAL "	+ i.getValue());
						if(i.getReason().equals("")){
						
						}else{
						writer.write("\n"+"\t"+"\t"+"REASON "	+ i.getReason());}
						
						writer.write("\n"+"\t"+"}"+"\n");
					}
					
					}
					writer.write("}");
					writer.close();
				}//try
				
				catch (IOException e) {
				System.err.println("Error writing file.");
				}
	}//CreateFileExpenses
	
	public void CreateFileTransactions (String theFileName) {
		
		System.out.println(" >>>>>>> Write data from ARRAYLIST to FILE...");
		
		FileWriter writer = null;
		
		try	{
			writer = new FileWriter(new File(theFileName));
			writer.write ("TRN_LIST"+"\n"+"{"+"\n");
			
			for (Moves i:moves){
				writer.write ("\t"+"TRN"+"\n"+"\t"+"{"+"\n"+"\t"+"\t");
				
				if (i instanceof Clearence){
						writer.write ("TYPE "+ "FINAL_APOZIMIOSI"
								+"\n"+"\t"+"\t"+"EMPLOYEE_CODE "+ (i.getErgazomenos()).getCode()
								+ "\n"+"\t"+"\t"+"VAL "	+ i.getAmountcomp()
								+"\n"+"\t"+"}"+"\n");
					}
				else if(i instanceof Difference){
						writer.write ("TYPE "+ "DIAFORA"
								+"\n"+"\t"+"\t"+"EMPLOYEE_CODE "+ (i.getErgazomenos()).getCode()
								+ "\n"+"\t"+"\t"+"VAL "	+ i.getAmountcomp()
								+"\n"+"\t"+"}"+"\n");
			
				}else if(i instanceof Advance){
						writer.write ("TYPE "+ "PROKATAVOLI"
								+"\n"+"\t"+"\t"+"EMPLOYEE_CODE "+ (i.getErgazomenos()).getCode()
								+ "\n"+"\t"+"\t"+"VAL "	+ i.getAmountcomp()
								+"\n"+"\t"+"}"+"\n");
				}else if(i instanceof Compensation){
					if (i.getDapanType() instanceof AxiaxPososto ){
						writer.write ("EMPLOYEE_CODE "+ (i.getErgazomenos()).getCode()
						+"\n"+"\t"+"\t"+"TYPE  "+ "APOZIMIOSI"
						+"\n"+"\t"+"\t"+"EXPENSE_TYPE  "+ "1"
						+ "\n"+"\t"+"\t"+"EXPENSE_CODE "	+ (i.getDapanType()).getCode()
						+ "\n"+"\t"+"\t"+"VAL "	+ i.getValue()
						+"\n"+"\t"+"}"+"\n");
					}else if (i.getDapanType() instanceof AmountXPrice){
						writer.write ("EMPLOYEE_CODE "+(i.getErgazomenos()).getCode()
						+"\n"+"\t"+"\t"+"TYPE  "+ "APOZIMIOSI"
						+"\n"+"\t"+"\t"+"EXPENSE_TYPE  "+ "2"
						+ "\n"+"\t"+"\t"+"EXPENSE_CODE "	+(i.getDapanType()).getCode()
						+ "\n"+"\t"+"\t"+"VAL "	+ i.getValue()
						+"\n"+"\t"+"}"+"\n");
					}
					
				}
				
			}
			writer.write("}");
			writer.close();
		}//try
				
				catch (IOException e) {
				System.err.println("Error writing file.");
				}
	}//CreateFileTransactions
    
}