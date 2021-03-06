package com.sksamuel.jqm4gwt.form.elements;

import com.google.gwt.dom.client.Document;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.TextBox;
import com.sksamuel.jqm4gwt.HasMini;
import com.sksamuel.jqm4gwt.form.JQMFieldContainer;
import com.sksamuel.jqm4gwt.html.FormLabel;

/**
 * @author Stephen K Samuel samspade79@gmail.com 10 May 2011 00:24:06
 *         <p/>
 *         An implementation of a jquery mobile "slider" widget.
 *         <p/>
 *         For further info see
 * @link http://jquerymobile.com/demos/1.0b1/#/demos/1.0b1/docs/forms/forms-slider.html
 */
public class JQMSlider extends JQMFieldContainer implements HasValue<Integer>, HasMini {

    private final FormLabel label = new FormLabel();

    /**
     * The input to use as the base element
     */
    private final TextBox input = new TextBox();

    /**
     * Create a new {@link JQMSlider} with the given label and default values
     * for the min and max
     *
     * @param text the label text
     */
    public JQMSlider(String text) {
        String id = Document.get().createUniqueId();

        label.setFor(id);
        label.setText(text);

        input.getElement().setId(id);
        input.getElement().setAttribute("type", "range");
        input.getElement().setAttribute("value", "0");
        input.getElement().setAttribute("min", "0");
        input.getElement().setAttribute("max", "100");

        add(label);
        add(input);
    }

    /**
     * Create a new {@link JQMSlider} with the given label and min and max
     * values
     *
     * @param text the label text
     * @param min  the minimum value of the slider
     * @param max  the maximum value of the slider
     */
    public JQMSlider(String text, int min, int max) {
        this(text);
        setMax(max);
        setMin(min);
    }

    @Override
    public HandlerRegistration addValueChangeHandler(ValueChangeHandler<Integer> handler) {
        return input.addValueChangeHandler(new ValueChangeHandler<String>() {

            @Override
            public void onValueChange(ValueChangeEvent<String> event) {
                ValueChangeEvent.fire(JQMSlider.this, getValue());
            }
        });
    }

    public void disable() {
        disable(input.getElement().getId());
    }

    private native void disable(String id)/*-{
                                $("#"+id).slider('disable');
								}-*/;

    public void enable() {
        enable(input.getElement().getId());
    }

    private native void enable(String id) /*-{
                                $("#"+id).slider('enable');
								}-*/;

    /**
     * Returns the max value of the slider
     */
    public int getMax() {
        return Integer.parseInt(input.getElement().getAttribute("max"));
    }

    /**
     * Returns the min value of the slider
     */
    public int getMin() {
        return Integer.parseInt(input.getElement().getAttribute("min"));
    }

    @Override
    public String getTheme() {
        return input.getElement().getAttribute("data-theme");
    }

    public String getTrackTheme() {
        return input.getElement().getAttribute("data-tracktheme");
    }

    /**
     * returns the current value of the slider
     */
    @Override
    public Integer getValue() {
        return Integer.parseInt(getValue(getId()));
    }

    private native String getValue(String id) /*-{
                                    return $wnd.$("#" + name).attr("value");
									}-*/;

    public boolean isHighlight() {
        return "true".equals(getAttribute("data-highligh"));
    }

    @Override
    public boolean isMini() {
        return "true".equals(getAttribute("data-mini"));
    }

    private native void refresh(String id, int value) /*-{
										$wnd.$("#" + name).val(value).slider("refresh");
										}-*/;

    public void setHighlight(boolean highlight) {
        setAttribute("data-highlight", String.valueOf(highlight));
    }

    /**
     * Sets the new max range for the slider.
     *
     * @param max the new max range
     */
    public void setMax(int max) {
        input.getElement().setAttribute("max", String.valueOf(max));
    }

    /**
     * Sets the new min range for the slider
     *
     * @param min the new min range
     */
    public void setMin(int min) {
        input.getElement().setAttribute("min", String.valueOf(min));
    }

    /**
     * If set to true then renders a smaller version of the standard-sized element.
     */
    @Override
    public JQMSlider setMini(boolean mini) {
        setAttribute("data-mini", String.valueOf(mini));
        return this;
    }


    /**
     * Sets the theme swatch for the slider
     */
    public void setTrackTheme(String theme) {
        input.getElement().setAttribute("data-track-theme", theme);
    }

    /**
     * Sets the value of the slider to the given value
     *
     * @param value the new value of the slider, must be in the range of the
     *              slider
     */
    @Override
    public void setValue(Integer value) {
        setValue(value, false);
    }

    /**
     * Sets the value of the slider to the given value
     *
     * @param value the new value of the slider, must be in the range of the
     *              slider
     */
    @Override
    public void setValue(Integer value, boolean ignored) {
        getValue(input.getElement().getId());
        input.getElement().setAttribute("value", String.valueOf(value));
        refresh(getId(), value);
    }
}
