package com.epimetheus.game.core.component.render;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.epimetheus.game.core.component.AbstractComponent;
import com.epimetheus.game.core.component.Component;

public class RenderComponent extends AbstractComponent {

	private Texture texture = null;
	private TextureRegion textureRegion = null;
	
	private RenderComponent() {
	}
	
	public TextureRegion getTextureRegion() {
		return textureRegion;
	}
	public Texture getTexture() {
		return texture;
	}
	public boolean isTextureRegion() {
		return textureRegion != null;
	}
	
	@Override
	public Class<? extends Component> getComponentType() {
		return RenderComponent.class;
	}
	
	public static Component generate(Texture texture) {
		RenderComponent comp = new RenderComponent();
		comp.texture = texture;
		return comp;
	}
	public static Component generate(TextureRegion textureRegion) {
		RenderComponent comp = new RenderComponent();
		comp.textureRegion = textureRegion;
		return comp;
	}
	public static Component generate(String atlasName, String regionName) {
		RenderComponent comp = new RenderComponent();
		TextureAtlas atlas = new TextureAtlas("img/"+atlasName+".txt");
		TextureRegion region = atlas.findRegion(regionName);
		comp.textureRegion = region;
		return comp;
	}
}
