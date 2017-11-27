package Operation.WriteNewMsg;

public class staticMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
    TestMultiWrite t =new TestMultiWrite();
   
    try {
		t.beforeClass();
	} catch (InterruptedException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
    for (int i=0;i<20;i++)
    {
    	     try {
				//t.minute1();
				Thread.sleep(10000);
				System.out.println(i);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    }
	}

}
