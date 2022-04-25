import java.rmi.RemoteException;

public interface BingoCallback extends java.rmi.Remote
{
	public void isWinner() throws RemoteException;
	public void isNotWinner() throws RemoteException;
}
