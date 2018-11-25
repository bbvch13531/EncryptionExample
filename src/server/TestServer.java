package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class TestServer{

	public void go() throws IOException
	{
		ServerSocket ss = null;
		Socket s = null;

		try
		{
			ss = new ServerSocket(5432);
			System.out.println("**서버 실행**");

			while(true)
			{
				s = ss.accept(); 
				ServerThread st = new ServerThread(s);
				st.start(); 
				System.out.println(s.getInetAddress()+"접속");
			}
		}
		finally
		{
			if (s != null)
			s.close();
			if (ss != null)
			ss.close();
			System.out.println("**서버 종료**");
		}
	}

	public static void main(String[] args) 
	{
		TestServer ts = new TestServer();
		try 
		{
			ts.go();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}

	}
}
