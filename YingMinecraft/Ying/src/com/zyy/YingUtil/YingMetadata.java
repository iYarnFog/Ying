package com.zyy.YingUtil;

import java.util.List;

import com.zyy.Ying;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.metadata.Metadatable;

public class YingMetadata
{
    /*
            Block torch = location.getBlock();
          YingMetadata.setMetaValue(torch, "btorch", Double.valueOf(fuel));
          this.torches.add(torch);
    */
    public static <T> T setMetaValue(String key, Metadatable meta, T value)
    {
        meta.setMetadata(key, new FixedMetadataValue(Ying.getYing(), value));
        return value;
    }

    //Block torch = (Block)iterator.next();
    //torch = torch.getLocation().getBlock();
    //Double meta = (Double)YingMetadata.getMetaValue(Double.class, "btorch",  torch);
    public static <T>T getMetaValue(Class<T> YClass, String key, Metadatable metadatable)
    {
        List<MetadataValue> meta = metadatable.getMetadata(key);
        for (MetadataValue m : meta) {
            if ((YClass.isInstance(m.value())) || (m.value().getClass().isAssignableFrom(YClass))) {
                return (T)m.value();
            }
        }
        return null;
    }

}
