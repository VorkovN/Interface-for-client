package Commands;
import Msg.MessageToServer;
import Route.MyCollection;
import Route.Route;

public class RemoveGreaterCommand implements Command {

    MyCollection myCollection = null;
    String arg = null;
    Route newRoute = null;

    public MessageToServer execute(){
        MessageToServer msg = new MessageToServer();
        try{
            msg.setStr(myCollection.removeGreater(arg));
            return msg;
        }catch (NumberFormatException | IndexOutOfBoundsException e){
            msg.setStr("Wrong id, please enter your command again!");
            return msg;
        }
    }

    public void setMyCollection(MyCollection myCollection) {
        this.myCollection = myCollection;
    }

    public MyCollection getMyCollection() {
        return myCollection;
    }

    public void setArg(String arg) {
        this.arg = arg;
    }

    public String getArg() {
        return arg;
    }

    public void setNewRoute(Route newRoute) {
        this.newRoute = newRoute;
    }

    public Route getNewRoute() {
        return newRoute;
    }
}
