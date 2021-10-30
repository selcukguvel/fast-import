package org.jetbrains.plugins.template

import com.intellij.openapi.project.Project
import com.intellij.openapi.ui.DialogWrapper
import com.intellij.ui.components.JBScrollPane
import com.intellij.util.ui.JBUI
import org.jetbrains.plugins.template.style.Colors
import java.awt.Dimension
import java.awt.event.ActionEvent
import javax.swing.Action
import javax.swing.JComponent
import javax.swing.JPanel
import javax.swing.JScrollPane
import javax.swing.JTextArea
import javax.swing.ScrollPaneConstants

class EditPathPanel(private val project: Project) : DialogWrapper(true) {
    private val textPane: JTextArea = JTextArea()
    private val scrollPane: JScrollPane = JBScrollPane()

    init {
        init()
        title = "Edit Path"
    }

    override fun createCenterPanel(): JComponent {
        val panel = JPanel()
        panel.preferredSize = Dimension(480, 240)

        initTextPane()
        initScrollPane(panel.preferredSize)
        panel.add(scrollPane)

        return panel
    }

    private fun initTextPane() {
        textPane.size = Dimension(480, 240)
        textPane.margin = JBUI.insets(5)
        textPane.isEditable = true
        textPane.lineWrap = true
        textPane.wrapStyleWord = true
        textPane.background = Colors.editPathPanelBackground
        textPane.foreground = Colors.editPathPanelText
        textPane.caretColor = Colors.editPathPanelText
        textPane.text = PathStorageService.getPath(project)
    }

    private fun initScrollPane(size: Dimension) {
        scrollPane.viewport.add(textPane)
        scrollPane.preferredSize = size
        scrollPane.verticalScrollBarPolicy = ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS
        scrollPane.horizontalScrollBarPolicy = ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER
    }

    override fun createActions(): Array<Action> {
        return arrayOf(SavePathAction())
    }

    private inner class SavePathAction : DialogWrapperAction("Save") {
        init {
            putValue(DEFAULT_ACTION, true)
        }

        override fun doAction(e: ActionEvent) {
            PathStorageService.setPath(textPane.text, project)
            close(OK_EXIT_CODE)
        }
    }
}