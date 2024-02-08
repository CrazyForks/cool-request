package com.cool.request.action.copy;

import com.cool.request.common.icons.CoolRequestIcons;
import com.cool.request.utils.ClipboardUtils;
import com.cool.request.utils.ResourceBundleUtils;
import com.cool.request.view.main.MainTopTreeView;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.ui.treeStructure.SimpleTree;
import com.intellij.util.ui.tree.TreeUtil;
import org.jetbrains.annotations.NotNull;

import javax.swing.tree.TreePath;

public class CopyMethodNameAnAction extends AnAction {
    private final SimpleTree simpleTree;

    public CopyMethodNameAnAction(MainTopTreeView mainTopTreeView) {
        super(ResourceBundleUtils.getString("http.method"));
        getTemplatePresentation().setIcon(CoolRequestIcons.IC_METHOD);
        this.simpleTree = ((SimpleTree) mainTopTreeView.getTree());
    }

    @Override
    public void actionPerformed(@NotNull AnActionEvent e) {
        TreePath selectedPathIfOne = TreeUtil.getSelectedPathIfOne(this.simpleTree);
        if (selectedPathIfOne != null && selectedPathIfOne.getLastPathComponent() instanceof MainTopTreeView.RequestMappingNode) {
            MainTopTreeView.RequestMappingNode requestMappingNode = (MainTopTreeView.RequestMappingNode) selectedPathIfOne.getLastPathComponent();
            ClipboardUtils.copyToClipboard(requestMappingNode.getData().getHttpMethod());
        }
    }
}