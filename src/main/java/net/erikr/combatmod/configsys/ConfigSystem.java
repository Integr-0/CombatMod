package net.erikr.combatmod.configsys;

import net.erikr.combatmod.CombatModClient;
import net.erikr.combatmod.SharedVariables;
import net.fabricmc.loader.api.FabricLoader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;
import java.util.function.UnaryOperator;

public class ConfigSystem {
    static Path configPath = FabricLoader.getInstance().getConfigDir();

    public static void createConfig() {
        try {
            File config = new File(configPath.toString()+"combatmod_config.txt");
            if (config.createNewFile()) {
                CombatModClient.LOGGER.info("Config created: " + config.getName());
                CombatModClient.LOGGER.info("Directory: " + configPath.toString()+"combatmod_config.txt");
            } else {
                CombatModClient.LOGGER.warn("Config already exists.");
                CombatModClient.LOGGER.info("Directory: " + configPath.toString()+"combatmod_config.txt");
            }
        } catch (IOException e) {
            CombatModClient.LOGGER.error("An error occurred.");
            e.printStackTrace();
        }
    }

    //Config Structure:
    //---------------------------
    //1 ThemeColor = xxxx ✅
    //2 ThemeMode = xxxx ✅
    //3 inited = xxxx ✅
    //4 ✅
    //5 EnabledButtons { ✅
    //6    xxxx = x ✅
    //7    xxxx = x ✅
    //18 } ✅
    //19 ✅
    //20 ButtonPoses { ✅
    //11   xxxx = x
    //12   xxxx = x
    //13   xxxx = x
    //14 } ✅
    //---------------------------

    public static void fixConfig() {
        if (getThemeColor() == "error") {
            writeLine("ThemeColor = Orange", 1);
            SharedVariables.ThemeColor = "Orange";
        }
        if (getThemeMode() == "error") {
            writeLine("ThemeMode = Light", 2);
            SharedVariables.ThemeMode = "Light";
        }
        if (getInited() == "error") {
            writeLine("inited = False", 3);
        }
        if (decoratorHasError(4, "//-----DO NOT EDIT THIS FILE-----//")) {
            writeLine("//-----DO NOT EDIT THIS FILE-----//", 4);
        }
        if (decoratorHasError(5, "EnabledButtons {")) {
            writeLine("EnabledButtons {", 5);
        }
        if (enabledButtonGetter(6) == "error") {
            writeLine("   ToggleCustomHotbar = False", 6);
        }
        if (enabledButtonGetter(7) == "error") {
            writeLine("   TogglePlayerInspector = False", 7);
        }
        if (enabledButtonGetter(8) == "error") {
            writeLine("   TogglePlayerInfo = False", 8);
        }
        if (enabledButtonGetter(9) == "error") {
            writeLine("   ToggleCoordinateHud = False", 9);
        }
        if (enabledButtonGetter(10) == "error") {
            writeLine("   ToggleDirectionHud = False", 10);
        }
        if (enabledButtonGetter(11) == "error") {
            writeLine("   ToggleAutoSprint = False", 11);
        }
        if (enabledButtonGetter(12) == "error") {
            writeLine("   ToggleFullbright = False", 12);
        }
        if (enabledButtonGetter(13) == "error") {
            writeLine("   ToggleBreakingParticles = False", 13);
        }
        if (enabledButtonGetter(14) == "error") {
            writeLine("   ToggleOwnNametag = False", 14);
        }
        if (enabledButtonGetter(15) == "error") {
            writeLine("   ToggleExplosions = False", 15);
        }
        if (enabledButtonGetter(16) == "error") {
            writeLine("   ToggleKeystrokes = False", 16);
        }
        if (enabledButtonGetter(17) == "error") {
            writeLine("   Anticheat = False", 17);
        }
        if (decoratorHasError(18, "}")) {
            writeLine("}", 18);
        }
        if (decoratorHasError(19, "ButtonPoses {")) {
            writeLine("ButtonPoses {", 19);
        }
    }

    public static boolean decoratorHasError(int line, String string) {
        String line1 = readInConfig(line);
        if (line1 != null && line1.equals(string)) {
            return false;
        } else return true;
    }

    public static String buttonPoseGetter(int line) {
        String line1 = readInConfig(line);
        if (line1 != null) {
            String value = line1.substring(line1.lastIndexOf("=") + 2);
            return value;
        } else return "error";
    }

    public static void printButtonPose(String id, int value, int line) {
        try {
            List<String> fileContent = new ArrayList<>(Files.readAllLines(Path.of(configPath.toString()+"combatmod_config.txt"), StandardCharsets.UTF_8));
            for (int I = 0; I <= line; I++) {
                fileContent.add("//");
            }
            fileContent.set(line-1, "   "+id+" = "+value);
            ArrayList<String> list = new ArrayList<>();
            list.add("//");
            fileContent.removeAll(list);
            Files.write(Path.of(configPath.toString()+"combatmod_config.txt"), fileContent, StandardCharsets.UTF_8);
        } catch (IOException e) {
            CombatModClient.LOGGER.error("An error occurred.");
            e.printStackTrace();
        }
    }

    public static String enabledButtonGetter(int line) {
        String line1 = readInConfig(line);
        if (line1 != null) {
            String value = line1.substring(line1.lastIndexOf("=") + 2);
            if (value.equals("True") || value.equals("False")) {
                return value;
            } else return "error";
        } else return "error";
    }

    public static String getInited() {
        String line1 = readInConfig(3);
        if (line1 != null && line1.startsWith("inited = ")) {
            String value = line1.substring(line1.lastIndexOf("=") + 2);
            if (value.equals("True") || value.equals("False")) {
                return value;
            } else return "error";
        } else return "error";
    }

    public static String getThemeColor() {
        String line1 = readInConfig(1);
        if (line1 != null && line1.startsWith("ThemeColor = ")) {
            String value = line1.substring(line1.lastIndexOf("=") + 2);
            if (value.equals("Orange") || value.equals("Blue") || value.equals("Red") || value.equals("Green") || value.equals("Yellow") || value.equals("Rainbow")) {
                return value;
            } else return "error";
        } else return "error";
    }

    public static String getThemeMode() {
        String line1 = readInConfig(2);
        if (line1 != null && line1.startsWith("ThemeMode = ")) {
            String value = line1.substring(line1.lastIndexOf("=") + 2);
            if (value.equals("Dark") || value.equals("Light")) {
                return value;
            } else return "error";
        } else return "error";
    }

    public static void writeLine(String string, int line) {
        try {
            List<String> fileContent = new ArrayList<>(Files.readAllLines(Path.of(configPath.toString()+"combatmod_config.txt"), StandardCharsets.UTF_8));
            for (int I = 0; I <= line; I++) {
                fileContent.add("//");
            }
            fileContent.set(line-1, string);
            ArrayList<String> list = new ArrayList<>();
            list.add("//");
            fileContent.removeAll(list);
            Files.write(Path.of(configPath.toString()+"combatmod_config.txt"), fileContent, StandardCharsets.UTF_8);
        } catch (IOException e) {
            CombatModClient.LOGGER.error("An error occurred.");
            e.printStackTrace();
        }
    }
    
    public static String readInConfig(int line) {
        try {
            File config = new File(configPath.toString()+"combatmod_config.txt");
            int dataLine = 0;
            Scanner reader = new Scanner(config);
            while (reader.hasNextLine()) {
                String data = reader.nextLine();
                dataLine = dataLine+1;
                if (dataLine == line) {
                    return data;
                }
            }
            reader.close();
        } catch (FileNotFoundException e) {
            CombatModClient.LOGGER.error("An error occurred.");
            e.printStackTrace();
            return null;
        }
        return null;
    }

}
