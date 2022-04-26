package coda.purrfectsmoothies.common.blocks.entities;

import coda.purrfectsmoothies.PurrfectSmoothies;
import coda.purrfectsmoothies.registry.PSBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.RandomizableContainerBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

import java.util.ArrayList;
import java.util.List;

public class BlenderBlockEntity extends RandomizableContainerBlockEntity implements IAnimatable {
    private NonNullList<ItemStack> items = NonNullList.withSize(slots, ItemStack.EMPTY);
    private final AnimationFactory factory = new AnimationFactory(this);
    public static int slots = 5;
    public int blendingTicks;

    public BlenderBlockEntity(BlockPos pos, BlockState state) {
        super(PSBlockEntities.BLENDER.get(), pos, state);
    }

    @Override
    protected Component getDefaultName() {
        return new TranslatableComponent("container." + PurrfectSmoothies.MOD_ID + ".blender");
    }

    @Override
    protected AbstractContainerMenu createMenu(int p_58627_, Inventory p_58628_) {
        return null;
    }

    @Override
    protected void saveAdditional(CompoundTag tag) {
        super.saveAdditional(tag);

        if (!this.trySaveLootTable(tag)) {
            ContainerHelper.saveAllItems(tag, this.items);
        }
    }

    @Override
    public void load(CompoundTag tag) {
        super.load(tag);

        this.items = NonNullList.withSize(getContainerSize(), ItemStack.EMPTY);
        if (!this.tryLoadLootTable(tag)) {
            ContainerHelper.loadAllItems(tag, this.items);
        }
    }

    public int countItems(List<ItemStack> stacks) {
        List<ItemStack> currentStacks = new ArrayList<>();

        for (ItemStack stack : stacks) {
            if (!stack.isEmpty()) {
                currentStacks.add(stack);
            }
        }

        return currentStacks.size();
    }

    @Override
    public NonNullList<ItemStack> getItems() {
        return this.items;
    }

    @Override
    protected void setItems(NonNullList<ItemStack> items) {
        this.items = items;
    }

    @Override
    public int getContainerSize() {
        return slots;
    }

    @Override
    public boolean isEmpty() {
        for (int i = 0; i < getContainerSize(); i++) {
            if (!getItem(i).isEmpty()) return false;
        }
        return true;
    }

    // todo - add isBlending
    public boolean isBlending() {
        return false;
    }

    public static void tick(Level level, BlockPos position, BlockState state, BlenderBlockEntity blender) {
    /*      if (blender.currentRecipe != null) {
            if (++blender.blendingTicks >= 100) {
                //Clear the inventory and remove the bottle
                for (int i = 0; i < 9; i++) {
                    blender.removeItem(i);
                }
                blender.getItem(9).shrink(1);
                //Set the output
                blender.setItem(10, blender.currentRecipe.assemble(blender));
                blender.currentRecipe = null;
                blender.blendingTicks = 0;
            }
        }*/
    }

    @Override
    public void registerControllers(AnimationData data) {

    }

    @Override
    public AnimationFactory getFactory() {
        return factory;
    }
}
