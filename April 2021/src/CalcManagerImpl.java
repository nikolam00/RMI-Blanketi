import java.rmi.server.UnicastRemoteObject;
import java.rmi.RemoteException;
import java.util.LinkedList;

public class CalcManagerImpl extends UnicastRemoteObject implements CalcManager
{
	private LinkedList<VCalcRequest> Zahtevi;
	private VCalcRequest Trenutno;
	private int ID;
	
	public CalcManagerImpl() throws RemoteException
	{
		super();
		
		this.ID=1;
		
		Trenutno=null;
		Zahtevi=new LinkedList<VCalcRequest>();
	}

	@Override
	public int SendVCalcRequest(VCalcRequest req) throws RemoteException {
		
		if(!req.CheckRequest())
			return -1;
		else
		{
			VCalcRequest Tmp=new VCalcRequest(this.ID,req.vrati_A(),req.vrati_B(),req.cb);
			this.ID++;
			
			this.Zahtevi.add(Tmp);
			
			
			if(this.Trenutno!=null)
			{
				System.out.println("trenutno se obradjuje"+this.Trenutno.cId);
				return this.Trenutno.cId;
			}
			else
				{
					System.out.println("Trenutno se obradjuje nijedan\n");
					return 0;
				}
		}
	}

	@Override
	public boolean RunNextVCalc() throws RemoteException 
	{
		if(this.Zahtevi.isEmpty())
			return false;
	
		System.out.println("Usao");
		System.out.flush();
		this.Trenutno=this.Zahtevi.pollFirst();
		
		double Rezultat= this.Trenutno.Sracunaj_Izraz();
		
		this.Trenutno.cb.onDone(this.Trenutno.cId, Rezultat);
		
		return true;
	}
}