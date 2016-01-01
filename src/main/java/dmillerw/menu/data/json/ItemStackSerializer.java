package dmillerw.menu.data.json;

import com.google.gson.*;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameData;

import java.lang.reflect.Type;
import java.util.Map;

/**
 * @author dmillerw
 */
public class ItemStackSerializer implements JsonSerializer<ItemStack>, JsonDeserializer<ItemStack> {

    @Override
    public JsonElement serialize(ItemStack src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject object = new JsonObject();

        object.add("name", new JsonPrimitive(String.valueOf(GameData.getItemRegistry().getNameForObject(src.getItem()))));
        object.add("damage", new JsonPrimitive(src.getItemDamage()));

        return object;
    }

    @Override
    public ItemStack deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        if (!json.isJsonObject()) {
            return null;
        }

        String name = "";
        int damage = 0;

        for (Map.Entry<String, JsonElement> entry : json.getAsJsonObject().entrySet()) {
            String key = entry.getKey();
            JsonElement element = entry.getValue();

            if (key.equals("name")) {
                name = element.getAsString();
            } else if (key.equals("damage")) {
                damage = element.getAsInt();
            }
        }

        return name.isEmpty() ? null : new ItemStack(GameData.getItemRegistry().getObject(new ResourceLocation(name)), 1, damage); //TODO Test if resourcelocation works
    }
}
