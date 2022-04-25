import java.rmi.Remote;
import java.rmi.RemoteException;

public interface CalcManager extends Remote 
{
	public int SendVCalcRequest(VCalcRequest req) throws RemoteException;
	
	public boolean RunNextVCalc() throws RemoteException;
}
