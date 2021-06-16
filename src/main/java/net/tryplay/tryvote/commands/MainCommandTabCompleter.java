package net.tryplay.tryvote.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.List;

public class MainCommandTabCompleter implements TabCompleter {

    /**
     * Checks if a string is equal (ignoring upper cases) to another string completed or uncompleted.
     * @param input The first string.
     * @param string The string to "cut into pieces" in order to compare every possibility to the first string.
     * @return True if the string is at least partially equal to the input.
     */
    public boolean equalsToStrings(String input, String string){
        for(int i = 0; i < string.length(); i++){
            if(input.equalsIgnoreCase(string.substring(0,i))) return true;
        }
        return false;
    }


    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        List<String> completions = new ArrayList<>();

        if(args.length == 1) {
            if(args[0].equalsIgnoreCase("")) {
                completions.add("help");
                completions.add("version");
                completions.add("reload");

            } else if(equalsToStrings(args[0], "help")) {
                completions.add("help");

            } else if(equalsToStrings(args[0], "version")) {
                completions.add("version");

            } else if(equalsToStrings(args[0], "reload")) {
                completions.add("reload");
            }

        }
        return completions;
    }



}
