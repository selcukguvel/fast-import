package org.jetbrains.plugins.template

import com.intellij.ide.util.PropertiesComponent
import com.intellij.openapi.project.Project

object PathStorageService {
    private const val pathKey = "com.github.selcukguvel.fastimport.path";

    fun getPath(project: Project): String {
        return PropertiesComponent.getInstance(project).getValue(pathKey) ?: ""
    }

    fun setPath(path: String, project: Project) {
        PropertiesComponent.getInstance(project).setValue(pathKey, path)
    }
}
