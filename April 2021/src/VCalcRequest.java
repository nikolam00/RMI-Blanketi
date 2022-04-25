import java.io.Serializable;
import java.util.Vector;
import java.math.*;

public class VCalcRequest implements Serializable{

	int cId;
	Vector<Integer> A;
	Vector<Integer> B;
	
	public VCalcCallback cb;

	public VCalcRequest(int CID,Vector<Integer> A,Vector<Integer> B,VCalcCallback cb)
	{
		this.cId=CID;
		
		this.A=A;
		this.B=B;
		this.cb=cb;
	}
	
	public VCalcRequest(Vector<Integer> A,Vector<Integer> B,VCalcCallback cb)
	{
		this.A=A;
		this.B=B;
		this.cb=cb;
	}
	
	public int get_cId() 
	{
		return this.cId;
	}
	public Vector<Integer> vrati_A()
	{
		return this.A;
	}
	public Vector<Integer> vrati_B()
	{
		return this.B;
	}
	
	public boolean CheckRequest()
	{
		if(this==null || this.cb == null || (this.A.size()!=this.B.size()) || this.A==null || this.B==null)
				return false;
		return true;
	}
	
	public Vector<Integer> Razlika(Vector<Integer> A,Vector<Integer> B)
	{
		
		Vector<Integer> Razlika=new Vector<Integer>(A.size());
				
		for(int i=0;i<A.size();i++)
		{
			Razlika.set(i, A.get(i)-B.get(i));	
		}
				
		return Razlika;
		   
	}
	
	public double Norma(Vector<Integer> v)
	{
		double S=0;
		
		for(int i=0;i<v.size();i++)
		{
			S=S+v.get(i)*v.get(i);
		}
		
		S=Math.sqrt(S);
		
		return S;
	}
	
	public double Sracunaj_Izraz()
	{
		double Izraz=1;
		
		Vector<Integer> V1=new Vector<Integer>();
		Vector<Integer> V2=new Vector<Integer>();
		
		
		V1=this.Razlika(this.A, this.B);
		V2=this.Razlika(this.B, this.A);
		
		double Sum=0;
		
		for(int i=0;i<V1.size();i++)
		{
			Sum+=V1.get(i)*V2.get(i);
		}
		
		
		Izraz=Izraz*Sum*this.Norma(V1);
		
		return Izraz;
	}
	
}
