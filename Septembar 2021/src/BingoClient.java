import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;
import java.util.Vector;

import java.rmi.Naming;
import java.rmi.RemoteException;


public class BingoClient 
{
	public class BingoCallbackImpl extends UnicastRemoteObject implements BingoCallback
	{
		public BingoCallbackImpl() throws RemoteException
		{
			super();
		}

		@Override
		public void isWinner() throws RemoteException 
		{
			System.out.println("Tiket je dobitan!");	
		}

		@Override
		public void isNotWinner() throws RemoteException 
		{
			System.out.println("Tiket nije dobitan!");
		}
	}
	
	public BingoClient() throws RemoteException
	{
		try
		{
			BingoManager Manager=new BingoManagerImpl();
			
			Manager=(BingoManager)Naming.lookup("rmi://localhost:1099/Bingo");
			
			Scanner Input = new Scanner(System.in);
			
			Vector<Integer> Odigrani_brojevi = new Vector<Integer>(15);
			
			System.out.println("Unesite 15 brojeva za kombinaciju:\n");
			
			for(int i=0;i<15;i++)
			{
				Odigrani_brojevi.add(Input.nextInt());
			}
			
			BingoCallback cb = new BingoCallbackImpl();
			
			Ticket Odigran_Tiket=Manager.playTicket(Odigrani_brojevi, cb);
			
			Manager.drawNumber();
			
			Input.close();
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
	}
	
	public static void main(String[] args) {
		try 
        {
            new BingoClient();  
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }

	}
}
