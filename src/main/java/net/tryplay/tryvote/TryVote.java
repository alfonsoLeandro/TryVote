package net.tryplay.tryvote;

import net.tryplay.tryvote.commands.MainCommand;
import net.tryplay.tryvote.commands.MainCommandTabCompleter;
import net.tryplay.tryvote.utils.Reloadable;
import net.tryplay.tryvote.utils.YamlFile;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashSet;
import java.util.Set;

public final class TryVote extends JavaPlugin {

    private final Set<Reloadable> reloadables = new HashSet<>();
    final private String version = getDescription().getVersion();
    final private char color = 'e';
    final private String name = "&f[&" + color + getDescription().getName() + "&f]";
    //private String latestVersion; TODO
    private YamlFile configYaml;

    /**
     * Sends a message to the console, with colors and prefix added.
     * @param msg The message to be sent.
     */
    private void send(String msg) {
        Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', name + " " + msg));
    }


    /**
     * Plugin enable logic.
     */
    @Override
    public void onEnable() {
        send("&aEnabled&f. Version: &e" + version);
        send("&fThank you for using my plugin! &" + color + getDescription().getName() + "&f By " + getDescription().getAuthors().get(0));
        send("&fJoin my discord server at &chttps://discordapp.com/invite/ZznhQud");
        send("Please consider subscribing to my yt channel: &c" + getDescription().getWebsite());
        reloadFiles();
        registerEvents();
        registerCommands();
        //updateChecker();
    }

    /**
     * Plugin disable logic.
     */
    @Override
    public void onDisable() {
        send("&cDisabled&f. Version: &e" + version);
        send("&fThank you for using my plugin! &" + color + getDescription().getName() + "&f By " + getDescription().getAuthors().get(0));
        send("&fJoin my discord server at &chttps://discordapp.com/invite/ZznhQud");
        send("Please consider subscribing to my yt channel: &c" + getDescription().getWebsite());
    }

//TODO: Update checker
//    private void updateChecker(){
//        try {
//            HttpURLConnection con = (HttpURLConnection) new URL(
//                    "https://api.spigotmc.org/legacy/update.php?resource=71422").openConnection();
//            final int timed_out = 1250;
//            con.setConnectTimeout(timed_out);
//            con.setReadTimeout(timed_out);
//            latestVersion = new BufferedReader(new InputStreamReader(con.getInputStream())).readLine();
//            if (latestVersion.length() <= 7) {
//                if(!version.equals(latestVersion)){
//                    String exclamation = "&e&l(&4&l!&e&l)";
//                    send(exclamation +" &cThere is a new version available. &e(&7"+latestVersion+"&e)");
//                    send(exclamation +" &cDownload it here: &fhttp://bit.ly/2Pl4Rg7");
//                }
//            }
//        } catch (Exception ex) {
//            send("&cThere was an error while checking for updates");
//        }
//    }


    /**
     * Gets the plugins current version.
     * @return The version string.
     */
    public String getVersion() {
        return this.version;
    }

//    /**
//     * Gets the latest version available from spigot.
//     * @return The latest version or null.
//     */
//    public String getLatestVersion() {
//        return this.latestVersion;
//    }



    /**
     * Registers and reloads plugin files.
     * Please use {@link #reload()} for reloading the plugin.
     */
    public void reloadFiles() {
        configYaml = new YamlFile(this, "config.yml");
    }


    /**
     * Reloads the entire plugin.
     */
    public void reload(){
        reloadFiles();
        for(Reloadable rl : reloadables){
            rl.reload();
        }
    }

    /**
     * Registers the event listeners.
     */
    private void registerEvents() {
        PluginManager pm = getServer().getPluginManager();
        //pm.registerEvents(new Event(this), this);
    }


    /**
     * Registers commands and command classes.
     */
    private void registerCommands() {
        PluginCommand mainCommand = getCommand("tryVote");

        if(mainCommand == null){
            send("&cCommands were not registered properly. Please check your plugin.yml is intact");
            send("&cDisabling TryVote"); //TODO
            this.setEnabled(false);
            return;
        }

        MainCommand mainCommandExecutor = new MainCommand(this);

        MainCommandTabCompleter mainCommandTabCompleter = new MainCommandTabCompleter();

        reloadables.add(mainCommandExecutor);

        mainCommand.setExecutor(mainCommandExecutor);
        mainCommand.setTabCompleter(mainCommandTabCompleter);


    }

    /**
     * Get the config YamlFile.
     * @return The YamlFile containing the config file.
     */
    public YamlFile getConfigYaml(){
        return this.configYaml;
    }



}
