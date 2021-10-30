package org.jetbrains.plugins.template

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent

class EditPathAction : AnAction() {
    override fun update(e: AnActionEvent) {
        val presentation = e.presentation
        presentation.isEnabledAndVisible = true
    }

    override fun actionPerformed(e: AnActionEvent) {
        val panel = EditPathPanel(e.project!!)
        panel.setResizable(false)
        panel.show()
    }
}
