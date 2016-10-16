package br.unb.cic.lp.gol;
import java.util.LinkedList;


public class CareTaker {
	 LinkedList<Memento> mementoList = new LinkedList<Memento>(); 
	private int restore=0;
	
	public void add(Memento state){
	      	if(mementoList.size()>9 ){
	      	       mementoList.removeFirst();
	      	       mementoList.addLast(state);
	      	}
	      	else{
	      		mementoList.addLast(state);
	      	}
	      	restore= mementoList.size();
	}
	
	public  Memento get(int index){
		return mementoList.get(index);
		}
	
	public  Memento get(){
		if(restore == mementoList.size()){
			restore --;
			return mementoList.getLast();
		}
		else if(restore<1){
			restore--;
			return mementoList.getFirst();
		}
		else{
			return get(--restore);
		}
	}
	
}
