/* This file is part of InternalPluginManager
 *
 * Copyright 2014 Blockhaus2000
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  see the License for the specific language governing permissions and
 *  Limitations under the License.
 */
package com.blockhaus2000.bukkit.mock;

import java.net.InetSocketAddress;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import org.bukkit.Achievement;
import org.bukkit.Effect;
import org.bukkit.EntityEffect;
import org.bukkit.GameMode;
import org.bukkit.Instrument;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Note;
import org.bukkit.Server;
import org.bukkit.Sound;
import org.bukkit.Statistic;
import org.bukkit.WeatherType;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.conversations.Conversation;
import org.bukkit.conversations.ConversationAbandonedEvent;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Egg;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.entity.Snowball;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerTeleportEvent.TeleportCause;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.InventoryView.Property;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.map.MapView;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.permissions.Permission;
import org.bukkit.permissions.PermissionAttachment;
import org.bukkit.permissions.PermissionAttachmentInfo;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.util.Vector;

/**
 *
 * @author Blockhaus2000
 */
@SuppressWarnings("javadoc")
public class MockPlayer implements Player {
    private String perm;
    private final boolean op;

    /**
     * This is a mock implementation.
     *
     * @param perm
     *            This is a mock implementation.
     * @param op
     *            This is a mock implementation.
     */
    public MockPlayer(final String perm, final boolean op) {
        this.perm = perm;
        this.op = op;
    }

    @Override
    public void sendMessage(final String message) {
    }

    @Override
    public void sendMessage(final String[] messages) {
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public PlayerInventory getInventory() {
        return null;
    }

    @Override
    public Inventory getEnderChest() {
        return null;
    }

    @Override
    public boolean setWindowProperty(final Property prop, final int value) {
        return false;
    }

    @Override
    public InventoryView getOpenInventory() {
        return null;
    }

    @Override
    public InventoryView openInventory(final Inventory inventory) {
        return null;
    }

    @Override
    public InventoryView openWorkbench(final Location location, final boolean force) {
        return null;
    }

    @Override
    public InventoryView openEnchanting(final Location location, final boolean force) {
        return null;
    }

    @Override
    public void openInventory(final InventoryView inventory) {
    }

    @Override
    public void closeInventory() {
    }

    @Override
    public ItemStack getItemInHand() {
        return null;
    }

    @Override
    public void setItemInHand(final ItemStack item) {
    }

    @Override
    public ItemStack getItemOnCursor() {
        return null;
    }

    @Override
    public void setItemOnCursor(final ItemStack item) {
    }

    @Override
    public boolean isSleeping() {
        return false;
    }

    @Override
    public int getSleepTicks() {
        return 0;
    }

    @Override
    public GameMode getGameMode() {
        return null;
    }

    @Override
    public void setGameMode(final GameMode mode) {
    }

    @Override
    public boolean isBlocking() {
        return false;
    }

    @Override
    public int getExpToLevel() {
        return 0;
    }

    @Override
    public boolean isPermissionSet(final String name) {
        return false;
    }

    @Override
    public boolean isPermissionSet(final Permission perm) {
        return false;
    }

    /**
     * {@inheritDoc}
     *
     * @see org.bukkit.permissions.Permissible#hasPermission(java.lang.String)
     */
    @Override
    public boolean hasPermission(final String perm) {
        return this.perm != null && perm != null && this.perm.equals(perm);
    }

    /**
     * {@inheritDoc}
     *
     * @see org.bukkit.permissions.Permissible#hasPermission(org.bukkit.permissions.Permission)
     */
    @Override
    public boolean hasPermission(final Permission perm) {
        return hasPermission(perm.getName());
    }

    /**
     * Sets the permission that the {@link MockPlayer} have.
     *
     * <p>
     * <b> This is only for testing! </b>
     * </p>
     *
     * @param perm
     *            The permission to set
     */
    public void setPermission(final String perm) {
        this.perm = perm;
    }

    @Override
    public PermissionAttachment addAttachment(final Plugin plugin, final String name, final boolean value) {
        return null;
    }

    @Override
    public PermissionAttachment addAttachment(final Plugin plugin) {
        return null;
    }

    @Override
    public PermissionAttachment addAttachment(final Plugin plugin, final String name, final boolean value, final int ticks) {
        return null;
    }

    @Override
    public PermissionAttachment addAttachment(final Plugin plugin, final int ticks) {
        return null;
    }

    @Override
    public void removeAttachment(final PermissionAttachment attachment) {
    }

    @Override
    public void recalculatePermissions() {
    }

    @Override
    public Set<PermissionAttachmentInfo> getEffectivePermissions() {
        return null;
    }

    @Override
    public boolean isOp() {
        return op;
    }

    @Override
    public void setOp(final boolean value) {
    }

    @Override
    public double getEyeHeight() {
        return 0;
    }

    @Override
    public double getEyeHeight(final boolean ignoreSneaking) {
        return 0;
    }

    @Override
    public Location getEyeLocation() {
        return null;
    }

    @Override
    public List<Block> getLineOfSight(final HashSet<Byte> transparent, final int maxDistance) {
        return null;
    }

    @Override
    public Block getTargetBlock(final HashSet<Byte> transparent, final int maxDistance) {
        return null;
    }

    @Override
    public List<Block> getLastTwoTargetBlocks(final HashSet<Byte> transparent, final int maxDistance) {
        return null;
    }

    @Override
    public Egg throwEgg() {
        return null;
    }

    @Override
    public Snowball throwSnowball() {
        return null;
    }

    @Override
    public Arrow shootArrow() {
        return null;
    }

    @Override
    public int getRemainingAir() {
        return 0;
    }

    @Override
    public void setRemainingAir(final int ticks) {
    }

    @Override
    public int getMaximumAir() {
        return 0;
    }

    @Override
    public void setMaximumAir(final int ticks) {
    }

    @Override
    public int getMaximumNoDamageTicks() {
        return 0;
    }

    @Override
    public void setMaximumNoDamageTicks(final int ticks) {
    }

    @Override
    public double getLastDamage() {
        return 0;
    }

    @Override
    public int _INVALID_getLastDamage() {
        return 0;
    }

    @Override
    public void setLastDamage(final double damage) {
    }

    @Override
    public void _INVALID_setLastDamage(final int damage) {
    }

    @Override
    public int getNoDamageTicks() {
        return 0;
    }

    @Override
    public void setNoDamageTicks(final int ticks) {
    }

    @Override
    public Player getKiller() {
        return null;
    }

    @Override
    public boolean addPotionEffect(final PotionEffect effect) {
        return false;
    }

    @Override
    public boolean addPotionEffect(final PotionEffect effect, final boolean force) {
        return false;
    }

    @Override
    public boolean addPotionEffects(final Collection<PotionEffect> effects) {
        return false;
    }

    @Override
    public boolean hasPotionEffect(final PotionEffectType type) {
        return false;
    }

    @Override
    public void removePotionEffect(final PotionEffectType type) {
    }

    @Override
    public Collection<PotionEffect> getActivePotionEffects() {
        return null;
    }

    @Override
    public boolean hasLineOfSight(final Entity other) {
        return false;
    }

    @Override
    public boolean getRemoveWhenFarAway() {
        return false;
    }

    @Override
    public void setRemoveWhenFarAway(final boolean remove) {
    }

    @Override
    public EntityEquipment getEquipment() {
        return null;
    }

    @Override
    public void setCanPickupItems(final boolean pickup) {
    }

    @Override
    public boolean getCanPickupItems() {
        return false;
    }

    @Override
    public void setCustomName(final String name) {
    }

    @Override
    public String getCustomName() {
        return null;
    }

    @Override
    public void setCustomNameVisible(final boolean flag) {
    }

    @Override
    public boolean isCustomNameVisible() {
        return false;
    }

    @Override
    public boolean isLeashed() {
        return false;
    }

    @Override
    public Entity getLeashHolder() throws IllegalStateException {
        return null;
    }

    @Override
    public boolean setLeashHolder(final Entity holder) {
        return false;
    }

    @Override
    public <T extends Projectile> T launchProjectile(final Class<? extends T> projectile) {
        return null;
    }

    @Override
    public <T extends Projectile> T launchProjectile(final Class<? extends T> projectile, final Vector velocity) {
        return null;
    }

    @Override
    public void damage(final double amount) {
    }

    @Override
    public void _INVALID_damage(final int amount) {
    }

    @Override
    public void damage(final double amount, final Entity source) {
    }

    @Override
    public void _INVALID_damage(final int amount, final Entity source) {
    }

    @Override
    public double getHealth() {
        return 0;
    }

    @Override
    public int _INVALID_getHealth() {
        return 0;
    }

    @Override
    public void setHealth(final double health) {
    }

    @Override
    public void _INVALID_setHealth(final int health) {
    }

    @Override
    public double getMaxHealth() {
        return 0;
    }

    @Override
    public int _INVALID_getMaxHealth() {
        return 0;
    }

    @Override
    public void setMaxHealth(final double health) {
    }

    @Override
    public void _INVALID_setMaxHealth(final int health) {
    }

    @Override
    public void resetMaxHealth() {
    }

    @Override
    public Location getLocation() {
        return null;
    }

    @Override
    public Location getLocation(final Location loc) {
        return null;
    }

    @Override
    public void setVelocity(final Vector velocity) {
    }

    @Override
    public Vector getVelocity() {
        return null;
    }

    @Override
    public World getWorld() {
        return null;
    }

    @Override
    public boolean teleport(final Location location) {
        return false;
    }

    @Override
    public boolean teleport(final Location location, final TeleportCause cause) {
        return false;
    }

    @Override
    public boolean teleport(final Entity destination) {
        return false;
    }

    @Override
    public boolean teleport(final Entity destination, final TeleportCause cause) {
        return false;
    }

    @Override
    public List<Entity> getNearbyEntities(final double x, final double y, final double z) {
        return null;
    }

    @Override
    public int getEntityId() {
        return 0;
    }

    @Override
    public int getFireTicks() {
        return 0;
    }

    @Override
    public int getMaxFireTicks() {
        return 0;
    }

    @Override
    public void setFireTicks(final int ticks) {
    }

    @Override
    public void remove() {
    }

    @Override
    public boolean isDead() {
        return false;
    }

    @Override
    public boolean isValid() {
        return false;
    }

    @Override
    public Server getServer() {
        return null;
    }

    @Override
    public Entity getPassenger() {
        return null;
    }

    @Override
    public boolean setPassenger(final Entity passenger) {
        return false;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean eject() {
        return false;
    }

    @Override
    public float getFallDistance() {
        return 0;
    }

    @Override
    public void setFallDistance(final float distance) {
    }

    @Override
    public void setLastDamageCause(final EntityDamageEvent event) {
    }

    @Override
    public EntityDamageEvent getLastDamageCause() {
        return null;
    }

    @Override
    public UUID getUniqueId() {
        return null;
    }

    @Override
    public int getTicksLived() {
        return 0;
    }

    @Override
    public void setTicksLived(final int value) {
    }

    @Override
    public void playEffect(final EntityEffect type) {
    }

    @Override
    public EntityType getType() {
        return null;
    }

    @Override
    public boolean isInsideVehicle() {
        return false;
    }

    @Override
    public boolean leaveVehicle() {
        return false;
    }

    @Override
    public Entity getVehicle() {
        return null;
    }

    @Override
    public void sendPluginMessage(final Plugin source, final String channel, final byte[] message) {
    }

    @Override
    public Set<String> getListeningPluginChannels() {
        return null;
    }

    @Override
    public boolean isConversing() {
        return false;
    }

    @Override
    public void acceptConversationInput(final String input) {
    }

    @Override
    public boolean beginConversation(final Conversation conversation) {
        return false;
    }

    @Override
    public void abandonConversation(final Conversation conversation) {
    }

    @Override
    public void abandonConversation(final Conversation conversation, final ConversationAbandonedEvent details) {
    }

    @Override
    public boolean isOnline() {
        return false;
    }

    @Override
    public boolean isBanned() {
        return false;
    }

    @Override
    public void setBanned(final boolean banned) {
    }

    @Override
    public boolean isWhitelisted() {
        return false;
    }

    @Override
    public void setWhitelisted(final boolean value) {
    }

    @Override
    public Player getPlayer() {
        return null;
    }

    @Override
    public long getFirstPlayed() {
        return 0;
    }

    @Override
    public long getLastPlayed() {
        return 0;
    }

    @Override
    public boolean hasPlayedBefore() {
        return false;
    }

    @Override
    public Map<String, Object> serialize() {
        return null;
    }

    @Override
    public void setMetadata(final String metadataKey, final MetadataValue newMetadataValue) {
    }

    @Override
    public List<MetadataValue> getMetadata(final String metadataKey) {
        return null;
    }

    @Override
    public boolean hasMetadata(final String metadataKey) {
        return false;
    }

    @Override
    public void removeMetadata(final String metadataKey, final Plugin owningPlugin) {
    }

    @Override
    public String getDisplayName() {
        return null;
    }

    @Override
    public void setDisplayName(final String name) {
    }

    @Override
    public String getPlayerListName() {
        return null;
    }

    @Override
    public void setPlayerListName(final String name) {
    }

    @Override
    public void setCompassTarget(final Location loc) {
    }

    @Override
    public Location getCompassTarget() {
        return null;
    }

    @Override
    public InetSocketAddress getAddress() {
        return null;
    }

    @Override
    public void sendRawMessage(final String message) {
    }

    @Override
    public void kickPlayer(final String message) {
    }

    @Override
    public void chat(final String msg) {
    }

    @Override
    public boolean performCommand(final String command) {
        return false;
    }

    @Override
    public boolean isSneaking() {
        return false;
    }

    @Override
    public void setSneaking(final boolean sneak) {
    }

    @Override
    public boolean isSprinting() {
        return false;
    }

    @Override
    public void setSprinting(final boolean sprinting) {
    }

    @Override
    public void saveData() {
    }

    @Override
    public void loadData() {
    }

    @Override
    public void setSleepingIgnored(final boolean isSleeping) {
    }

    @Override
    public boolean isSleepingIgnored() {
        return false;
    }

    @Override
    public void playNote(final Location loc, final byte instrument, final byte note) {
    }

    @Override
    public void playNote(final Location loc, final Instrument instrument, final Note note) {
    }

    @Override
    public void playSound(final Location location, final Sound sound, final float volume, final float pitch) {
    }

    @Override
    public void playSound(final Location location, final String sound, final float volume, final float pitch) {
    }

    @Override
    public void playEffect(final Location loc, final Effect effect, final int data) {
    }

    @Override
    public <T> void playEffect(final Location loc, final Effect effect, final T data) {
    }

    @Override
    public void sendBlockChange(final Location loc, final Material material, final byte data) {
    }

    @Override
    public boolean sendChunkChange(final Location loc, final int sx, final int sy, final int sz, final byte[] data) {
        return false;
    }

    @Override
    public void sendBlockChange(final Location loc, final int material, final byte data) {
    }

    @Override
    public void sendMap(final MapView map) {
    }

    @Override
    public void updateInventory() {
    }

    @Override
    public void awardAchievement(final Achievement achievement) {
    }

    @Override
    public void removeAchievement(final Achievement achievement) {
    }

    @Override
    public boolean hasAchievement(final Achievement achievement) {
        return false;
    }

    @Override
    public void incrementStatistic(final Statistic statistic) throws IllegalArgumentException {
    }

    @Override
    public void decrementStatistic(final Statistic statistic) throws IllegalArgumentException {
    }

    @Override
    public void incrementStatistic(final Statistic statistic, final int amount) throws IllegalArgumentException {
    }

    @Override
    public void decrementStatistic(final Statistic statistic, final int amount) throws IllegalArgumentException {
    }

    @Override
    public void setStatistic(final Statistic statistic, final int newValue) throws IllegalArgumentException {
    }

    @Override
    public int getStatistic(final Statistic statistic) throws IllegalArgumentException {
        return 0;
    }

    @Override
    public void incrementStatistic(final Statistic statistic, final Material material) throws IllegalArgumentException {
    }

    @Override
    public void decrementStatistic(final Statistic statistic, final Material material) throws IllegalArgumentException {
    }

    @Override
    public int getStatistic(final Statistic statistic, final Material material) throws IllegalArgumentException {
        return 0;
    }

    @Override
    public void incrementStatistic(final Statistic statistic, final Material material, final int amount)
            throws IllegalArgumentException {
    }

    @Override
    public void decrementStatistic(final Statistic statistic, final Material material, final int amount)
            throws IllegalArgumentException {
    }

    @Override
    public void setStatistic(final Statistic statistic, final Material material, final int newValue)
            throws IllegalArgumentException {
    }

    @Override
    public void incrementStatistic(final Statistic statistic, final EntityType entityType) throws IllegalArgumentException {
    }

    @Override
    public void decrementStatistic(final Statistic statistic, final EntityType entityType) throws IllegalArgumentException {
    }

    @Override
    public int getStatistic(final Statistic statistic, final EntityType entityType) throws IllegalArgumentException {
        return 0;
    }

    @Override
    public void incrementStatistic(final Statistic statistic, final EntityType entityType, final int amount)
            throws IllegalArgumentException {
    }

    @Override
    public void decrementStatistic(final Statistic statistic, final EntityType entityType, final int amount) {
    }

    @Override
    public void setStatistic(final Statistic statistic, final EntityType entityType, final int newValue) {
    }

    @Override
    public void setPlayerTime(final long time, final boolean relative) {
    }

    @Override
    public long getPlayerTime() {
        return 0;
    }

    @Override
    public long getPlayerTimeOffset() {
        return 0;
    }

    @Override
    public boolean isPlayerTimeRelative() {
        return false;
    }

    @Override
    public void resetPlayerTime() {
    }

    @Override
    public void setPlayerWeather(final WeatherType type) {
    }

    @Override
    public WeatherType getPlayerWeather() {
        return null;
    }

    @Override
    public void resetPlayerWeather() {
    }

    @Override
    public void giveExp(final int amount) {
    }

    @Override
    public void giveExpLevels(final int amount) {
    }

    @Override
    public float getExp() {
        return 0;
    }

    @Override
    public void setExp(final float exp) {
    }

    @Override
    public int getLevel() {
        return 0;
    }

    @Override
    public void setLevel(final int level) {
    }

    @Override
    public int getTotalExperience() {
        return 0;
    }

    @Override
    public void setTotalExperience(final int exp) {
    }

    @Override
    public float getExhaustion() {
        return 0;
    }

    @Override
    public void setExhaustion(final float value) {
    }

    @Override
    public float getSaturation() {
        return 0;
    }

    @Override
    public void setSaturation(final float value) {
    }

    @Override
    public int getFoodLevel() {
        return 0;
    }

    @Override
    public void setFoodLevel(final int value) {
    }

    @Override
    public Location getBedSpawnLocation() {
        return null;
    }

    @Override
    public void setBedSpawnLocation(final Location location) {
    }

    @Override
    public void setBedSpawnLocation(final Location location, final boolean force) {
    }

    @Override
    public boolean getAllowFlight() {
        return false;
    }

    @Override
    public void setAllowFlight(final boolean flight) {
    }

    @Override
    public void hidePlayer(final Player player) {
    }

    @Override
    public void showPlayer(final Player player) {
    }

    @Override
    public boolean canSee(final Player player) {
        return false;
    }

    @Override
    public boolean isOnGround() {
        return false;
    }

    @Override
    public boolean isFlying() {
        return false;
    }

    @Override
    public void setFlying(final boolean value) {
    }

    @Override
    public void setFlySpeed(final float value) throws IllegalArgumentException {
    }

    @Override
    public void setWalkSpeed(final float value) throws IllegalArgumentException {
    }

    @Override
    public float getFlySpeed() {
        return 0;
    }

    @Override
    public float getWalkSpeed() {
        return 0;
    }

    @Override
    public void setTexturePack(final String url) {
    }

    @Override
    public void setResourcePack(final String url) {
    }

    @Override
    public Scoreboard getScoreboard() {
        return null;
    }

    @Override
    public void setScoreboard(final Scoreboard scoreboard) throws IllegalArgumentException, IllegalStateException {
    }

    @Override
    public boolean isHealthScaled() {
        return false;
    }

    @Override
    public void setHealthScaled(final boolean scale) {
    }

    @Override
    public void setHealthScale(final double scale) throws IllegalArgumentException {
    }

    @Override
    public double getHealthScale() {
        return 0;
    }

    @Override
    public void sendSignChange(final Location loc, final String[] msgs) throws IllegalArgumentException {
    }
}
