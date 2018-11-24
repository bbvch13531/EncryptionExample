package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;


public class ServerThread extends Thread
{

	private Socket socket;
	private BufferedReader br = null;
	private PrintWriter pw = null;
	private String userIP = "������";

	ServerThread(Socket s)
	{
		this.socket = s;
	}

	public void run()
	{
		try
		{
			service();
		}
		catch(IOException e)
		{
			System.out.println("**"+userIP+"�� ���� ����.");
		}
		finally
		{
			try 
			{
				closeAll();
			} 
			catch (IOException e) 
			{
				e.printStackTrace();
			}
		}
	}

	private void service()throws IOException
	{
		br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		pw = new PrintWriter(socket.getOutputStream(), true);
		String str = null;
		while(true)
		{
			str = br.readLine();
			if(str == null)
			{
				System.out.println(userIP+"���� ������ �����߽��ϴ�.");
				break;
			}
			System.out.println(socket.getInetAddress()+"��: "+str);
			pw.println(str);
		}
	}
	public void closeAll()throws IOException
	{
		if (pw != null)
			pw.close();
		if (br != null)
			br.close();
		if (socket != null)
			socket.close();
	}
}
