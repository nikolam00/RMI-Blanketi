import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Vector;

public interface BingoManager extends java.rmi.Remote
{
	public Ticket playTicket(Vector<Integer>Numbers, BingoCallback cb) throws RemoteException;
	
	public void drawNumber() throws RemoteException;
	
}