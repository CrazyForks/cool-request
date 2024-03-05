package com.cool.request.action.actions;

import com.cool.request.common.icons.CoolRequestIcons;
import com.cool.request.common.icons.KotlinCoolRequestIcons;
import com.cool.request.utils.ResourceBundleUtils;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.ToggleAction;
import org.jetbrains.annotations.NotNull;

public class ShowMarkNodeAnAction extends DynamicIconToggleActionButton {
    private final MakeSelectedListener makeSelectedListener;
    private boolean isSelected = false;

    public ShowMarkNodeAnAction(MakeSelectedListener makeSelectedListener) {
        super(()-> ResourceBundleUtils.getString("mark"), KotlinCoolRequestIcons.INSTANCE.getMARK());
        this.makeSelectedListener = makeSelectedListener;
    }

    @Override
    public boolean isSelected(@NotNull AnActionEvent e) {
        return isSelected;
    }

    @Override
    public void setSelected(@NotNull AnActionEvent e, boolean state) {
        isSelected = !isSelected;
        makeSelectedListener.setMarkSelected(e, state);
    }

    public static interface MakeSelectedListener {
        public void setMarkSelected(@NotNull AnActionEvent e, boolean state);
    }
}
