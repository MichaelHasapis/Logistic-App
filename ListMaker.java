import java.util.*;

public abstract class ListMaker{
	public abstract void showList();
	public abstract int getsize();
}



class ListMaker1 extends ListMaker{
	
	private ArrayList <Ergazomenos> erg= new ArrayList <Ergazomenos>();
	
	public void addErgazomenos(Ergazomenos e){
		erg.add (e);
	}
	
	public void showList() {
		for (Ergazomenos e:erg)
			System.out.println (e+"\n");			
		
	}	
	
	public int getsize(){
		return erg.size();
	}
	
	public Ergazomenos getErgazomeno(int spot){
		return erg.get(spot);
	}
	

	
	public void listnameErgazomenos(){
		for(int i=0; i<erg.size(); i++){
					Ergazomenos ergatomana= erg.get(i);
					String number = String.valueOf(i+1);
					System.out.println(ergatomana.getCode()+" ("+number+")");
				}
	}
	
}


class ListMaker2 extends ListMaker{
	private ArrayList <DapanTypes> dp = new ArrayList <DapanTypes>();
	
	public void addDapanType(DapanTypes dapani){
		dp.add(dapani);
	}
	public void showList() {
		for (DapanTypes dapani:dp)
			System.out.println (dapani+"\n");			
		
	}
	
	public int getsize(){
		return dp.size();
	}
	
	public DapanTypes getDapanType(int point){
		return dp.get(point);
	}
	
	public void listnamedapantype(){
		for(int i=0; i<dp.size(); i++){
					DapanTypes dapanomana= dp.get(i);
					String number = String.valueOf(i+1);
					System.out.println(dapanomana.getCode()+" ("+number+")");
				}
	}
	
	
}

class ListMaker3 extends ListMaker{
	private ArrayList <DapanErg> de = new ArrayList <DapanErg>();
	
	public void addDapanErg(DapanErg dpe){
		de.add(dpe);
	}
	
	public void showList() {
		for (DapanErg dapanierg:de)
			System.out.println (dapanierg+"\n");			
		
	}
	
	public int getsize(){
		return de.size();
	}
	
	public DapanErg getDapanErg(int point){
		return de.get(point);
	}
	
	public double calculate_each_type(Ergazomenos erg, DapanTypes dp){
		double typetotalcost=0;
		for(int i=0; i<de.size(); i++){
			DapanErg dentro = de.get(i);
			if(erg==dentro.getErgazomenos() & dp== dentro.getDapanType()){
				typetotalcost+=dentro.getWorthofcomp();
			}
		}
		return typetotalcost;
	}
	
}


class ListMaker4 extends ListMaker{
	private ArrayList <Moves> moves = new ArrayList <Moves>();
	
	public void addMoves(Moves m){
		moves.add(m);
	}
	
	
	public void showList() {
		for (Moves step:moves)
			System.out.println (step+"\n");			
		
	}
	
	public int getsize(){
		return moves.size();
	}
	
	public double calculatealltheadvance(Ergazomenos erg){
		double alltheadvance=0;
		for (Moves mo:moves){
			if(mo instanceof Advance & erg==mo.getErgazomenos()){
				alltheadvance+=mo.getAmountcomp();
			}
		}
		return alltheadvance;
	}
	
	public void getthetransactions(Ergazomenos erg){
		for (Moves mo:moves){
			if(erg==mo.getErgazomenos()){
				System.out.println(mo);
			}
		}
	}
	
	public Moves getTransaction(int point){
		return moves.get(point);
	}
	
	public void removeextra(){
		moves.removeIf(n-> !(n instanceof Advance));
	}
	
	
	public void grandfinale(Ergazomenos erg){
		for(Moves towel : moves){
			if(towel instanceof Clearence){
				if(erg==towel.getErgazomenos()){
					String tissue = Double.toString(towel.getAmountcomp());
					System.out.println(erg+"\t\tFinal return: "+tissue);
				}
			}	
		}
	}

}









