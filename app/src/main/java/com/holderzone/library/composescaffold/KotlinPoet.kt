package com.holderzone.library.composescaffold

import android.os.Bundle
import android.widget.FrameLayout
import com.alibaba.android.arouter.facade.annotation.Route
import com.holderzone.library.composescaffold.base.BaseRepository
import com.holderzone.library.composescaffold.base.mvvm.BaseViewModel
import com.holderzone.library.composescaffold.network.ApiHelper
import com.squareup.kotlinpoet.*
import com.squareup.kotlinpoet.ParameterizedTypeName.Companion.parameterizedBy
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.HiltViewModel
import java.io.File
import javax.inject.Inject


val moduleName = "Card"
val packageName = "com.holderzone.library.composescaffold.ui.${moduleName.firstCharLowerCase()}"
val path = ".\\app\\src\\main\\java"


fun main() {
    buildSource().writeTo(File(path))
    buildRepository().writeTo(File(path))
    buildViewModel().writeTo(File(path))
    buildActivity().writeTo(File(path))
}


fun buildActivity(): FileSpec {

    val viewModel = ClassName(packageName, "${moduleName.firstCharUpCase()}ViewModel")
    val baseWebViewActivity =  ClassName("com.holderzone.library.composescaffold.base", "HolderVMActivity")

    return FileSpec.builder(packageName, "${moduleName.firstCharUpCase()}Activity")
        .addImport("androidx.lifecycle","ViewModelProvider")
        .addType(
            TypeSpec.classBuilder("${moduleName.firstCharUpCase()}Activity")
                .addAnnotation(AndroidEntryPoint::class)
                .addAnnotation(
                    AnnotationSpec.builder(Route::class)
                        .addMember("path = %S",  "/aio/${moduleName.firstCharLowerCase()}")
                        .build()
                )
                .superclass(baseWebViewActivity.parameterizedBy(viewModel))

                .addFunction(
                    FunSpec.builder("onCreate")
                        .addModifiers(KModifier.OVERRIDE)
                        .addParameter(
                            "savedInstanceState",
                            Bundle::class.asClassName().copy(nullable = true)
                        )
                        .addStatement("super.onCreate(savedInstanceState)")
                        .build()

                )

                .addFunction(
                    FunSpec.builder("initData")
                        .addModifiers(KModifier.OVERRIDE)
                        .addModifiers(KModifier.PROTECTED)
                        .addParameter(
                            "savedInstanceState",
                            Bundle::class.asClassName().copy(nullable = true)
                        )
                        .build()

                )

                .addFunction(
                    FunSpec.builder("injectViewModel")
                        .returns(viewModel)
                        .addModifiers(KModifier.OVERRIDE)
                        .addStatement("return ViewModelProvider(this)[${moduleName.firstCharUpCase()}ViewModel::class.java]")
                        .build()

                )
                .build()
        ).build()
}

fun buildViewModel(): FileSpec {

    val repository = ClassName(packageName, "${moduleName.firstCharUpCase()}Repository")

    return FileSpec.builder(packageName, "${moduleName.firstCharUpCase()}ViewModel")
        .addType(
            TypeSpec.classBuilder("${moduleName.firstCharUpCase()}ViewModel")
                .addAnnotation(HiltViewModel::class)
                .superclass(BaseViewModel::class)
                .primaryConstructor(
                    FunSpec.constructorBuilder()
                        .addAnnotation(Inject::class)
                        .addParameter("repository", repository)
                        .build()
                )
                .build()
        )
        .build()
}

fun buildSource(): FileSpec {
    return FileSpec.builder(packageName, "${moduleName.firstCharUpCase()}Source")
        .addType(
            TypeSpec.classBuilder("${moduleName.firstCharUpCase()}Source")
                .primaryConstructor(
                    FunSpec.constructorBuilder()
                        .addAnnotation(Inject::class)
                        .addParameter("apiHelper", ApiHelper::class)
                        .build()
                )
                .build()
        )
        .build()
}

fun buildRepository(): FileSpec {

    val source = ClassName(packageName, "${moduleName.firstCharUpCase()}Source")

    return FileSpec.builder(packageName, "${moduleName.firstCharUpCase()}Repository")
        .addType(
            TypeSpec.classBuilder("${moduleName.firstCharUpCase()}Repository")
                .primaryConstructor(
                    FunSpec.constructorBuilder()
                        .addAnnotation(Inject::class)
                        .addParameter("source", source)
                        .build()
                )
                .superclass(BaseRepository::class)
                .build()
        )
        .build()
}

fun String.firstCharLowerCase(): String {
    val result = this.toCharArray()
    result[0] = result[0].toLowerCase()
    return result.concatToString()
}

fun String.firstCharUpCase(): String {
    val result = this.toCharArray()
    result[0] = result[0].toUpperCase()
    return result.concatToString()
}