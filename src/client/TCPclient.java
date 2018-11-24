package client;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;


public class TCPclient {

    public static void main(String[] args) 
    {
        try{
            String ip = "39.115.19.133"; //���� �ּ�
            int port = 5432; //������ ���� ��Ʈ
            Socket socket = new Socket(ip, port); //Ŭ���̾�Ʈ�� ���� ����

            
            OutputStream out = socket.getOutputStream(); //������ �������κ��� ����� ����
            InputStream in = socket.getInputStream(); //������ �������κ��� �Է��� ����
             
            PrintWriter pw = new PrintWriter(new OutputStreamWriter(out)); //��� ��Ʈ���� ��ȯ
            BufferedReader br = new BufferedReader(new InputStreamReader(in)); //�Է� ��Ʈ���� ��ȯ

            String myMsg = null; //���� �޽���
            
            
            AES256Util256 aes256 = new AES256Util256("1234567890123111456");
    		MD5_hash md5 = new MD5_hash();
    		
            myMsg = "20000000"; //�й�
            String Enctext = aes256.aesEncode(myMsg);
    		String hash = md5.testMD5(Enctext);;
    		
    		pw.println(myMsg); //PrintWriter�� �̿��Ͽ� �������� ����
            pw.flush(); //���� ����
    		
            pw.println(Enctext); //PrintWriter�� �̿��Ͽ� �������� ����
            pw.flush(); //���� ����
            
            pw.println(hash); //PrintWriter�� �̿��Ͽ� �������� ����
            pw.flush(); //���� ����
                        
            pw.close();
            br.close();
            socket.close();

        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}
