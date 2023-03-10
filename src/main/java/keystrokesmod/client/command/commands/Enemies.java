package keystrokesmod.client.command.commands;

import keystrokesmod.client.command.Command;
import keystrokesmod.client.module.modules.combat.AimAssist;
import net.minecraft.entity.Entity;

import static keystrokesmod.client.clickgui.raven.Terminal.print;

public class Enemies extends Command {
    public Enemies() {
        super("enemies", "Allows you to manage and view your enemies list", 1, 2, new String[]{"add / remove / list", "Player's name"}, new String[] {"e"});
    }

    @Override
    public void onCall(String[] args){
        if (args.length == 0){
            listEnemies();
        }

        else if(args[0].equalsIgnoreCase("list")) {
            listEnemies();
        }

        else if(args.length == 2){
            if(args[0].equalsIgnoreCase("add")){
                boolean added = AimAssist.addEnemy(args[1]);
                if (added) {
                    print("Successfully added " + args[1] + " to your enemies list!");
                } else {
                    print("An error occurred!");
                }
            }
            else if(args[0].equalsIgnoreCase("remove")){
                boolean removed = AimAssist.removeEnemy(args[1]);
                if (removed) {
                    print("Successfully removed " + args[1] + " from your enemies list!");
                } else {
                    print("An error occurred!");
                }
            }
        }
        else {
            this.incorrectArgs();
        }
    }

    public void listEnemies(){
        if(AimAssist.getEnemies().isEmpty()){
            print("You have no enemies. :)");
        }
        else {
            print("Your enemies are:");
            for (Entity entity : AimAssist.getEnemies()){
                print(entity.getName());
            }
        }
    }
}
