package net.tryplay.tryvote.commands;

import net.tryplay.tryvote.TryVote;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;

public final class MainCommand implements CommandExecutor {

    private final TryVote plugin;
    //Translatable messages
    private String reloaded;

    /**
     * MainCommand class constructor.
     * @param plugin The main class instance.
     */
    public MainCommand(TryVote plugin){
        this.plugin = plugin;
        loadMessages();
    }

    /**
     * Pre loads every message used for this command.
     */
    protected void loadMessages(){
        FileConfiguration config = plugin.getConfigYaml().getAccess();
        reloaded = config.getString("config.messages.reloaded");
    }



    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if(args.length == 0 || args[0].equalsIgnoreCase("help")) {
            send(sender, "&6List of commands");
            send(sender, "&f/"+label+" help");
            send(sender, "&f/"+label+" version");
            send(sender, "&f/"+label+" reload");



        }else if(args[0].equalsIgnoreCase("reload")) {
            if(!sender.hasPermission(/*TODO plugin name*/".reload")) {
                send(sender, noPerm);
                return true;
            }
            plugin.reloadFiles();
            loadMessages();
            send(sender, reloaded);


        }else if(args[0].equalsIgnoreCase("version")) {
            if(!sender.hasPermission(/*TODO plugin name*/".version")) {
                send(sender, noPerm);
                return true;
            }
//            if(!plugin.getVersion().equals(plugin.getLatestVersion())){
//                send(sender, "&fVersion: &e"+plugin.getVersion()+"&f. &cUpdate available!");
//                send(sender, "&fDownload here: http://bit.ly/2Pl4Rg7");
//                return true;
//            }
            send(sender, "&fVersion: &e"+plugin.getVersion()+"&f. &aUp to date!");


            //unknown command
        }else {
            send(sender, unknown.replace("%command%", label));
        }



        return true;
    }


    public void reload(){
        loadMessages();
    }
}
