package org.jetbrains.plugins.template

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.command.CommandProcessor
import com.intellij.openapi.actionSystem.CommonDataKeys
import com.intellij.openapi.application.Application
import com.intellij.openapi.application.ApplicationManager

class ImportPathAction : AnAction() {
    override fun update(e: AnActionEvent) {
        val presentation = e.presentation
        presentation.isEnabledAndVisible = true
    }

    override fun actionPerformed(e: AnActionEvent) {
        val path = PathStorageService.getPath(e.project!!)
        performImportPathAction(e, "${path}\n")
    }

    private fun performImportPathAction(e: AnActionEvent, path: String) {
        val application: Application = ApplicationManager.getApplication()
        val editor = e.getRequiredData(CommonDataKeys.EDITOR)
        CommandProcessor.getInstance().executeCommand(
            e.project,
            {
                application.runWriteAction {
                    val document = editor.document
                    document.insertString(0, path)
                }
            },
            "IMPORT PATH",
            null
        )
    }
}
