package Client;

import Commands.*;
import Msg.MessageToServer;
import Route.Route;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class CommandExecutor {

    private String address;
    private int port;
    private User user;
    private static CommandExecutor commandExecutor = null;



    private static Map<String, Command> commands = new HashMap<>();
    public ArrayList<String> history = new ArrayList<>();

    public static CommandExecutor getCommandExecutor(){
        if (commandExecutor == null) {
            commandExecutor = new CommandExecutor();


            addCommand("add", new AddCommand());
            addCommand("clear", new ClearCommand());
            addCommand("count_less_than_distance", new CountLessThanDistanceCommand());
            addCommand("execute_script", new ExecuteScriptCommand(commandExecutor));
            addCommand("exit", new ExitCommand());
            addCommand("filter_greater_than_distance", new FilterGreaterThanDistanceCommand());
            addCommand("help", new HelpCommand());
            addCommand("history", new HistoryCommand(commandExecutor));
            addCommand("info", new InfoCommand());
            addCommand("remove_all_by_distance", new RemoveAllByDistanceCommand());
            addCommand("remove_by_id", new RemoveByIdCommand());
            addCommand("remove_first", new RemoveFirstCommand());
            addCommand("remove_greater", new RemoveGreaterCommand());
            addCommand("save", new SaveCommand());
            addCommand("show", new ShowCommand());
            addCommand("update", new UpdateCommand());

        }
        return commandExecutor;
    }


    public static void addCommand(String commandName, Command command) {
        commands.put(commandName, command);
    }

    public void execute(String action) {
        try(Socket socket = new Socket(address, port)){
            (new ObjectOutputStream(socket.getOutputStream())).writeObject(Boolean.TRUE);
            ObjectOutputStream toServer = new ObjectOutputStream(socket.getOutputStream());
            String[] actionParts = action.split(" ");
            if (action.isEmpty()) {
                return;
            }
            if (actionParts.length == 1) {
                Command command = commands.get(actionParts[0]);
                if (command != null) {
                    historyList(actionParts[0]);
                    if (command instanceof HistoryCommand || command instanceof ExitCommand) {
                        command.execute();
                    } else {
                        if (command instanceof AddCommand) {
                            Route newRoute = null;
                            try {
                                newRoute = new Initialization().initialization(user);
                            } catch (NumberFormatException e) {
                                System.out.println("\nWrong input, please enter your values again!");
                            }
                            command.setNewRoute(newRoute);
                        }
                        toServer.writeObject(command);
                        toServer.writeObject(user);
                        ObjectInputStream fromServer = new ObjectInputStream(socket.getInputStream());
                        System.out.println(((MessageToServer) fromServer.readObject()).getStr());
                        user = (User) fromServer.readObject();
                        fromServer.close();
                    }
                } else {
                    System.out.println("Commands.Command doesn't exist");
                }
            } else if (actionParts.length == 2) {
                Command command = commands.get(actionParts[0]);
                String arg = actionParts[1];
                if (command != null) {
                    historyList(actionParts[0]);
                    command.setArg(arg);
                    if (command instanceof ExecuteScriptCommand) {
                        toServer.close();
                        ObjectInputStream fromServer = new ObjectInputStream(socket.getInputStream());
                        fromServer.close();
                        command.execute();
                    } else {
                        if (command instanceof UpdateCommand) {
                            System.out.println(checkId(user, arg));
                            if (checkId(user, arg)){
                                Route newRoute = null;
                                try {
                                    newRoute = new Initialization().initialization(user);
                                } catch (NumberFormatException e) {
                                    System.out.println("\nWrong input, please enter your values again!");
                                }
                                command.setNewRoute(newRoute);
                                toServer.writeObject(command);
                                toServer.writeObject(user);
                                ObjectInputStream fromServer = new ObjectInputStream(socket.getInputStream());
                                System.out.println(((MessageToServer) fromServer.readObject()).getStr());
                                user = (User) fromServer.readObject();
                                fromServer.close();
                            }
                            else{
                                System.out.println("This element isn't belongs to you");
                            }
                        }else{
                            toServer.writeObject(command);
                            toServer.writeObject(user);
                            ObjectInputStream fromServer = new ObjectInputStream(socket.getInputStream());
                            System.out.println(((MessageToServer) fromServer.readObject()).getStr());
                            user = (User) fromServer.readObject();
                            fromServer.close();
                        }
                    }
                } else {
                    System.out.println("Commands.Command doesn't exist");
                }

            } else {
                System.out.println("Wrong command input");
            }
            toServer.close();
        }catch(IOException | ClassNotFoundException ignored){
        }
    }


    public void historyList(String command){
        if(history.size() > 6) {
            history.remove(0);
        }
        history.add(command);
    }

    public User registrationAuthorization(){
        try(Socket socket = new Socket(address, port)) {
            Thread.sleep(200);
            (new ObjectOutputStream(socket.getOutputStream())).writeObject(Boolean.TRUE);
            ObjectOutputStream toServer = new ObjectOutputStream(socket.getOutputStream());
            toServer.writeObject(user);
            ObjectInputStream fromServer = new ObjectInputStream(socket.getInputStream());
            user = (User)fromServer.readObject();
            toServer.close();
            fromServer.close();
        }
        catch (IOException | ClassNotFoundException | InterruptedException e){
            e.printStackTrace();
        }
        System.out.println(user.getIds());
        System.out.println(user.getName());
        System.out.println(user.getStatus());
        return user;
    }

    public boolean checkId(User user, String arg){
        System.out.println(user.getIds());
        int a = Integer.parseInt(arg);
        for (int i: user.getIds()) {
            if (i == a){
                return true;
            }
        }
        return false;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public void checkAddreess() throws IOException {
        try(Socket socket = new Socket(address, port)) {
            (new ObjectOutputStream(socket.getOutputStream())).writeObject(Boolean.FALSE);
        }
    }
}