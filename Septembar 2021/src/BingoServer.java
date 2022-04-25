import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.util.Scanner;

public class BingoServer 
{
	public BingoServer()
	{
		try
		{
			BingoManager Manager=new BingoManagerImpl();
			
			LocateRegistry.createRegistry(1099);
			Naming.rebind("rmi://localhost:1099/Bingo", Manager);
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		
	}
	
	
	public static void main(String[] args) 
	{
		try 
        {
            new BingoServer();

            System.out.println("Server je pokrenut....\nPritisnite Enter za kraj...");

            Scanner s = new Scanner(System.in);
            s.nextLine();
            s.close();
            
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }
	}
}
