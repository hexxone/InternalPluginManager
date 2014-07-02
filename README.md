InternalPluginManager
=====================

The InternalPluginManager is an API to make the command usage in Bukkit plugins more lightweight. Although, the InternalPluginManager has, as the name says, an internal plugin system so you can use plugins without using the Bukkit library directly.

Mainly the InternalPluginManager provides:
	- A lightweight command system (see below for a details)
	- A lightweight internal plugin system below Bukkit (see below for details)
	- Some utility classes (like StackUtil, StringUtil, etc.) and the TSS (TagStorageSystem, see http://repo.blockhaus2000.com/com/blockhaus2000/tag-storage-system/)


The command system:
-------------------
With the command system, you can tag methods with an annotation (@Command) to mark them as a command executor. You only have to register the class that contains the executors.
The annotations has a lot of functions, such as:

	- Aliases (The first alias is the command, of course.)
	- Easy selection of allowed senders
	- Easy and lightweight flag system (toggle- and value-flags)
	- Syntax selection (for example, you can select that the second argument has to be an Integer)
	- Min/Max arguments
	- Command priority (You can tag more than one method with the same command, the method with the highest priority will be executed at first.)
	- Second level command (If you tag more than one method with the same command (for example "/foo"), you can set a second level command (for example "do"). The method will only be executed, if the user executed "/foo do".)
	- A lot more things


The plugin system:
------------------
With the plugin system, you can load plugins without the direct usage of Bukkit (plugins are located in "plugins/InternalPluginManager/plugins/". If you use this, the other systems works as best as possible.
Main features of the plugin system:

	- Easy access to the command system
	- Access to all Bukkit systems
	- Higher performance because Bukkit doesn't have to load so much plugins


Upcoming features:
------------------
	- An update system for the plugin system (You place the updated jar in the "plugins/InternalPluginManager/updates/" folder an execute the command "/ipm update", then the plugin to update will be updated to the latest version.).


NOTE: This README document is still work in progress!
