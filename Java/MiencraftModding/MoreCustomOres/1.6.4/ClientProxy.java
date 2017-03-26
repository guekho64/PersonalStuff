package guekho64.MoreCustomOresExtendedWorkbenchAddOn.files.common;

import static naruto1310.extendedWorkbench.EWConfig.biggerTools;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import guekho64.MoreCustomOresExtendedWorkbenchAddOn.files.common.MoreCustomOresExtendedWorkbenchAddOn.Extra.Utils.Environment;
import naruto1310.extendedWorkbench.item.RenderExtendedTool;
import net.minecraft.item.Item;
import net.minecraftforge.client.MinecraftForgeClient;

@SideOnly(Side.CLIENT)
public class ClientProxy extends CommonProxy {
		@Override
		public void preInit() {
			super.preInit();
		}
		@Override
		public void registerRenderInformation() {
			RenderExtendedTool render = new RenderExtendedTool();
			if (biggerTools == 2) {
					for (int i = 0; i < Environment.Lists.Item.toolList.size(); i++) {
						MinecraftForgeClient.registerItemRenderer(((Item)Environment.Lists.Item.toolList.get(i).get(0)).itemID , render);
					}
			}
			else if (biggerTools == 1) {
				for (int i = 0; i < Environment.Lists.Item.toolList.size(); i++) {
					if (((Item)Environment.Lists.Item.toolList.get(i).get(0)).getUnlocalizedName().toLowerCase().contains(Environment.Misc.wordSword)) {
						MinecraftForgeClient.registerItemRenderer(((Item)Environment.Lists.Item.toolList.get(i).get(0)).itemID , render);
					}
				}
			}
		}
	}
