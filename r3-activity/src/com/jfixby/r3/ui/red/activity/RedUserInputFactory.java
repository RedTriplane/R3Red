
package com.jfixby.r3.ui.red.activity;

import com.jfixby.r3.api.activity.input.Button;
import com.jfixby.r3.api.activity.input.ButtonSpecs;
import com.jfixby.r3.api.activity.input.CustomInput;
import com.jfixby.r3.api.activity.input.CustomInputSpecs;
import com.jfixby.r3.api.activity.input.TouchArea;
import com.jfixby.r3.api.activity.input.TouchAreaSpecs;
import com.jfixby.r3.api.activity.input.UserInputFactory;
import com.jfixby.r3.ui.red.activity.input.InputSpecs;
import com.jfixby.r3.ui.red.activity.input.RedButton;
import com.jfixby.r3.ui.red.activity.input.RedTouchArea;
import com.jfixby.r3.ui.red.activity.input.RedTouchAreaSpecs;

public class RedUserInputFactory implements UserInputFactory {

	private final RedComponentsFactory master;

	public RedUserInputFactory (final RedComponentsFactory redComponentsFactory) {
		this.master = redComponentsFactory;
	}

	@Override
	public ButtonSpecs newButtonSpecs () {
		return new InputSpecs();
	}

	@Override
	public Button newButton (final ButtonSpecs button_specs) {
		return new RedButton(button_specs, this.master);
	}

	@Override
	public TouchArea newTouchArea (final TouchAreaSpecs specs) {
		return new RedTouchArea(specs, this.master);
	}

	@Override
	public TouchAreaSpecs newTouchAreaSpecs () {
		return new RedTouchAreaSpecs();
	}

	@Override
	public CustomInputSpecs newCustomInputSpecs () {
		return new InputSpecs();
	}

	@Override
	public CustomInput newCustomInput (final CustomInputSpecs specs) {
		return new RedCustomInput(specs, this.master);
	}
}
