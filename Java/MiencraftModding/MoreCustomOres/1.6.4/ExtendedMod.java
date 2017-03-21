/* Copyright guekho64 © All Rights Reserved 2017 */

package guekho64.MoreCustomOresExtendedWorkbenchAddOn.files.common;

import static naruto1310.extendedWorkbench.mod_ExtendedWorkbench.extendedValues.increaseArmorDurability;
import static guekho64.MoreCustomOresExtendedWorkbenchAddOn.files.common.MoreCustomOresExtendedWorkbenchAddOn.Main.Content.Mod.Config.Declaration.confiGuekho;
import guekho64.MoreCustomOresExtendedWorkbenchAddOn.files.common.MoreCustomOresExtendedWorkbenchAddOn.Extra.Utils.CustomTypes;
import guekho64.MoreCustomOresExtendedWorkbenchAddOn.files.common.MoreCustomOresExtendedWorkbenchAddOn.Extra.Utils.Environment;
import guekho64.MoreCustomOresExtendedWorkbenchAddOn.files.common.MoreCustomOresExtendedWorkbenchAddOn.Extra.Utils.Methods;
import guekho64.MoreCustomOresExtendedWorkbenchAddOn.files.common.MoreCustomOresExtendedWorkbenchAddOn.Main.Content;
import guekho64.mod.blocks.MineralDeAluminio;
import guekho64.mod.common.MoreCustomOres;

import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumArmorMaterial;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.ISpecialArmor;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.IGuiHandler;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.registry.GameData;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.ItemData;
import cpw.mods.fml.relauncher.IFMLLoadingPlugin.MCVersion;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

//TODO: Checar las ID' disponibles realmente desde 1 a 255 (O) Hallar un modo de evitar algún crash relacionado con este rango de ID's

@Mod(modid=Environment.General.mod, name=Environment.General.mod, version=Environment.General.version, dependencies=Environment.General.dependencies)
@NetworkMod(clientSideRequired=true, serverSideRequired=false)
@MCVersion(value=Environment.General.gameVersion)
public class MoreCustomOresExtendedWorkbenchAddOn {
	public static class Extra {
		public static class Utils {
			public static class Methods {
				public static class FundamentalMethods {
					public static void LoadConfig (Configuration config) {
						config.load();
					}
					public static int[] DoConfigTasks (Configuration config, List blockList, int blockInicialID, List itemList, int itemInicialID) {
						int realBlockInicialTerrainID = config.get(ToUppercaseFirstLetter(Environment.Misc.wordTerrain) + " " + ToUppercaseFirstLetter(Environment.Misc.wordBlocks), ToUppercaseFirstLetter(Environment.Misc.wordInicial) + " " + ToUppercaseFirstLetter(Environment.Misc.wordID), FastID((List)blockList.get(0), 0)).getInt();
						int realBlockInicialNormalID = config.get(ToUppercaseFirstLetter(Environment.Misc.wordNormal) + " " + ToUppercaseFirstLetter(Environment.Misc.wordBlocks), ToUppercaseFirstLetter(Environment.Misc.wordInicial) + " " + ToUppercaseFirstLetter(Environment.Misc.wordID), FastID((List)blockList.get(1), blockInicialID)).getInt();
						int realItemInicialID= FixItemID(config.get(ToUppercaseFirstLetter(Environment.Misc.wordItems), ToUppercaseFirstLetter(Environment.Misc.wordInicial) + " " + ToUppercaseFirstLetter(Environment.Misc.wordID), FastID(itemList, itemInicialID)).getInt());
						int[] result = new int[] {realBlockInicialTerrainID, realBlockInicialNormalID, realItemInicialID};
						return result;
					}
					public static void SaveConfig (Configuration config) {
						config.save();
					}
					public static String ToUppercaseFirstLetter (String string) {
						final String output = string.substring(0, 1).toUpperCase() + string.substring(1);
						return output;
					}
					public static int UnFixItemID (int value) {
						return (value+256);
					}
					public static int FixItemID (int value) {
						return (value-256);
					}
					public static ReflectedStuff.GetIDMap GetIDMap () {
						return new ReflectedStuff.GetIDMap();
					}
					public static int FastID (List<Integer> actualList, int preferredValue) {
						try {
							return GetEmptyID(actualList, preferredValue);
						}
						catch (Exception problem) {
							problem.printStackTrace();
							return GetEmptyID(actualList, preferredValue);
						}
						finally {
							Runtime.getRuntime().gc();
						}
					}
					public static int GetEmptyID (List<Integer> actualList, int preferredValue) {
						Integer value;
						if (preferredValue == 0) {
							value =  actualList.get(0);
							if (!(value < Item.itemsList.length)) {
								value = Item.itemsList.length-1;
							}
						}
						else if (!(actualList.contains(preferredValue))) {
							value =  actualList.get(0);
							if (!(value < Item.itemsList.length)) {
								value = Item.itemsList.length-1;
							}
						}
						else {
							value = preferredValue;
							if (!(value < Item.itemsList.length)) {
								value = Item.itemsList.length-1;
							}
						}
						actualList.remove((Object)value);
						return value;
					}
					public static List BlockEmptyIDs () {
						final List<List> listOfLists = new ArrayList<List>();
						final List<Integer> listTerrain = new ArrayList<Integer>();
						final List<Integer> listNormal = new ArrayList<Integer>();
						for (int i = 1; i < 256; ++i) {
							if (!(GetIDMap().idMap.containsKey(i))) {
								if (i != 256 || !(i >= 256) || i != 0 || !(i <= 0)) {
									listTerrain.add(i);
								}
							}
						}
						for (int i = 409; i <= Block.blocksList.length; ++i) {
							if (!(GetIDMap().idMap.containsKey(i))) {
								if (i != 4096 || !(i >= 4096) || i != 0 || !(i <= 0)) {
									listNormal.add(i);
								}
							}
						}
						listOfLists.add(listTerrain);
						listOfLists.add(listNormal);
						return listOfLists;
					}
					public static List ItemEmptyIDs () {
						final List<Integer> list = new ArrayList<Integer>();
						for (int i = Block.blocksList.length; i <= Item.itemsList.length; ++i) {
							if (!(GetIDMap().idMap.containsKey(i))) {
								if (i != 32000 || !(i >= 32000) || i != 4095 || !(i <= 4095)) {
									list.add(i);
								}
							}
						}
						return list;
					}
					@SafeVarargs
					public static <E> Object[] Unpack(E... objects) {
						final List<Object> list = new ArrayList<Object>();
						for (Object object : objects) {
							if (object instanceof Object[]) {
								list.addAll(Arrays.asList((Object[]) object));
							}
							else{
								list.add(object);
							}
						}
						return list.toArray(new Object[list.size()]);
					}
				}
				public static class	SimplifiedMethods {
					public static CustomTypes.Items.Armor.Extended ExtendedArmor (int armorType, String materialName, EnumArmorMaterial material, CreativeTabs creativeTab) {
						return new CustomTypes.Items.Armor.Extended(SimplifiedMethods.GetItemID(Content.Mod.Names.ArmorParts.extendedArmorParts[armorType] + FundamentalMethods.ToUppercaseFirstLetter(materialName)), material, Content.Mod.Declaration.Renderers.armorRenderer[armorType], armorType, creativeTab, Content.Mod.Names.ArmorParts.extendedArmorParts[armorType] + FundamentalMethods.ToUppercaseFirstLetter(materialName));
					}
					public static int GetItemID (String itemName) {
						Environment.IDs.tmp++;
						return FundamentalMethods.FixItemID(confiGuekho.get(FundamentalMethods.ToUppercaseFirstLetter(Environment.Misc.wordItems), itemName, FundamentalMethods.UnFixItemID(Environment.IDs.Item.real+(Environment.IDs.tmp-1))).getInt());
					}
				}
			}
			public static class ReflectedStuff {
				public static class GetIDMap {
					public static Field map;
					public static Map<Integer, ItemData> idMap; {
						try {
							map = GameData.class.getDeclaredField("idMap");
							map.setAccessible(true);
							idMap = (Map<Integer, ItemData>) map.get(new GameData());
						}
						catch (Exception problem) {
							problem.printStackTrace();
						}
					}
				}
			}
			public static class CustomTypes {
				public static class Proxies {
					public static class Common implements IGuiHandler{
						@Override
						public Object getServerGuiElement(int ID,EntityPlayer player,World world,int x,int y,int z) {
							return null;
						}
						@Override
						public Object getClientGuiElement(int ID,EntityPlayer player,World world,int x,int y,int z) {
							return null;
						}
					}
				}
				public static class Items {
					public static class Armor {
						public static class Extended  extends ItemArmor implements ISpecialArmor {
							private String texturePath;
							/** Holds the 'base' maxDamage that each armorType have. **/
						    private static final int[] EWmaxDamageArray = new int[] {11, 16, 15, 13};

						    /** Holds the amount of damage that the armor reduces at full durability. **/
						    public final int EWdamageReduceAmount;

						    /** The EnumArmorMaterial used for this ItemArmor **/
						    private final EnumArmorMaterial material;

						    public Extended(int par1, EnumArmorMaterial par2EnumArmorMaterial, int par3, int par4, CreativeTabs tab, String uName) {
						        super(par1, par2EnumArmorMaterial, par3, par4);
						        super.setCreativeTab(tab);
						        super.setUnlocalizedName(uName);
						        this.material = par2EnumArmorMaterial;
						        this.EWdamageReduceAmount = par2EnumArmorMaterial.getDamageReductionAmount(par4);
						        this.setMaxDamage(par2EnumArmorMaterial.getDurability(par4));
						        this.setMaxStackSize(1);
						        this.setMaxDamage((int)(getMaxDamage() * increaseArmorDurability));
						    }

						    /**
						     * Return the enchantability factor of the item, most of the time is based on material.
						     */
						    @Override
							public int getItemEnchantability() {
						        return this.material.getEnchantability();
						    }

						    /**
						     * Returns the 'max damage' factor array for the armor, each piece of armor have a durability factor (that gets
						     * multiplied by armor material factor)
						     */
						    static int[] getMaxDamageArray() {
						        return EWmaxDamageArray;
						    }

						    @Override
						    public ArmorProperties getProperties(EntityLivingBase player, ItemStack armor, DamageSource source, double damage, int slot) {
						        return new ArmorProperties(0, (this.EWdamageReduceAmount + 0.5D) / 25D , armor.getMaxDamage() + 1 - armor.getItemDamage());
						    }

						    @Override
						    public int getArmorDisplay(EntityPlayer player, ItemStack armor, int slot) {
						        return this.EWdamageReduceAmount;
						    }

						    @Override
						    public void damageArmor(EntityLivingBase entity, ItemStack stack, DamageSource source, int damage, int slot) {
						        stack.damageItem(damage, entity);
						    }

						    @Override
							@SideOnly(Side.CLIENT)
						    public boolean requiresMultipleRenderPasses() {
						    	return false;
						    }

						    @Override
						    public int getColor(ItemStack stack) {
						        if (this.material != EnumArmorMaterial.CLOTH)
						            return -1;

						        NBTTagCompound var2 = stack.getTagCompound();

						        if (var2 == null)
						    		return 0x632E1B;

						        NBTTagCompound var3 = var2.getCompoundTag("display");
						        return var3 == null ? 0x632E1B : (var3.hasKey("color") ? var3.getInteger("color") : 0x632E1B);
						    }
						}
					}
				}
				public static class MainTab extends CreativeTabs {
					public MainTab(String a) {
						super(a);
					}
					@SideOnly(Side.CLIENT)
					public int getTabIconItemIndex() {
						return Content.Mod.Declaration.Armor.extendedHelmetOrichalcum.itemID;
					}
				}
			}
			public static class Environment {
				public static class General {
					public static final String mod = "MoreCustomOresExtendedWorkbenchAddOn";
					public static final String version = "0.64";
					public static final String gameVersion = "1.6.4";
					public static final String generalPath = "guekho64.MoreCustomOresExtendedWorkbenchAddOn.files";
					public static final String dependencies = "required-after:extendedWorkbench; required-after:MoreCustomOres";
				}
				public static class IDs {
					public static int tmp = 0;
					public static class Block {
						public static final int inicial = 640;
						public static class Terrain {
							public static int real;
						}
						public static class Normal {
							public static int real;
						}
					}
					public static class Item {
						public static final int inicial = 6400;
						public static int real;
					}
				}
				public static class Config {
					public static final String folderName = "config";
					public static final String fileExtension = "cfg";
					public static final String fileName = General.mod + Misc.symbolDot + fileExtension;
					public static final String path = folderName + fileName;
				}
				public static class Proxies {
					public static class Common {
						public static final String localPath = "Main.Content.CommonProxy";
						public static final String qualifiedName = General.generalPath + Misc.wordCommon  + General.mod  + localPath;
					}
				}
				public static class Misc {
					public static final String symbolDot = ".";
					public static final String symbolExclamationSignEnd = "!";
					public static final String wordBlocks = "blocks";
					public static final String wordCommon = "common";
					public static final String wordExtended = "extended";
					public static final String wordID = "id";
					public static final String wordInicial = "inicial";
					public static final String wordItems = "items";
					public static final String wordNormal = "normal";
					public static final String wordTab = "tab";
					public static final String wordTerrain = "terrain";
					public static final String wordWarning = "warning";
				}
			}
		}
	}
	public static class Main {
		public static class Content {
			public static class Instances {
				@Instance(Environment.General.mod)
				public MoreCustomOresExtendedWorkbenchAddOn MoreCustomOresExtendedWorkbenchAddOnInstance = new MoreCustomOresExtendedWorkbenchAddOn();
			}
			public static class Proxy {
				public static class CommonProxy {
					@SidedProxy(serverSide=Environment.Proxies.Common.qualifiedName)
					public static CustomTypes.Proxies.Common common;
				}
			}
			public static class Mod {
				public static class Config {
					public static class Declaration {
						public static final File file = new File(Environment.Config.folderName, Environment.Config.fileName);
						public static final Configuration confiGuekho = new Configuration(file);
					}
				}
				public static class Names {
					public static class ArmorParts {
						public static final String[] armorParts = {"helmet", "chestplate", "pants", "boots"};
						public static final String[] extendedArmorParts = {Environment.Misc.wordExtended + Methods.FundamentalMethods.ToUppercaseFirstLetter(armorParts[0]), Environment.Misc.wordExtended + Methods.FundamentalMethods.ToUppercaseFirstLetter(armorParts[1]), Environment.Misc.wordExtended + Methods.FundamentalMethods.ToUppercaseFirstLetter(armorParts[2]), Environment.Misc.wordExtended + Methods.FundamentalMethods.ToUppercaseFirstLetter(armorParts[3])};
					}
					public static class Materials {
						public static final String enderWeapon = "enderWeapon";
						public static final String enderTool = "enderTool";
						public static final String orichalcum = "orichalcum";
						public static final String titanium = "titanium";
						public static final String platinum = "platinum";
						public static final String quartz = "quartz";
						public static final String stone = "stone";
					}
					public static class CreativeTabs {
						public static final String extended = Environment.Misc.wordExtended + Methods.FundamentalMethods.ToUppercaseFirstLetter(Environment.Misc.wordTab);
					}
				}
				public static class Declaration {
					public static class Renderers {
						public static int[] armorRenderer;
					}
					public static class ToolMaterials {
						public static final EnumToolMaterial orichalcum = MoreCustomOres.toolGemaDeOricalco;
						public static final EnumToolMaterial titanium = MoreCustomOres.toolLingoteDeTitanio;
						public static final EnumToolMaterial platinum = MoreCustomOres.toolLingoteDePlatino;
						public static final EnumToolMaterial quartz = MoreCustomOres.toolQuartz;
						public static final EnumToolMaterial ender = MoreCustomOres.toolEnder;
						public static final EnumToolMaterial enderSword = MoreCustomOres.toolEnderSword;
					}
					public static class ArmorMaterials {
						public static final EnumArmorMaterial orichalcum = MoreCustomOres.armorGemaDeOricalco;
						public static final EnumArmorMaterial titanium = MoreCustomOres.armorLingoteDeTitanio;
						public static final EnumArmorMaterial platinum = MoreCustomOres.armorLingoteDePlatino;
						public static final EnumArmorMaterial quartz = MoreCustomOres.armorCuarzo;
						public static final EnumArmorMaterial stone = MoreCustomOres.armorPiedra;
					}
					public static class Tools {}
					public static class Armor {
						public static final CustomTypes.Items.Armor.Extended extendedHelmetOrichalcum = Methods.SimplifiedMethods.ExtendedArmor(0, Names.Materials.orichalcum, ArmorMaterials.orichalcum, CreativeTabs.mainTab);
						public static final CustomTypes.Items.Armor.Extended extendedChestplateOrichalcum = Methods.SimplifiedMethods.ExtendedArmor(1, Names.Materials.orichalcum, ArmorMaterials.orichalcum, CreativeTabs.mainTab);
						public static final CustomTypes.Items.Armor.Extended extendedPantsOrichalcum = Methods.SimplifiedMethods.ExtendedArmor(2, Names.Materials.orichalcum, ArmorMaterials.orichalcum, CreativeTabs.mainTab);
						public static final CustomTypes.Items.Armor.Extended extendedBootsOrichalcum = Methods.SimplifiedMethods.ExtendedArmor(3, Names.Materials.orichalcum, ArmorMaterials.orichalcum, CreativeTabs.mainTab);
					}
					public static class CreativeTabs {
						public static final CustomTypes.MainTab mainTab = new CustomTypes.MainTab(Names.CreativeTabs.extended);
					}
				}
			}
		}
	}
	@EventHandler
	public static void PreInit(FMLPreInitializationEvent event) {
		if(FMLCommonHandler.instance().getEffectiveSide().isClient()) {
			Content.Mod.Declaration.Renderers.armorRenderer = new int[] {RenderingRegistry.addNewArmourRendererPrefix(Environment.Misc.wordExtended + Content.Mod.Names.Materials.orichalcum), RenderingRegistry.addNewArmourRendererPrefix(Environment.Misc.wordExtended + Content.Mod.Names.Materials.titanium), RenderingRegistry.addNewArmourRendererPrefix(Environment.Misc.wordExtended + Content.Mod.Names.Materials.platinum), RenderingRegistry.addNewArmourRendererPrefix(Environment.Misc.wordExtended + Content.Mod.Names.Materials.quartz), RenderingRegistry.addNewArmourRendererPrefix(Environment.Misc.wordExtended + Content.Mod.Names.Materials.stone)};
		}
	}
	@EventHandler
	public static void Init(FMLInitializationEvent event) {
		Methods.FundamentalMethods.LoadConfig(confiGuekho);
		final int[] ids = Methods.FundamentalMethods.DoConfigTasks(confiGuekho, (List) Methods.FundamentalMethods.BlockEmptyIDs(), Environment.IDs.Block.inicial, Methods.FundamentalMethods.ItemEmptyIDs(), Environment.IDs.Item.inicial);
		Environment.IDs.Block.Terrain.real = ids[0];
		Environment.IDs.Block.Normal.real = ids[1];
		Environment.IDs.Item.real = ids[2];
		final Content.Instances instances = new Main.Content.Instances();
		final Content.Proxy.CommonProxy commonProxy = new Content.Proxy.CommonProxy();
		final Content.Mod.Config.Declaration configDeclaration = new Content.Mod.Config.Declaration();
		final Content.Mod.Declaration.Renderers renderers = new Content.Mod.Declaration.Renderers();
		final Content.Mod.Declaration.ArmorMaterials armorMaterials = new Content.Mod.Declaration.ArmorMaterials();
		final Content.Mod.Declaration.ToolMaterials toolMaterials = new Content.Mod.Declaration.ToolMaterials();
		final Content.Mod.Declaration.Armor armor = new Content.Mod.Declaration.Armor();
		final Content.Mod.Declaration.Tools tools = new Content.Mod.Declaration.Tools();
		final Content.Mod.Declaration.CreativeTabs creativeTabs = new Content.Mod.Declaration.CreativeTabs();
		Methods.FundamentalMethods.SaveConfig(confiGuekho);
	}
	@EventHandler
	public static void PostInit(FMLPostInitializationEvent event) {
	}
}
