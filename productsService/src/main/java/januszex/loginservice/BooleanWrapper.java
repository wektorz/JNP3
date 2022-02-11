package januszex.loginservice;

public class BooleanWrapper {
    private boolean state;
    BooleanWrapper()
    {
        state =true;
    }
    public void set(boolean state){ this.state=state; }
    public boolean get(){ return state; }
}
