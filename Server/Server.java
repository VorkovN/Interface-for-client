package Server;

import Client.User;
import Commands.Command;
import Route.MyCollection;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.nio.channels.SocketChannel;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server implements Runnable {

    SocketChannel socket = null;
    MyCollection myCollection = null;
    static ExecutorService executeIt = Executors.newCachedThreadPool();


    public Server(SocketChannel socket, MyCollection myCollection) {
        this.socket = socket;
        this.myCollection = myCollection;
    }

    @Override
    public void run() {
        try (ObjectInputStream bool = new ObjectInputStream(socket.socket().getInputStream())){
            if ((Boolean) bool.readObject()){
                try (ObjectInputStream fromClient = new ObjectInputStream(socket.socket().getInputStream())){
                    Object obj = fromClient.readObject();
                    if (obj instanceof Command) {
                        User user = (User) fromClient.readObject();
                        Command cmd = (Command) obj;
                        executeIt.execute(new MessageHandler(socket, myCollection, cmd, user));//
                        System.out.println("Connection accepted. com");//
                    } else {
                        User user = (User) obj;
                        System.out.println("Connection accepted. us");//
                        executeIt.execute(new MessageHandler(socket, myCollection, user));//
                    }
                    Thread.sleep(1000);
                } catch (IOException | ClassNotFoundException | InterruptedException e) {
                    e.printStackTrace();
                }
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}