package pl.edu.osp.objects;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class PlantManager {
    
    final private String plantsConfigPath =
            "configdata/plants.cfg";

    final private Map<String, PlantConstans> plantsConsts = new TreeMap<>();
    
    
    public PlantManager() {
        loadData();
    }
    
    public Plant getPlant(String className) {
        String fullClassName = "pl.edu.osp.objects."+className;
        Plant p = null;
        try {
          Constructor<?> c = Class.forName(fullClassName)
                .getConstructor(PlantConstans.class);
            p = (Plant) c.newInstance(getPlantConst(className));
        }
        catch(Exception e) {
            System.out.println("Error initialize while Tile: " + e.toString());
        }
        return p;
    }
    
    public PlantConstans getPlantConst(String className) {
        return plantsConsts.get(className);
    }
    
    protected String loadData() {
        File file = new File(plantsConfigPath);
        Scanner sc = null;
        String name;
        try {
            sc = new Scanner(file);
            while(sc.hasNext()) {
              name = sc.next();
              plantsConsts.put(name, new PlantConstans(sc.nextShort(),
                      sc.nextByte(), sc.nextByte(), name));
            }
        } catch(IOException e) {
            System.out.println("Cant load file for config plants");
        } finally {
            if(sc!= null) sc.close();
        }
        return "";
    }

}
