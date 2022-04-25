import java.rmi.Remote;
import java.rmi.RemoteException;

public interface VCalcCallback extends Remote
{
	public void onDone(int cId,double Result) throws RemoteException;
}
