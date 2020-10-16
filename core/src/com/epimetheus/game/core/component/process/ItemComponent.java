package com.epimetheus.game.core.component.process;

import com.epimetheus.game.core.component.AbstractComponent;
import com.epimetheus.game.core.component.Component;
import com.epimetheus.game.core.entity.Entity;

public class ItemComponent extends AbstractComponent {
	
	private boolean installable = false;
	private boolean installed =  false;
	private InventoryComponent container = null;
	private Entity owner = null;
	
	private ItemComponent(boolean installable, boolean installed, InventoryComponent container, Entity owner) {
		super();
		this.installable = installable;
		this.installed = installed;
		this.container = container;
		this.owner = owner;
	}

	public boolean isInstallable() {
		return installable;
	}
	public void setInstallable(boolean installable) {
		this.installable = installable;
	}
	public boolean isInstalled() {
		return installed;
	}
	public void setInstalled(boolean installed) {
		this.installed = installed;
	}
	public InventoryComponent getContainer() {
		return container;
	}
	public void setContainer(InventoryComponent container) {
		this.container = container;
	}
	public Entity getOwner() {
		return owner;
	}
	public void setOwner(Entity owner) {
		this.owner = owner;
	}

	@Override
	public Class<? extends Component> getComponentType() {
		return ItemComponent.class;
	}
	
	public static ItemComponent generate(boolean installable) {
		return new ItemComponent(installable, false, null, null);
	}
}
