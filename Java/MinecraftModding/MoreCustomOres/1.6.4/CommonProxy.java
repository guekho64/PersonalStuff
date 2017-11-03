package guekho64.MoreCustomOresExtendedWorkbenchAddOn.files.common;

import cpw.mods.fml.common.network.IGuiHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class CommonProxy implements IGuiHandler {
		public void preInit() {}
		public void registerRenderInformation() {}
		@Override
		public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
			return null;
		}
		@Override
		public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
			return null;
		}
	}
