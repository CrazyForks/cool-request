package com.hxl.plugin.springboot.invoke.action;

import com.hxl.plugin.springboot.invoke.plugin.apifox.ApiFoxExport;
import com.hxl.plugin.springboot.invoke.view.main.MainTopTreeView;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.ui.treeStructure.SimpleTree;
import icons.MyIcons;
import org.jetbrains.annotations.NotNull;

public class ApipostExportAnAction extends AnAction {
    private final SimpleTree simpleTree;
    private final ApiFoxExport apifoxExp = new ApiFoxExport();
    private MainTopTreeView mainTopTreeView;

    public ApipostExportAnAction(MainTopTreeView mainTopTreeView) {
        super("apipost", "apipost", MyIcons.APIFOX);
        this.simpleTree = ((SimpleTree) mainTopTreeView.getTree());
        this.mainTopTreeView = mainTopTreeView;
    }

    @Override
    public void actionPerformed(@NotNull AnActionEvent e) {
//        CursorUtils.setWait(simpleTree);
//        boolean result = apifoxExp.canExport();
//        CursorUtils.setDefault(simpleTree);
//        if (!result) {
//            apifoxExp.showCondition();
//            return;
//        }
//        List<RequestMappingModel> requestMappingModels = mainTopTreeView.getSelectRequestMappings();
//        if (requestMappingModels.size()==0){
//            Messages.showErrorDialog("无可导出得接口","提示");
//            return;
//        }
//        apifoxExp.export(OpenApiUtils.toOpenApiJson(requestMappingModels.stream()
//                .distinct()
//                .collect(Collectors.toList())));
    }
}
