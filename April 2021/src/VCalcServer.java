import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.util.Scanner;

public class VCalcServer 
{
	
	public VCalcServer()
	{
		try
		{
			// U Serveru mi treba jedna istanca za menadzera kojeg posle povezujemo preko Naming
			
			CalcManager Manager=new CalcManagerImpl();
			
			LocateRegistry.createRegistry(1099);
			Naming.rebind("rmi://localhost:1099/Kalkulator", Manager);
			
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
		}
	}
	
	public static void main(String[] args)
	{
        try 
        {
            new VCalcServer();

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
