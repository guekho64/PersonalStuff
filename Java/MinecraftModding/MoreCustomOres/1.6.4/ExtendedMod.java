package guekho64.MoreCustomOresExtendedWorkbenchAddOn.files.common;

import static guekho64.MoreCustomOresExtendedWorkbenchAddOn.files.common.MoreCustomOresExtendedWorkbenchAddOn.Main.Content.Mod.Config.Declaration.confiGuekho;
import static naruto1310.extendedWorkbench.mod_ExtendedWorkbench.extendedValues.increaseArmorDurability;
import static naruto1310.extendedWorkbench.mod_ExtendedWorkbench.extendedValues.increaseHoeDurability;
import static naruto1310.extendedWorkbench.mod_ExtendedWorkbench.extendedValues.increaseSwordDamage;
import static naruto1310.extendedWorkbench.mod_ExtendedWorkbench.extendedValues.increaseSwordDurability;
import static naruto1310.extendedWorkbench.mod_ExtendedWorkbench.extendedValues.increaseSwordMiningSpeed;
import static naruto1310.extendedWorkbench.mod_ExtendedWorkbench.extendedValues.increaseToolAttackDamage;
import static naruto1310.extendedWorkbench.mod_ExtendedWorkbench.extendedValues.increaseToolDurability;
import static naruto1310.extendedWorkbench.mod_ExtendedWorkbench.extendedValues.increaseToolPower;

import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;

import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.registry.GameData;
import cpw.mods.fml.common.registry.ItemData;
import cpw.mods.fml.relauncher.IFMLLoadingPlugin.MCVersion;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import guekho64.MoreCustomOresExtendedWorkbenchAddOn.files.common.MoreCustomOresExtendedWorkbenchAddOn.Extra.Utils.CustomTypes;
import guekho64.MoreCustomOresExtendedWorkbenchAddOn.files.common.MoreCustomOresExtendedWorkbenchAddOn.Extra.Utils.Environment;
import guekho64.MoreCustomOresExtendedWorkbenchAddOn.files.common.MoreCustomOresExtendedWorkbenchAddOn.Extra.Utils.Methods;
import guekho64.MoreCustomOresExtendedWorkbenchAddOn.files.common.MoreCustomOresExtendedWorkbenchAddOn.Main.Content;
import guekho64.mod.common.MoreCustomOres;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumArmorMaterial;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemSpade;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.EnumHelper;
import net.minecraftforge.common.ISpecialArmor;

//TODO: Checar las ID' disponibles realmente desde 1 a 255 (O) Hallar un modo de evitar algún crash relacionado con este rango de ID's

@Mod(modid=Environment.General.mod, name=Environment.General.mod, version=Environment.General.version, dependencies=Environment.General.dependencies)
@NetworkMod(clientSideRequired=true, serverSideRequired=false)
@MCVersion(value=Environment.General.gameVersion)
public class MoreCustomOresExtendedWorkbenchAddOn {
	@SidedProxy(serverSide="guekho64.MoreCustomOresExtendedWorkbenchAddOn.files.common.Common", clientSide="guekho64.MoreCustomOresExtendedWorkbenchAddOn.files.common.Client")
	public static CommonProxy proxy;
	@Instance(Environment.General.mod)
	public static MoreCustomOresExtendedWorkbenchAddOn MoreCustomOresExtendedWorkbenchAddOnInstance = new MoreCustomOresExtendedWorkbenchAddOn();
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
					public static List RegisterToListOfToolItems (List justAList) {
						Environment.Lists.Item.toolList.add(justAList);
						return justAList;
					}
					public static List RegisterToListOfArmorItems (List justAList) {
						Environment.Lists.Item.armorList.add(justAList);
						return justAList;
					}
					public static List ExtendedTool (int toolType, String materialName, EnumToolMaterial material, ItemStack repairMaterial, CreativeTabs creativeTab) {
						final List list = new ArrayList();
						list.clear();
						if (toolType < 0) {
							;
						}
						else if (toolType == 0) {
							final Item item = new CustomTypes.Item.Tool.ExtendedPickaxe(SimplifiedMethods.GetItemID(Content.Mod.Names.ToolTypes.extendedtoolTypes[toolType] + FundamentalMethods.ToUppercaseFirstLetter(materialName)), material, repairMaterial, creativeTab, Content.Mod.Names.ToolTypes.extendedtoolTypes[toolType] + FundamentalMethods.ToUppercaseFirstLetter(materialName));
							list.add(item);
						}
						else if (toolType == 1) {
							final Item item = new CustomTypes.Item.Tool.ExtendedAxe(SimplifiedMethods.GetItemID(Content.Mod.Names.ToolTypes.extendedtoolTypes[toolType] + FundamentalMethods.ToUppercaseFirstLetter(materialName)), material, repairMaterial, creativeTab, Content.Mod.Names.ToolTypes.extendedtoolTypes[toolType] + FundamentalMethods.ToUppercaseFirstLetter(materialName));
							list.add(item);
						}
						else if (toolType == 2){
							final Item item = new CustomTypes.Item.Tool.ExtendedHoe(SimplifiedMethods.GetItemID(Content.Mod.Names.ToolTypes.extendedtoolTypes[toolType] + FundamentalMethods.ToUppercaseFirstLetter(materialName)), material, repairMaterial, creativeTab, Content.Mod.Names.ToolTypes.extendedtoolTypes[toolType] + FundamentalMethods.ToUppercaseFirstLetter(materialName));
							list.add(item);
						}
						else if (toolType == 3) {
							final Item item = new CustomTypes.Item.Tool.ExtendedSword(SimplifiedMethods.GetItemID(Content.Mod.Names.ToolTypes.extendedtoolTypes[toolType] + FundamentalMethods.ToUppercaseFirstLetter(materialName)), material, repairMaterial, creativeTab, Content.Mod.Names.ToolTypes.extendedtoolTypes[toolType] + FundamentalMethods.ToUppercaseFirstLetter(materialName));
							list.add(item);
						}
						else if (toolType == 4) {
							final Item item = new CustomTypes.Item.Tool.ExtendedSpade(SimplifiedMethods.GetItemID(Content.Mod.Names.ToolTypes.extendedtoolTypes[toolType] + FundamentalMethods.ToUppercaseFirstLetter(materialName)), material, repairMaterial, creativeTab, Content.Mod.Names.ToolTypes.extendedtoolTypes[toolType] + FundamentalMethods.ToUppercaseFirstLetter(materialName));
							list.add(item);
						}
						else if (toolType > 4) {
							;
						}
						if (list.size() == 1) {
							return list;
						}
						else {
							return new ArrayList();
						}
					}
					public static List ExtendedArmor (int armorType, String materialName, EnumArmorMaterial material, ItemStack repairMaterial, CreativeTabs creativeTab) {
						final List list = new ArrayList();
						list.add(new CustomTypes.Item.Armor.Extended(SimplifiedMethods.GetItemID(Content.Mod.Names.ArmorParts.extendedArmorParts[armorType] + FundamentalMethods.ToUppercaseFirstLetter(materialName)), material, Content.Mod.Declaration.Renderers.armorRenderer[armorType], armorType, repairMaterial, creativeTab, Content.Mod.Names.ArmorParts.extendedArmorParts[armorType] + FundamentalMethods.ToUppercaseFirstLetter(materialName)));
						return list;
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
				public static class Item {
					public static class Armor {
						public static class Extended  extends ItemArmor implements ISpecialArmor {
							private String texturePath;

							public ItemStack itemStack;

							/** Holds the 'base' maxDamage that each armorType have. **/
						    private static final int[] EWmaxDamageArray = new int[] {11, 16, 15, 13};

						    /** Holds the amount of damage that the armor reduces at full durability. **/
						    public final int EWdamageReduceAmount;

						    /** The EnumArmorMaterial used for this ItemArmor **/
						    public EnumArmorMaterial material;

						    public Extended(int par1, EnumArmorMaterial par2EnumArmorMaterial, int par3, int par4, ItemStack itemStack, CreativeTabs tab, String uName) {
						        super(par1, par2EnumArmorMaterial, par3, par4);
						        super.setCreativeTab(tab);
						        super.setUnlocalizedName(uName + Environment.Compatibility.stringExtendedWorkbench);
						        this.itemStack = itemStack;
						        this.material = par2EnumArmorMaterial;
						        this.EWdamageReduceAmount = par2EnumArmorMaterial.getDamageReductionAmount(par4);
						        this.setMaxDamage(par2EnumArmorMaterial.getDurability(par4));
						        this.setMaxStackSize(1);
						        this.setMaxDamage((int)(getMaxDamage() * increaseArmorDurability));
						    }
							@Override
						    public boolean getIsRepairable(ItemStack itemToRepair, ItemStack itemToSpend) {
								if(itemToSpend.getItem().equals(this.itemStack.getItem()) && (itemToSpend.getItemDamage() == this.itemStack.getItemDamage()) && itemToRepair.getItem().equals(this)) {
									return true;
								}
								else {
									return false;
								}
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
					public static class Tool {
						public static class ExtendedPickaxe extends ItemPickaxe {
						    public ExtendedPickaxe(int par1, EnumToolMaterial par2EnumToolMaterial, ItemStack repairMaterial, CreativeTabs tab, String uName) {
						        super(par1, par2EnumToolMaterial);
						        super.setCreativeTab(tab);
						        super.setUnlocalizedName(uName + Environment.Compatibility.stringExtendedWorkbench);
						        setMaxDamage((int)(getMaxDamage() * increaseToolDurability));
						        this.damageVsEntity *= increaseToolAttackDamage;
						        this.itemStack = repairMaterial;
						    }
						    @Override
						    public float getStrVsBlock(ItemStack par1ItemStack, Block par2Block) {
						        return super.getStrVsBlock(par1ItemStack, par2Block) * increaseToolPower;
						    }
						    public ItemStack itemStack;
							@Override
						    public boolean getIsRepairable(ItemStack itemToRepair, ItemStack itemToSpend) {
								if(itemToSpend.getItem().equals(this.itemStack.getItem()) && (itemToSpend.getItemDamage() == this.itemStack.getItemDamage()) && itemToRepair.getItem().equals(this)) {
									return true;
								}
								else {
									return false;
								}
							}
						}
						public static class ExtendedAxe extends ItemAxe {
						    public ExtendedAxe(int par1, EnumToolMaterial par2EnumToolMaterial, ItemStack repairMaterial, CreativeTabs tab, String uName) {
						        super(par1, par2EnumToolMaterial);
						        super.setCreativeTab(tab);
						        super.setUnlocalizedName(uName + Environment.Compatibility.stringExtendedWorkbench);
						        setMaxDamage((int)(getMaxDamage() * increaseToolDurability));
						        this.damageVsEntity *= increaseToolAttackDamage;
						        this.itemStack = repairMaterial;
						    }
						    @Override
						    public float getStrVsBlock(ItemStack par1ItemStack, Block par2Block) {
						        return super.getStrVsBlock(par1ItemStack, par2Block) * increaseToolPower;
						    }
						    public ItemStack itemStack;
							@Override
						    public boolean getIsRepairable(ItemStack itemToRepair, ItemStack itemToSpend) {
								if(itemToSpend.getItem().equals(this.itemStack.getItem()) && (itemToSpend.getItemDamage() == this.itemStack.getItemDamage()) && itemToRepair.getItem().equals(this)) {
									return true;
								}
								else {
									return false;
								}
							}
						}
						public static class ExtendedHoe extends ItemHoe {
						    public ExtendedHoe(int par1, EnumToolMaterial par2EnumToolMaterial, ItemStack repairMaterial, CreativeTabs tab, String uName) {
						        super(par1, par2EnumToolMaterial);
						        super.setCreativeTab(tab);
						        super.setUnlocalizedName(uName + Environment.Compatibility.stringExtendedWorkbench);
						        this.setMaxDamage((int)(getMaxDamage() * increaseHoeDurability));
						        this.itemStack = repairMaterial;
						    }
						    @Override
						    public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int i, int j, int k, int l, float f0, float f1, float f2) {
						        if(super.onItemUse(stack, player, world, i, j, k, l, f0, f1, f2))
						        	return world.setBlock(i, j, k, world.getBlockId(i, j, k), 15, 3);
						        return false;
						    }
						    public ItemStack itemStack;
							@Override
						    public boolean getIsRepairable(ItemStack itemToRepair, ItemStack itemToSpend) {
								if(itemToSpend.getItem().equals(this.itemStack.getItem()) && (itemToSpend.getItemDamage() == this.itemStack.getItemDamage()) && itemToRepair.getItem().equals(this)) {
									return true;
								}
								else {
									return false;
								}
							}
						}
						public static class ExtendedSword extends ItemSword {
							protected EnumToolMaterial ewToolMaterial;
							protected float exWeaponDamage;
						    public ExtendedSword(int par1, EnumToolMaterial par2EnumToolMaterial, ItemStack repairMaterial, CreativeTabs tab, String uName) {
						        super(par1, par2EnumToolMaterial);
						        super.setCreativeTab(tab);
						        super.setUnlocalizedName(uName + Environment.Compatibility.stringExtendedWorkbench);
						        this.ewToolMaterial = par2EnumToolMaterial;
						        this.itemStack = repairMaterial;
						        setMaxDamage((int)(getMaxDamage() * increaseSwordDurability));
						        exWeaponDamage = 5 + par2EnumToolMaterial.getDamageVsEntity() * increaseSwordDamage;
						    }
						    @Override
						    public Multimap getItemAttributeModifiers() {
						        Multimap multimap = HashMultimap.create();
						        multimap.put(SharedMonsterAttributes.attackDamage.getAttributeUnlocalizedName(), new AttributeModifier(field_111210_e, "Weapon modifier", (double)this.exWeaponDamage, 0));
						        return multimap;
						    }
						    public ItemStack itemStack;
							@Override
						    public boolean getIsRepairable(ItemStack itemToRepair, ItemStack itemToSpend) {
								if(itemToSpend.getItem().equals(this.itemStack.getItem()) && (itemToSpend.getItemDamage() == this.itemStack.getItemDamage()) && itemToRepair.getItem().equals(this)) {
									return true;
								}
								else {
									return false;
								}
							}
						    @Override
						    public float getStrVsBlock(ItemStack par1ItemStack, Block par2Block) {
						        return (int)(super.getStrVsBlock(par1ItemStack, par2Block) * increaseSwordMiningSpeed);
						    }
						}
						public static class ExtendedSpade extends ItemSpade {
						    public ExtendedSpade(int par1, EnumToolMaterial par2EnumToolMaterial, ItemStack repairMaterial, CreativeTabs tab, String uName) {
						        super(par1, par2EnumToolMaterial);
						        super.setCreativeTab(tab);
						        super.setUnlocalizedName(uName + Environment.Compatibility.stringExtendedWorkbench);
						        setMaxDamage((int)(getMaxDamage() * increaseToolDurability));
						        this.itemStack = repairMaterial;
						        this.damageVsEntity *= increaseToolAttackDamage;
						    }
						    @Override
						    public float getStrVsBlock(ItemStack par1ItemStack, Block par2Block) {
						        return super.getStrVsBlock(par1ItemStack, par2Block) * increaseToolPower;
						    }
						    public ItemStack itemStack;
							@Override
						    public boolean getIsRepairable(ItemStack itemToRepair, ItemStack itemToSpend) {
								if(itemToSpend.getItem().equals(this.itemStack.getItem()) && (itemToSpend.getItemDamage() == this.itemStack.getItemDamage()) && itemToRepair.getItem().equals(this)) {
									return true;
								}
								else {
									return false;
								}
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
				public static class Compatibility {
					public static final String stringExtendedWorkbench = Environment.Misc.symbolColon + Environment.Misc.wordExtended + Methods.FundamentalMethods.ToUppercaseFirstLetter(Environment.Misc.wordWorkbench);
				}
				public static class Lists {
					public static class Block {
						public static List<List> list = new ArrayList<List>();
					}
					public static class Item {
						public static List<List> toolList = new ArrayList<List>();
						public static List<List> armorList = new ArrayList<List>();
					}
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
				public static class Misc {
					public static final String symbolColon = ":";
					public static final String symbolDot = ".";
					public static final String symbolExclamationSignEnd = "!";
					public static final String wordBlocks = "blocks";
					public static final String wordClient = "client";
					public static final String wordCommon = "common";
					public static final String wordExtended = "extended";
					public static final String wordID = "id";
					public static final String wordInicial = "inicial";
					public static final String wordItems = "items";
					public static final String wordNormal = "normal";
					public static final String wordSword = "sword";
					public static final String wordTab = "tab";
					public static final String wordTerrain = "terrain";
					public static final String wordWarning = "warning";
					public static final String wordWorkbench = "workbench";
				}
			}
		}
	}
	public static class Main {
		public static class Content {
			public static class MoreCustomOresExtras {
				public static class Fixes {
					public static void ArmorMaterialsFix () {
						MoreCustomOres.armorGemaDeOricalco = EnumHelper.addArmorMaterial("GemaDeOricalco", 44, new int[]{4, 11, 8, 4}, 35);
						MoreCustomOres.armorLingoteDePlatino = EnumHelper.addArmorMaterial("LingoteDePlatino", 18, new int[]{2, 6, 5, 2}, 17);
						MoreCustomOres.armorLingoteDeTitanio = EnumHelper.addArmorMaterial("LingoteDeTitanio", 22, new int[]{3, 7, 5, 3}, 8);
						MoreCustomOres.armorCuarzo = EnumHelper.addArmorMaterial("Cuarzo", 12, new int[]{2, 6, 4, 2}, 30);
						MoreCustomOres.armorPiedra = EnumHelper.addArmorMaterial("Piedra", 6, new int[]{1, 3, 2, 1}, 5);
					}
					public static void ToolMaterialsFix () {
						MoreCustomOres.toolQuartz = EnumHelper.addToolMaterial("CUARZO", 2, 537, 12.5F, 2F, 15);
						MoreCustomOres.toolLingoteDePlatino = EnumHelper.addToolMaterial("LINGOTEDEPLATINO", 3, 730, 10.078125F, 2.2F, 28);
					}
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
					public static class ToolTypes {
						public static final String[] toolTypes = {"pickaxe", "axe", "hoe", "sword", "spade"};
						public static final String[] extendedtoolTypes = {Environment.Misc.wordExtended + Methods.FundamentalMethods.ToUppercaseFirstLetter(toolTypes[0]) , Environment.Misc.wordExtended + Methods.FundamentalMethods.ToUppercaseFirstLetter(toolTypes[1]), Environment.Misc.wordExtended + Methods.FundamentalMethods.ToUppercaseFirstLetter(toolTypes[2]), Environment.Misc.wordExtended + Methods.FundamentalMethods.ToUppercaseFirstLetter(toolTypes[3]), Environment.Misc.wordExtended + Methods.FundamentalMethods.ToUppercaseFirstLetter(toolTypes[4])};
					}
					public static class Materials {
						public static final String generalArray[] = {"orichalcum", "titanium", "platinum", "quartz", "stone"};
						public static final String enderArray[] = {"enderWeapon", "enderTool"};
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
						public static final EnumToolMaterial[] generalArray = {MoreCustomOres.toolGemaDeOricalco, MoreCustomOres.toolLingoteDeTitanio, MoreCustomOres.toolLingoteDePlatino, MoreCustomOres.toolQuartz};
						public static final EnumToolMaterial[] enderArray = {MoreCustomOres.toolEnderSword, MoreCustomOres.toolEnder};
					}
					public static class ArmorMaterials {
						public static final EnumArmorMaterial[] array = {MoreCustomOres.armorGemaDeOricalco, MoreCustomOres.armorLingoteDeTitanio, MoreCustomOres.armorLingoteDePlatino, MoreCustomOres.armorCuarzo, MoreCustomOres.armorPiedra};
					}
					public static class Tools {
						public static final Item extendedPickaxeOrichalcum = (Item) Methods.SimplifiedMethods.RegisterToListOfToolItems(Methods.SimplifiedMethods.ExtendedTool(0, Names.Materials.generalArray[0], ToolMaterials.generalArray[0], new ItemStack(MoreCustomOres.GemaDeOricalco, 0, 0), CreativeTabs.mainTab)).get(0);
						public static final Item extendedAxeOrichalcum = (Item) Methods.SimplifiedMethods.RegisterToListOfToolItems(Methods.SimplifiedMethods.ExtendedTool(1, Names.Materials.generalArray[0], ToolMaterials.generalArray[0], new ItemStack(MoreCustomOres.GemaDeOricalco, 0, 0), CreativeTabs.mainTab)).get(0);
						public static final Item extendedHoeOrichalcum = (Item) Methods.SimplifiedMethods.RegisterToListOfToolItems(Methods.SimplifiedMethods.ExtendedTool(2, Names.Materials.generalArray[0], ToolMaterials.generalArray[0], new ItemStack(MoreCustomOres.GemaDeOricalco, 0, 0), CreativeTabs.mainTab)).get(0);
						public static final Item extendedSwordOrichalcum = ((Item) Methods.SimplifiedMethods.RegisterToListOfToolItems(Methods.SimplifiedMethods.ExtendedTool(3, Names.Materials.generalArray[0], ToolMaterials.generalArray[0], new ItemStack(MoreCustomOres.GemaDeOricalco, 0, 0), CreativeTabs.mainTab)).get(0)).setTextureName("extendedworkbench:tool01");
						public static final Item extendedSpadeOrichalcum = (Item) Methods.SimplifiedMethods.RegisterToListOfToolItems(Methods.SimplifiedMethods.ExtendedTool(4, Names.Materials.generalArray[0], ToolMaterials.generalArray[0], new ItemStack(MoreCustomOres.GemaDeOricalco, 0, 0), CreativeTabs.mainTab)).get(0);
                        public static final Item extendedPickaxeTitanium = (Item) Methods.SimplifiedMethods.RegisterToListOfToolItems(Methods.SimplifiedMethods.ExtendedTool(0, Names.Materials.generalArray[1], ToolMaterials.generalArray[1], new ItemStack(MoreCustomOres.Lingotes, 0, 1), CreativeTabs.mainTab)).get(0);
                        public static final Item extendedAxeTitanium = (Item) Methods.SimplifiedMethods.RegisterToListOfToolItems(Methods.SimplifiedMethods.ExtendedTool(1, Names.Materials.generalArray[1], ToolMaterials.generalArray[1], new ItemStack(MoreCustomOres.Lingotes, 0, 1), CreativeTabs.mainTab)).get(0);
                        public static final Item extendedHoeTitanium = (Item) Methods.SimplifiedMethods.RegisterToListOfToolItems(Methods.SimplifiedMethods.ExtendedTool(2, Names.Materials.generalArray[1], ToolMaterials.generalArray[1], new ItemStack(MoreCustomOres.Lingotes, 0, 1), CreativeTabs.mainTab)).get(0);
                        public static final Item extendedSwordTitanium = (Item) Methods.SimplifiedMethods.RegisterToListOfToolItems(Methods.SimplifiedMethods.ExtendedTool(3, Names.Materials.generalArray[1], ToolMaterials.generalArray[1], new ItemStack(MoreCustomOres.Lingotes, 0, 1), CreativeTabs.mainTab)).get(0);
                        public static final Item extendedSpadeTitanium = (Item) Methods.SimplifiedMethods.RegisterToListOfToolItems(Methods.SimplifiedMethods.ExtendedTool(4, Names.Materials.generalArray[1], ToolMaterials.generalArray[1], new ItemStack(MoreCustomOres.Lingotes, 0, 1), CreativeTabs.mainTab)).get(0);
                        public static final Item extendedPickaxePlatinum = (Item) Methods.SimplifiedMethods.RegisterToListOfToolItems(Methods.SimplifiedMethods.ExtendedTool(0, Names.Materials.generalArray[2], ToolMaterials.generalArray[2], new ItemStack(MoreCustomOres.Lingotes, 0, 0), CreativeTabs.mainTab)).get(0);
                        public static final Item extendedAxePlatinum = (Item) Methods.SimplifiedMethods.RegisterToListOfToolItems(Methods.SimplifiedMethods.ExtendedTool(1, Names.Materials.generalArray[2], ToolMaterials.generalArray[2], new ItemStack(MoreCustomOres.Lingotes, 0, 0), CreativeTabs.mainTab)).get(0);
                        public static final Item extendedHoePlatinum = (Item) Methods.SimplifiedMethods.RegisterToListOfToolItems(Methods.SimplifiedMethods.ExtendedTool(2, Names.Materials.generalArray[2], ToolMaterials.generalArray[2], new ItemStack(MoreCustomOres.Lingotes, 0, 0), CreativeTabs.mainTab)).get(0);
                        public static final Item extendedSwordPlatinum = (Item) Methods.SimplifiedMethods.RegisterToListOfToolItems(Methods.SimplifiedMethods.ExtendedTool(3, Names.Materials.generalArray[2], ToolMaterials.generalArray[2], new ItemStack(MoreCustomOres.Lingotes, 0, 0), CreativeTabs.mainTab)).get(0);
                        public static final Item extendedSpadePlatinum = (Item) Methods.SimplifiedMethods.RegisterToListOfToolItems(Methods.SimplifiedMethods.ExtendedTool(4, Names.Materials.generalArray[2], ToolMaterials.generalArray[2], new ItemStack(MoreCustomOres.Lingotes, 0, 0), CreativeTabs.mainTab)).get(0);
                        public static final Item extendedPickaxeQuartz = (Item) Methods.SimplifiedMethods.RegisterToListOfToolItems(Methods.SimplifiedMethods.ExtendedTool(0, Names.Materials.generalArray[3], ToolMaterials.generalArray[3], new ItemStack(Item.netherQuartz, 0, 0), CreativeTabs.testTab)).get(0);
                        public static final Item extendedAxeQuartz = (Item) Methods.SimplifiedMethods.RegisterToListOfToolItems(Methods.SimplifiedMethods.ExtendedTool(1, Names.Materials.generalArray[3], ToolMaterials.generalArray[3], new ItemStack(Item.netherQuartz, 0, 0), CreativeTabs.testTab)).get(0);
                        public static final Item extendedHoeQuartz = (Item) Methods.SimplifiedMethods.RegisterToListOfToolItems(Methods.SimplifiedMethods.ExtendedTool(2, Names.Materials.generalArray[3], ToolMaterials.generalArray[3], new ItemStack(Item.netherQuartz, 0, 0), CreativeTabs.testTab)).get(0);
                        public static final Item extendedSwordQuartz = (Item) Methods.SimplifiedMethods.RegisterToListOfToolItems(Methods.SimplifiedMethods.ExtendedTool(3, Names.Materials.generalArray[3], ToolMaterials.generalArray[3], new ItemStack(Item.netherQuartz, 0, 0), CreativeTabs.testTab)).get(0);
                        public static final Item extendedSpadeQuartz = (Item) Methods.SimplifiedMethods.RegisterToListOfToolItems(Methods.SimplifiedMethods.ExtendedTool(4, Names.Materials.generalArray[3], ToolMaterials.generalArray[3], new ItemStack(Item.netherQuartz, 0, 0), CreativeTabs.testTab)).get(0);
                        public static final Item extendedPickaxeEnder = (Item) Methods.SimplifiedMethods.RegisterToListOfToolItems(Methods.SimplifiedMethods.ExtendedTool(0, Names.Materials.enderArray[1], ToolMaterials.enderArray[1], new ItemStack(Block.dragonEgg, 0, 0), CreativeTabs.testTab)).get(0);
                        public static final Item extendedAxeEnder = (Item) Methods.SimplifiedMethods.RegisterToListOfToolItems(Methods.SimplifiedMethods.ExtendedTool(1, Names.Materials.enderArray[1], ToolMaterials.enderArray[1], new ItemStack(Block.dragonEgg, 0, 0), CreativeTabs.testTab)).get(0);
                        public static final Item extendedHoeEnder = (Item) Methods.SimplifiedMethods.RegisterToListOfToolItems(Methods.SimplifiedMethods.ExtendedTool(2, Names.Materials.enderArray[1], ToolMaterials.enderArray[1], new ItemStack(Block.dragonEgg, 0, 0), CreativeTabs.testTab)).get(0);
                        public static final Item extendedSwordEnder = (Item) Methods.SimplifiedMethods.RegisterToListOfToolItems(Methods.SimplifiedMethods.ExtendedTool(3, Names.Materials.enderArray[0], ToolMaterials.enderArray[0], new ItemStack(Block.dragonEgg, 0, 0), CreativeTabs.testTab)).get(0);
                        public static final Item extendedSpadeEnder = (Item) Methods.SimplifiedMethods.RegisterToListOfToolItems(Methods.SimplifiedMethods.ExtendedTool(4, Names.Materials.enderArray[1], ToolMaterials.enderArray[1], new ItemStack(Block.dragonEgg, 0, 0), CreativeTabs.testTab)).get(0);
					}
					public static class Armor {
						public static final Item extendedHelmetOrichalcum = (Item) Methods.SimplifiedMethods.RegisterToListOfArmorItems(Methods.SimplifiedMethods.ExtendedArmor(0, Names.Materials.generalArray[0], ArmorMaterials.array[0], new ItemStack(MoreCustomOres.GemaDeOricalco, 0, 0), CreativeTabs.mainTab)).get(0);
						public static final Item extendedChestplateOrichalcum = (Item) Methods.SimplifiedMethods.RegisterToListOfArmorItems(Methods.SimplifiedMethods.ExtendedArmor(1, Names.Materials.generalArray[0], ArmorMaterials.array[0], new ItemStack(MoreCustomOres.GemaDeOricalco, 0, 0), CreativeTabs.mainTab)).get(0);
						public static final Item extendedPantsOrichalcum = (Item) Methods.SimplifiedMethods.RegisterToListOfArmorItems(Methods.SimplifiedMethods.ExtendedArmor(2, Names.Materials.generalArray[0], ArmorMaterials.array[0], new ItemStack(MoreCustomOres.GemaDeOricalco, 0, 0), CreativeTabs.mainTab)).get(0);
						public static final Item extendedBootsOrichalcum = (Item) Methods.SimplifiedMethods.RegisterToListOfArmorItems(Methods.SimplifiedMethods.ExtendedArmor(3, Names.Materials.generalArray[0], ArmorMaterials.array[0], new ItemStack(MoreCustomOres.GemaDeOricalco, 0, 0), CreativeTabs.mainTab)).get(0);
                        public static final Item extendedHelmetTitanium = (Item) Methods.SimplifiedMethods.RegisterToListOfArmorItems(Methods.SimplifiedMethods.ExtendedArmor(0, Names.Materials.generalArray[1], ArmorMaterials.array[1], new ItemStack(MoreCustomOres.Lingotes, 0, 1), CreativeTabs.mainTab)).get(0);
                        public static final Item extendedChestplateTitanium = (Item) Methods.SimplifiedMethods.RegisterToListOfArmorItems(Methods.SimplifiedMethods.ExtendedArmor(1, Names.Materials.generalArray[1], ArmorMaterials.array[1], new ItemStack(MoreCustomOres.Lingotes, 0, 1), CreativeTabs.mainTab)).get(0);
                        public static final Item extendedPantsTitanium = (Item) Methods.SimplifiedMethods.RegisterToListOfArmorItems(Methods.SimplifiedMethods.ExtendedArmor(2, Names.Materials.generalArray[1], ArmorMaterials.array[1], new ItemStack(MoreCustomOres.Lingotes, 0, 1), CreativeTabs.mainTab)).get(0);
                        public static final Item extendedBootsTitanium = (Item) Methods.SimplifiedMethods.RegisterToListOfArmorItems(Methods.SimplifiedMethods.ExtendedArmor(3, Names.Materials.generalArray[1], ArmorMaterials.array[1], new ItemStack(MoreCustomOres.Lingotes, 0, 1), CreativeTabs.mainTab)).get(0);
                        public static final Item extendedHelmetPlatinum = (Item) Methods.SimplifiedMethods.RegisterToListOfArmorItems(Methods.SimplifiedMethods.ExtendedArmor(0, Names.Materials.generalArray[2], ArmorMaterials.array[2], new ItemStack(MoreCustomOres.Lingotes, 0, 0), CreativeTabs.mainTab)).get(0);
                        public static final Item extendedChestplatePlatinum = (Item) Methods.SimplifiedMethods.RegisterToListOfArmorItems(Methods.SimplifiedMethods.ExtendedArmor(1, Names.Materials.generalArray[2], ArmorMaterials.array[2], new ItemStack(MoreCustomOres.Lingotes, 0, 0), CreativeTabs.mainTab)).get(0);
                        public static final Item extendedPantsPlatinum = (Item) Methods.SimplifiedMethods.RegisterToListOfArmorItems(Methods.SimplifiedMethods.ExtendedArmor(2, Names.Materials.generalArray[2], ArmorMaterials.array[2], new ItemStack(MoreCustomOres.Lingotes, 0, 0), CreativeTabs.mainTab)).get(0);
                        public static final Item extendedBootsPlatinum = (Item) Methods.SimplifiedMethods.RegisterToListOfArmorItems(Methods.SimplifiedMethods.ExtendedArmor(3, Names.Materials.generalArray[2], ArmorMaterials.array[2], new ItemStack(MoreCustomOres.Lingotes, 0, 0), CreativeTabs.mainTab)).get(0);
					}
					public static class CreativeTabs {
						public static final CustomTypes.MainTab mainTab = new CustomTypes.MainTab(Names.CreativeTabs.extended);
						public static final CustomTypes.MainTab testTab = new CustomTypes.MainTab("Test");
					}
				}
			}
		}
	}
	@EventHandler
	public static void PreInit(FMLPreInitializationEvent event) {
		Content.MoreCustomOresExtras.Fixes.ArmorMaterialsFix();
		Content.MoreCustomOresExtras.Fixes.ToolMaterialsFix();
		if(FMLCommonHandler.instance().getEffectiveSide().isClient()) {
			Content.Mod.Declaration.Renderers.armorRenderer = new int[] {RenderingRegistry.addNewArmourRendererPrefix(Environment.Misc.wordExtended + Content.Mod.Names.Materials.generalArray[0]), RenderingRegistry.addNewArmourRendererPrefix(Environment.Misc.wordExtended + Content.Mod.Names.Materials.generalArray[1]), RenderingRegistry.addNewArmourRendererPrefix(Environment.Misc.wordExtended + Content.Mod.Names.Materials.generalArray[2]), RenderingRegistry.addNewArmourRendererPrefix(Environment.Misc.wordExtended + Content.Mod.Names.Materials.generalArray[3]), RenderingRegistry.addNewArmourRendererPrefix(Environment.Misc.wordExtended + Content.Mod.Names.Materials.generalArray[4])};
		}
	}
	@EventHandler
	public static void Init(FMLInitializationEvent event) {
		Methods.FundamentalMethods.LoadConfig(confiGuekho);
		final int[] ids = Methods.FundamentalMethods.DoConfigTasks(confiGuekho, (List) Methods.FundamentalMethods.BlockEmptyIDs(), Environment.IDs.Block.inicial, Methods.FundamentalMethods.ItemEmptyIDs(), Environment.IDs.Item.inicial);
		Environment.IDs.Block.Terrain.real = ids[0];
		Environment.IDs.Block.Normal.real = ids[1];
		Environment.IDs.Item.real = ids[2];
		final Content.Mod.Config.Declaration configDeclaration = new Content.Mod.Config.Declaration();
		final Content.Mod.Declaration.Renderers renderers = new Content.Mod.Declaration.Renderers();
		final Content.Mod.Declaration.ArmorMaterials armorMaterials = new Content.Mod.Declaration.ArmorMaterials();
		final Content.Mod.Declaration.ToolMaterials toolMaterials = new Content.Mod.Declaration.ToolMaterials();
		final Content.Mod.Declaration.Armor armor = new Content.Mod.Declaration.Armor();
		final Content.Mod.Declaration.Tools tools = new Content.Mod.Declaration.Tools();
		final Content.Mod.Declaration.CreativeTabs creativeTabs = new Content.Mod.Declaration.CreativeTabs();
		Methods.FundamentalMethods.SaveConfig(confiGuekho);
		proxy.registerRenderInformation();
	}
	@EventHandler
	public static void PostInit(FMLPostInitializationEvent event) {}
}
