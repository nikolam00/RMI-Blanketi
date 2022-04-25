import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.Scanner;
import java.rmi.server.UnicastRemoteObject;
import java.util.Vector;


public class VCalcClient {

	// U Klijenta prvo odradim implementaciju interfejsa CallBack, tu napravim klasu i konstruktor za taj callback
	
	public class VCalcCallbackImpl extends UnicastRemoteObject implements VCalcCallback
	{
		public VCalcCallbackImpl() throws RemoteException
		{
			super();
		}

		
		public void onDone(int cid, double result) throws RemoteException 
		{
			System.out.println("Rezultat izracunavanja "+cid+" je: "+result+"\n");
		}
	}
	
	public VCalcClient()
	{
		try
		{
			CalcManager Manager=new CalcManagerImpl();
			
			Manager=(CalcManager)Naming.lookup("rmi://localhost:1099/Kalkulator");
			
			Scanner Input = new Scanner(System.in);
			
			System.out.println("Unesite dimenziju vektora:");
			int N;
			N=Input.nextInt();
			
			Vector<Integer> A = new Vector<Integer>(N);
			Vector<Integer> B = new Vector<Integer>(N);
			
			System.out.println("Unesite elemente vektora A:\n");
			
			for(int i=0;i<N;i++)
			{
				A.add(Input.nextInt());
			}
			
			System.out.println("Unesite elemente vektora B:\n");
			
			for(int i=0;i<N;i++)
			{
				B.add(Input.nextInt());
			}
			
			VCalcCallback cb = new VCalcCallbackImpl();
			
			System.out.println("Prosao 1");
			
			VCalcRequest Req=new VCalcRequest(A,B,cb);
			
			System.out.println("Prosao 2");
			
			int Trenutno=Manager.SendVCalcRequest(Req);
			
			System.out.println("Prosao 3");
			
			boolean Iskaz=Manager.RunNextVCalc();
			
			System.out.println("Prosao 4");
			
			System.out.println("Trenutno se izracunava "+ Trenutno);
			
			Input.close();
			
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
		}
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		 try 
	     {
	         new VCalcClient();  
	     } 
	     catch (Exception e) 
	     {
	         e.printStackTrace();
	     }
	}

}
