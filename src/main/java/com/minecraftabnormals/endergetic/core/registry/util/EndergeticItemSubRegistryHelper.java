package com.minecraftabnormals.endergetic.core.registry.util;

import com.minecraftabnormals.abnormals_core.core.util.registry.ItemSubRegistryHelper;
import com.minecraftabnormals.abnormals_core.core.util.registry.RegistryHelper;
import com.minecraftabnormals.endergetic.common.items.EetleSpawnEggItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.fml.RegistryObject;

public final class EndergeticItemSubRegistryHelper extends ItemSubRegistryHelper {

	public EndergeticItemSubRegistryHelper(RegistryHelper parent) {
		super(parent, parent.getItemSubHelper().getDeferredRegister());
	}

	public RegistryObject<EetleSpawnEggItem> createEetleSpawnEgg() {
		EetleSpawnEggItem spawnEggItem = new EetleSpawnEggItem(4078146, 8356781, new Item.Properties().group(ItemGroup.MISC));
		this.spawnEggs.add(spawnEggItem);
		return this.deferredRegister.register("eetle_spawn_egg", () -> spawnEggItem);
	}

}