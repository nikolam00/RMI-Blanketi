import java.rmi.server.UnicastRemoteObject;
import java.rmi.RemoteException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Vector;

public class BingoManagerImpl extends UnicastRemoteObject implements BingoManager
{
	private HashMap<Integer, Ticket> odigraniTiketi;
	private boolean PoceloKolo;
	private ArrayList<Integer> izvuceniBrojevi;
	
	private int IdTicket;
	
	public BingoManagerImpl() throws RemoteException
	{
		this.odigraniTiketi = new HashMap<Integer, Ticket>();
		this.izvuceniBrojevi = new ArrayList<Integer>(90);
		this.PoceloKolo=false;
		
		this.IdTicket=1;
	}

	@Override
	public Ticket playTicket(Vector<Integer> Numbers, BingoCallback cb) throws RemoteException 
	{		
		if(this.PoceloKolo==true || Numbers.size()<15 || cb==null)
			return null;
		
		Ticket Novi_Tiket=new Ticket(this.IdTicket,Numbers,cb);
		
		this.odigraniTiketi.put(this.IdTicket, Novi_Tiket);
		this.IdTicket++;
		
		return Novi_Tiket;
	}

	@Override
	public void drawNumber() throws RemoteException 
	{
		Random rnd = new Random();
		int Izvucen_broj = rnd.nextInt(90)+1;
		
		while(this.izvuceniBrojevi.contains(Izvucen_broj)==true)
		{
			Izvucen_broj = rnd.nextInt(90)+1;
		}
		
		System.out.println("Izvucen je broj: "+ Izvucen_broj);
		this.izvuceniBrojevi.add(Izvucen_broj);
		
		if(this.izvuceniBrojevi.size()<15)
		{
			for(Ticket T: this.odigraniTiketi.values())
			{
				T.cb.isNotWinner();
			}
		}
		else
		{
			for(Ticket T: this.odigraniTiketi.values())
			{
				boolean Dobitan=true;
				
				for(Integer br : T.Brojevi)
				{
					if(!this.izvuceniBrojevi.contains(br))
						Dobitan=false;
				}
				
				if(Dobitan==true)
				{
					T.cb.isWinner();
				}
				else
					T.cb.isNotWinner();
			}
		}
	}
}