/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package toolbox.modeltools.rcaller;

import java.io.InputStream;

class PrintStream extends Thread 
{
	java.io.InputStream __is = null;
	public PrintStream(InputStream is) 
	{
		__is = is;
	} 

	public void run() 
	{
		try 
		{
			while(this != null) 
			{
				int _ch = __is.read();
				if(_ch != -1) 
					System.out.print((char)_ch); 
				else break;
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		} 
	}
}
	