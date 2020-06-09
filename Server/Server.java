package Server;

import Client.User;
import Commands.Command;
import Route.MyCollection;
import Route.Route;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
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
        try (ObjectInputStream b = new ObjectInputStream(socket.socket().getInputStream())){
            if ((Boolean) b.readObject()){
                try (ObjectInputStream fromClient = new ObjectInputStream(socket.socket().getInputStream())){
                    Object obj = fromClient.readObject();
                    if (obj instanceof Command) {
                        User user = (User) fromClient.readObject();
                        user.setArr(myCollection.getArr());
                        Command cmd = (Command) obj;
                        executeIt.execute(new MessageHandler(socket, myCollection, cmd, user));//
                        System.out.println("Connection accepted. com");//
                    } else {
                        User user = (User) obj;
                        user.setArr(myCollection.getArr());
                        executeIt.execute(new MessageHandler(socket, myCollection, user));//
                        System.out.println("Connection accepted. us");//
                    }
                    Thread.sleep(1000);
                } catch (IOException | ClassNotFoundException | InterruptedException ignored) {
                }
                try {
                    socket.close();
                } catch (IOException ignored) {
                }
            }
        }
        catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}