import java.io.Serializable;
import java.util.Vector;

public class Ticket implements Serializable
{
    public int Id;
    public Vector<Integer> Brojevi;
    public BingoCallback cb;
 
    public Ticket(int Id, Vector<Integer> Numbers, BingoCallback callback)
    {
        this.Id = Id;
        this.Brojevi = new Vector<>(15);
        
        for(int i=0;i<15;i++)
        {
        	this.Brojevi.add(Numbers.get(i));
        }
        this.cb=callback;
    }
}