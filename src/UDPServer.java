import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class UDPServer implements Runnable {

    private DatagramSocket socket;
    private boolean running;
    private byte[] buf = new byte[256];
    private static final int PORT = 90;

    public UDPServer()  {
        try {
            socket = new DatagramSocket(PORT);

        } catch (SocketException e) {
            e.printStackTrace();
        }
    }

    /**
     * Threading
     */
    public void run() {
        running = true;

        while (running) {
            DatagramPacket packet = new DatagramPacket(buf, buf.length);
            try {
                socket.receive(packet);
                InetAddress address = packet.getAddress();
                int port = packet.getPort();
                packet = new DatagramPacket(buf, buf.length, address, port);
                String received = new String(packet.getData(), 0, packet.getLength());
                String trim = received.trim();

                System.out.println(trim);
                Coords coords = Utils.parseCoords(trim);
                System.out.println(coords);
                if(coords != null) {
                    EditorPanel.actualRobot.setX((int)coords.getX());
                    EditorPanel.actualRobot.setY((int)coords.getY());
                    EditorPanel.actualRobot.setAngle((int)coords.getTheta());
                }

//                Coords coords = Utils.parseCoords(trim);
//                System.out.println(coords);

//                if (received.equals("end")) {
//                    running = false;
//                    continue;
//                }

            } catch (IOException e) {
                e.printStackTrace();
            }
            //socket.send(packet);
        }
        socket.close();
    }



    public static void main(String[] args) {

        Coords coords = Utils.parseCoords("X:2236,Y:243.328,T:80.56\n");

        System.out.println(coords);

    }
}
