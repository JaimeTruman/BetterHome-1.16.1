package es.serversurvival.data;

import es.serversurvival.main.Main;
import es.serversurvival.objects.Home;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public final class HomesFileManager {
    private File file;

    public void setUpFields() throws Exception {
        File pluginFile = new File(Main.getInstance().getDataFolder().getAbsolutePath());

        this.file = new File(pluginFile.getAbsolutePath() + File.separator + "homes.dat");

        if (!pluginFile.exists()) {
            pluginFile.mkdirs();

            if (!file.exists()) {
                file.createNewFile();
            }
        } else {
            HomesCache.INSTANCE.setHomes(this.getHomes(file));
        }
    }

    public void saveHomes(List<Home> set) throws Exception {
        ObjectOutputStream dataFlowOutput = new ObjectOutputStream(new FileOutputStream(file));
        dataFlowOutput.writeObject(set);
        dataFlowOutput.close();
    }

    private ArrayList<Home> getHomes(File file) throws Exception {
        ObjectInputStream dataFlowInput = new ObjectInputStream(new FileInputStream(file));
        Object object = dataFlowInput.readObject();
        dataFlowInput.close();

        return (ArrayList<Home>) object;
    }
}
