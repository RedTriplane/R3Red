
package com.jfixby.r3.ui.red.activity;

import com.jfixby.r3.api.activity.LayerBasedComponent;
import com.jfixby.r3.api.activity.input.CustomInput;
import com.jfixby.r3.api.activity.input.CustomInputSpecs;
import com.jfixby.r3.api.activity.input.TouchArea;
import com.jfixby.r3.api.activity.input.TouchAreaSpecs;
import com.jfixby.r3.api.activity.layer.Layer;
import com.jfixby.r3.api.activity.raster.Raster;
import com.jfixby.r3.api.activity.user.MouseInputEventListener;
import com.jfixby.r3.ui.red.activity.layers.RedLayer;
import com.jfixby.scarabei.api.collections.Collection;
import com.jfixby.scarabei.api.collections.Collections;
import com.jfixby.scarabei.api.collections.List;
import com.jfixby.scarabei.api.floatn.Float2;
import com.jfixby.scarabei.api.geometry.CanvasPosition;
import com.jfixby.scarabei.api.geometry.Geometry;

public class RedCustomInput implements CustomInput, LayerBasedComponent {

	@Override
	public String toString () {
		return "CustomInput[" + this.root.getName() + "]";
	}

	private final RedLayer root;

	final List<TouchArea> touch_areas = Collections.newList();
	final List<Raster> graphics = Collections.newList();

	private MouseInputEventListener listener;

	public RedCustomInput (final CustomInputSpecs button_specs, final RedComponentsFactory master) {
		this.root = master.newLayer();
		this.setName(button_specs.getName());
		final Collection<TouchAreaSpecs> areas = button_specs.listTouchAreas();
		{
			final Collection<Raster> options = button_specs.listOptions();
			for (int i = 0; i < options.size(); i++) {
				final Raster option_i = options.getElementAt(i);
				this.root.attachComponent(option_i);
				this.graphics.add(option_i);
				option_i.setOriginAbsolute(0, 0);
			}
		}
		for (int i = 0; i < areas.size(); i++) {
			final TouchAreaSpecs area_specs = areas.getElementAt(i);
			// area_specs.setID(id);

			final TouchArea area = master.getUserInputDepartment().newTouchArea(area_specs);
			area.shape().setOriginAbsolute(0, 0);
			area.setDebugRenderFlag(false);
			this.touch_areas.add(area);
		}
		this.root.attachComponents(this.touch_areas);
		this.setInputListener(this.listener);
	}

	@Override
	public void hide () {
		this.root.hide();
	}

	@Override
	public void show () {
		this.root.show();
	}

	@Override
	public boolean isVisible () {
		return this.root.isVisible();
	}

	@Override
	public void setName (final String name) {
		this.root.setName(name);
	}

	@Override
	public String getName () {
		return this.root.getName();
	}

	@Override
	public void setVisible (final boolean b) {
		this.root.setVisible(b);
	}

	@Override
	public Layer getRoot () {
		return this.root;
	}

	@Override
	public MouseInputEventListener getInputListener () {
		return this.listener;
	}

	@Override
	public void setInputListener (final MouseInputEventListener listener) {
		this.listener = listener;
		for (int i = 0; i < this.touch_areas.size(); i++) {
			this.touch_areas.getElementAt(i).setInputListener(this.listener);
		}

	}

	@Override
	public void setDebugRenderFlag (final boolean b) {
		for (int i = 0; i < this.touch_areas.size(); i++) {
			this.touch_areas.getElementAt(i).setDebugRenderFlag(b);
		}
	}

	@Override
	public Collection<Raster> listOptions () {
		return this.graphics;
	}

	@Override
	public Collection<TouchArea> listTouchAreas () {
		return this.touch_areas;
	}

	final CanvasPosition position = Geometry.newCanvasPosition();

	@Override
	public void setPosition (final Float2 position) {
		this.position.set(position);
	}

	@Override
	public CanvasPosition getPosition () {
		return this.position;
	}

	@Override
	public void updateChildrenPositionRespectively () {
		for (int i = 0; i < this.touch_areas.size(); i++) {
			this.touch_areas.getElementAt(i).shape().setPosition(this.position);
		}
		for (int i = 0; i < this.graphics.size(); i++) {
			this.graphics.getElementAt(i).setPosition(this.position);
		}
	}

	@Override
	public void setPositionX (final double x) {
		this.position.setX(x);
	}

	@Override
	public void setPositionY (final double y) {
		this.position.setY(y);
	}

	@Override
	public double getPositionX () {
		return this.position.getX();
	}

	@Override
	public double getPositionY () {
		return this.position.getY();
	}

}
