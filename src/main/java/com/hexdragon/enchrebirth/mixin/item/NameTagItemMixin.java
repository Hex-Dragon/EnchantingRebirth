package com.hexdragon.enchrebirth.mixin.item;

import com.hexdragon.enchrebirth.item.name_tag.NameTagScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.NameTagItem;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(NameTagItem.class)
public abstract class NameTagItemMixin extends Item {
    public NameTagItemMixin(Properties properties) {super(properties);}

    @Override public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        if (worldIn.isRemote) {
            DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> Minecraft.getInstance().displayGuiScreen(new NameTagScreen()));
        }
        return super.onItemRightClick(worldIn, playerIn, handIn);
    }

}
