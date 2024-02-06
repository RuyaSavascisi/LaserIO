package com.direwolf20.laserio.datagen.customrecipes;

import com.direwolf20.laserio.common.items.cards.BaseCard;
import com.direwolf20.laserio.setup.Registration;
import net.minecraft.core.NonNullList;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CraftingBookCategory;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.ShapelessRecipe;

import java.util.List;

public class CardClearRecipe extends ShapelessRecipe {
    public CardClearRecipe(String pGroup, CraftingBookCategory pCategory, ItemStack pResult, NonNullList<Ingredient> pIngredients) {
        super(pGroup, pCategory, pResult, pIngredients);
    }

    @Override
    public boolean isSpecial() {
        return true;
    }

    @Override
    public NonNullList<ItemStack> getRemainingItems(CraftingContainer inv) {
        NonNullList<ItemStack> nonnulllist = NonNullList.withSize(inv.getContainerSize(), ItemStack.EMPTY); //2 spots - one for filters, one for Overclockers

        int position = 0;

        for (int i = 0; i < inv.getContainerSize(); i++) {
            ItemStack itemStack = inv.getItem(i);
            Item item = itemStack.getItem();
            if (item instanceof BaseCard) {
                List<ItemStack> containedItems = ((BaseCard) item).getContainerItems(itemStack);
                nonnulllist.set(position, containedItems.get(0));
                nonnulllist.set(position + 1, containedItems.get(1));
                position = position + 2;
            } else {
                if (itemStack.hasCraftingRemainingItem()) {
                    nonnulllist.set(position, itemStack.getCraftingRemainingItem());
                    position++;
                }
            }
        }

        return nonnulllist;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return Registration.CARD_CLEAR_RECIPE_SERIALIZER.get();
    }


    public static class Serializer extends ShapelessRecipe.Serializer {

    }
}
