package com.github.selcukguvel.fastimport.services

import com.intellij.openapi.project.Project
import com.github.selcukguvel.fastimport.MyBundle

class MyProjectService(project: Project) {

    init {
        println(MyBundle.message("projectService", project.name))
    }
}
