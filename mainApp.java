import java.util.*;

/*************************************************************
 *	Student Name:		Dimitris Bouloutas
 *  Student ID Number:	3190136
 *
 *	Student Name:		Michalis Chasapis
 *  Student ID Number:	3200218
 *
 *
 * Team Number: Team 3
 *
 *
 * We have the app printing the default examples
 *
 *************************************************************/




public class mainApp{
	public static void main(String args[]) throws Exception{
		ListMaker1 Worker = new ListMaker1();
		ListMaker2 SpendingType = new ListMaker2();
		ListMaker3 Spending= new ListMaker3();
		ListMaker4 la = new ListMaker4();
		
		AllStructuredFile Creator = new AllStructuredFile();
		
		
		Creator.parseDapanTypesList("dapantypes.txt");
		Creator.parseErglistList("erg.txt");
		Creator.parseExpenseList("daperg.txt");
		Creator.parseTransactionList("moves.txt");
		
		Creator.transferthedata(Worker,SpendingType,Spending,la);
		
		Worker.showList();
		SpendingType.showList();
		Spending.showList();
		la.showList();
		
		Scanner in = new Scanner(System.in);
		Scanner pax = new Scanner(System.in);
		
		boolean done=false;
		String choice;
		while ( ! done)	{
			System.out.println("\n1. Insert new Expense Type");
   	 		System.out.println("2. Insert new Employee");
   	 		System.out.println("3. Insert new Expense of Employee");
   	 		System.out.println("4. Insert new Advance");
   	 		System.out.println("5. Show Expenses of Employees");
   	 		System.out.println("6. Clean up of Employee");
			System.out.println("7. Show Actions of Employees");
   	 		System.out.println("8. Clean up of all the Employees' Expenses");
   	 		System.out.println("9. Show final return value of all Employees ");
   	 		System.out.println("0. exit");
   	 		System.out.print("> ");
   	 		choice = in.nextLine();
			
			
			if (choice.equals ("1")){
				System.out.print("Are you sure you want to continue? (1->Yes OR 2->No) :");
				String second_choice = in.nextLine();
				if (second_choice.equals ("1")){
					System.out.print("\nChoose type of expense(1 for QuantityXPrice OR 2 for ValueXPercentage): ");
					String answer = in.nextLine();
					if (answer.equals ("1")){
						System.out.print("\nCode: ");
						String code = in.nextLine();
						System.out.print("\nDescription: ");
						String description = in.nextLine();
						System.out.print("\nMax month compenastion/employee: ");
						double maxcomp = pax.nextDouble();
						System.out.print("\nValue of Measuremet unit: ");
						double pricemonmetr = pax.nextDouble();
						System.out.print("\nMeasuremet unit: ");
						String mu = in.nextLine();
						AmountXPrice object1 = new AmountXPrice(code,description ,maxcomp ,pricemonmetr ,mu );
						SpendingType.addDapanType(object1);
				
					}else if (answer.equals ("2")){
						System.out.print("\nCode: ");
						String code = in.nextLine();
						System.out.print("\nDescription: ");
						String description = in.nextLine();
						System.out.print("\nMax month compenastion/employee: ");
						double maxcomp = pax.nextDouble();
						System.out.print("\nPercentage: ");
						double per = pax.nextDouble();
						AxiaxPososto object2 = new AxiaxPososto(code ,description ,maxcomp ,per );
						SpendingType.addDapanType(object2);
					}
				}
					
			}else if(choice.equals ("2")){
				System.out.print("Are you sure you want to continue? (1->Yes OR 2->No) :");
				String second_choice = in.nextLine();
				if (second_choice.equals ("1")){
					System.out.print("\nCode: ");
					String code = in.nextLine();
					System.out.print("\nFirstName: ");
					String firstname = in.nextLine();
					System.out.print("\nSurname: ");
					String surname = in.nextLine();
					System.out.print("\nMax Month Compensation: ");
					double  max = pax.nextDouble();
					Ergazomenos person1 = new Ergazomenos(code,firstname,surname,max);
					Worker.addErgazomenos(person1);
				}
				
				
				
			}else if(choice.equals ("3")){
				System.out.print("Are you sure you want to continue? (1->Yes OR 2->No) :");
				String second_choice = in.nextLine();
				if (second_choice.equals ("1")){
					Worker.listnameErgazomenos();
					System.out.print("\nGive index of employee from to list: ");
					int spot = pax.nextInt();
					SpendingType.listnamedapantype();
					System.out.print("\nGive index of expense type from to list: ");
					int point = pax.nextInt();
					System.out.print("\nGive value: ");
					double point2 = pax.nextDouble();
					System.out.print("\nGive reason: ");
					String point3 = in.nextLine();
					DapanErg moneygone = new DapanErg (Worker.getErgazomeno(spot-1),SpendingType.getDapanType(point-1),point2,point3);
					Spending.addDapanErg(moneygone);
				}
			
			}else if(choice.equals ("4")){
				System.out.print("Are you sure you want to continue? (1->Yes OR 2->No) :");
				String second_choice = in.nextLine();
				if (second_choice.equals ("1")){
					Worker.listnameErgazomenos();
					System.out.print("\nGive index of employee from to list: ");
					int spot = pax.nextInt();
					SpendingType.listnamedapantype();
					System.out.print("\nGive index of expense type from to list: ");
					int point = pax.nextInt();
					System.out.print("\nGive advance value: ");
					double point2 = pax.nextDouble();
					Advance zawarudo = new Advance(Worker.getErgazomeno(spot-1),SpendingType.getDapanType(point-1),point2);
					la.addMoves(zawarudo);
				
				}
			}else if(choice.equals ("5")){
				System.out.print("Are you sure you want to continue? (1->Yes OR 2->No) :");
				String second_choice = in.nextLine();
				if (second_choice.equals ("1")){
					Spending.showList();
				}
				
			}else if(choice.equals ("6")){
				System.out.print("Are you sure you want to continue? (1->Yes OR 2->No) :");
				String second_choice = in.nextLine();
				if (second_choice.equals ("1")){
				
					la.removeextra();
					
					Worker.listnameErgazomenos();
					System.out.print("\nGive index of employee from to list: ");
					
					int spot = pax.nextInt();
					
					Ergazomenos antexw = Worker.getErgazomeno(spot-1);
					
					double totalexpenses=0.0;
					
					for(int i=0; i<SpendingType.getsize();i++){
						
						DapanTypes dp = SpendingType.getDapanType(i);
						
						double typetotalcost = Spending.calculate_each_type(antexw,dp);
						
						
						if(typetotalcost!=0){
						
							if(typetotalcost>dp.getMaxapoz()){
								Compensation sorry = new Compensation(antexw,dp,dp.getMaxapoz());
								la.addMoves(sorry);
								System.out.println("\nNew transaction of compensation was created");
								totalexpenses+=sorry.getAmountcomp();
							}else{
								Compensation sorry = new Compensation(antexw,dp,typetotalcost);
								la.addMoves(sorry);
								System.out.println("\nNew transaction of compensation was created");
								totalexpenses+=sorry.getAmountcomp();
							}
						}
						
					}
					
					AxiaxPososto floater = new AxiaxPososto("0","0",0,0);
					
					if(totalexpenses>antexw.getMaxcomp()){
						Difference wealthy = new Difference(antexw,floater,totalexpenses-antexw.getMaxcomp());
						la.addMoves(wealthy);
						System.out.println("\nNew transaction of difference was created");
					}
					
					double alltheadvance= la.calculatealltheadvance(antexw);
					
					if(totalexpenses>antexw.getMaxcomp()){
						double cleaning_supply= totalexpenses-alltheadvance-(totalexpenses-antexw.getMaxcomp());
						Clearence shiny= new Clearence(antexw, floater,cleaning_supply);
						la.addMoves(shiny);
						System.out.println("\nNew transaction of clearance was created");
					}else{
						double cleaning_supply=totalexpenses-alltheadvance;
						Clearence shiny= new Clearence(antexw, floater,cleaning_supply);
						la.addMoves(shiny);
						System.out.println("\nNew transaction of clearance was created");
					}
				
				}
			
			}else if(choice.equals ("7")){
				System.out.print("Are you sure you want to continue? (1->Yes OR 2->No) :");
				String second_choice = in.nextLine();
				if (second_choice.equals ("1")){
					Worker.listnameErgazomenos();
					System.out.print("\nGive index of employee from to list: ");
					int spot = pax.nextInt();
					la.getthetransactions(Worker.getErgazomeno(spot-1));
				}
				
				
			}else if (choice.equals ("8")){
				System.out.print("Are you sure you want to continue? (1->Yes OR 2->No) :");
				String second_choice = in.nextLine();
				if (second_choice.equals ("1")){
					la.removeextra();
					
					for(int kwstas=0; kwstas<Worker.getsize();kwstas++){
						Ergazomenos antexw = Worker.getErgazomeno(kwstas);
						
						double totalexpenses=0.0;
					
						for(int i=0; i<SpendingType.getsize();i++){
						
							DapanTypes dp = SpendingType.getDapanType(i);
						
							double typetotalcost = Spending.calculate_each_type(antexw,dp);
						
							if(typetotalcost!=0){
						
								if(typetotalcost>dp.getMaxapoz()){
									Compensation sorry = new Compensation(antexw,dp,dp.getMaxapoz());
									la.addMoves(sorry);
									System.out.println("\nNew transaction of compensation was created");
									totalexpenses+=sorry.getAmountcomp();
								}else{
									Compensation sorry = new Compensation(antexw,dp,typetotalcost);
									la.addMoves(sorry);
									System.out.println("\nNew transaction of compensation was created");
									totalexpenses+=sorry.getAmountcomp();
								}
							}
						
						
						}
					
						AxiaxPososto floater = new AxiaxPososto("0","0",0,0);
					
						if(totalexpenses>antexw.getMaxcomp()){
							Difference wealthy = new Difference(antexw,floater,totalexpenses-antexw.getMaxcomp());
							la.addMoves(wealthy);
							System.out.println("\nNew transaction of difference was created");
						}
					
						double alltheadvance= la.calculatealltheadvance(antexw);
					
						if(totalexpenses>antexw.getMaxcomp()){
							double cleaning_supply= totalexpenses-alltheadvance-(totalexpenses-antexw.getMaxcomp());
							Clearence shiny= new Clearence(antexw, floater,cleaning_supply);
							la.addMoves(shiny);
							System.out.println("\nNew transaction of clearance was created");
						}else{
							double cleaning_supply=totalexpenses-alltheadvance;
							Clearence shiny= new Clearence(antexw, floater,cleaning_supply);
							la.addMoves(shiny);
							System.out.println("\nNew transaction of clearance was created");
						}
					}
				}
			}else if(choice.equals ("9")){
				System.out.print("Are you sure you want to continue? (1->Yes OR 2->No) :");
				String second_choice = in.nextLine();
				if (second_choice.equals ("1")){
					for(int umbrella=0; umbrella<Worker.getsize(); umbrella++){
						la.grandfinale(Worker.getErgazomeno(umbrella));
					}
				}
			
			
			}else{
				done = true;
				Creator.reversetransfer(Worker,SpendingType,Spending,la);
				Creator.CreateFileExpesnseType("dapantypes.txt");
				Creator.CreateFileEmployeeList("erg.txt");
				Creator.CreateFileExpense ("daperg.txt");
				Creator.CreateFileTransactions ("moves.txt");
				System.out.println("Thanks for using the app.");
			}
			
		}
	}
}