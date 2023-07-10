package co.bitengine.salvage.models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RecipeQuery {
    private final String name, permission;
    private final List<String> salvageItemTexts;
    private final List<String> lootTableItemTexts;

    private RecipeQuery(String name, String permission, List<String> salvageItemTexts, List<String> lootTableItemTexts) {
        this.name = name;
        this.permission = permission;
        this.salvageItemTexts = salvageItemTexts;
        this.lootTableItemTexts = lootTableItemTexts;
    }

    // name=dsds&permission=ds&input=hello,gr&output=hello,gr,ge
    public static RecipeQuery from(String search) {
        String [] parameters = search.split("&");
        String name = null;
        String permission = null;
        List<String> salvageItemTexts = new ArrayList<>();
        List<String> lootTableItemTexts = new ArrayList<>();
        for (String param : parameters) {
            if (param.contains("name=") && param.length() > param.indexOf("name=")+5) {
                name = param.substring(param.indexOf("name=")+5);
            } else if (param.contains("permission=") && param.length() > param.indexOf("permission=")+11) {
                permission = param.substring(param.indexOf("permission=")+11);
            } else if (param.contains("input=") && param.length() > param.indexOf("input=")+6) {
                salvageItemTexts.addAll(Arrays.asList(param.substring(param.indexOf("input=")+6).split(",")));
            } else if (param.contains("output=") && param.length() > param.indexOf("output=")+7) {
                lootTableItemTexts.addAll(Arrays.asList(param.substring(param.indexOf("output=")+7).split(",")));
            }
        }
        return new RecipeQuery(name, permission, salvageItemTexts, lootTableItemTexts);
    }

    public String getName() {
        return name;
    }

    public String getPermission() {
        return permission;
    }

    public List<String> getSalvageItemTexts() {
        return salvageItemTexts;
    }

    public List<String> getLootTableItemTexts() {
        return lootTableItemTexts;
    }
}
