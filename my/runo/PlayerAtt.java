package my.runo;
import com.anish.calabashbros.Player;
public class PlayerAtt implements Runnable {
    private Player player;
    public PlayerAtt(Player p) 
    {
        player=p;
    }
    @Override
    public void run() {
            player.attack();
        }
}
