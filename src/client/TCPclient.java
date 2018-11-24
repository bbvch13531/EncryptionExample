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
            String ip = "39.115.19.133"; //접속 주소
            int port = 5432; //접속할 서버 포트
            Socket socket = new Socket(ip, port); //클라이언트의 소켓 생성

            
            OutputStream out = socket.getOutputStream(); //서버의 소켓으로부터 출력을 받음
            InputStream in = socket.getInputStream(); //서버의 소켓으로부터 입력을 받음
             
            PrintWriter pw = new PrintWriter(new OutputStreamWriter(out)); //출력 스트림을 변환
            BufferedReader br = new BufferedReader(new InputStreamReader(in)); //입력 스트림을 변환

            String myMsg = null; //전달 메시지
            
            
            AES256Util256 aes256 = new AES256Util256("1234567890123111456");
    		MD5_hash md5 = new MD5_hash();
    		
            myMsg = "20000000"; //학번
            String Enctext = aes256.aesEncode(myMsg);
    		String hash = md5.testMD5(Enctext);;
    		
    		pw.println(myMsg); //PrintWriter를 이용하여 서버에게 전달
            pw.flush(); //버퍼 비우기
    		
            pw.println(Enctext); //PrintWriter를 이용하여 서버에게 전달
            pw.flush(); //버퍼 비우기
            
            pw.println(hash); //PrintWriter를 이용하여 서버에게 전달
            pw.flush(); //버퍼 비우기
                        
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
