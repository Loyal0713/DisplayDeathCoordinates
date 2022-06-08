package loyal0713.dispdeathcoord;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.text.Text;

public class DisplayDeathCoordinates implements ModInitializer {
    private double x, y, z;
    private boolean justDied = false;

    @Override
    public void onInitialize() {
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            try {

                // probably a better way to do this but it works
                if (!this.justDied && client.player.isDead()) {
                    this.justDied = true;
    
                    // send message to player
                    String msg = "Death coordinate: " + (int)this.x + ", " + (int)this.y + ", " + (int)this.z;
                    client.player.sendMessage(Text.of(msg), false);
                }

                // update player position
                if (client.player.isAlive()) {
                    this.justDied = false;
                    this.x = client.player.getX();
                    this.y = client.player.getY();
                    this.z = client.player.getZ();
                }
            } catch (NullPointerException e) {} // catch for no player (basically just in menus)
           
        });
    }

}
