import dependencies.UnitTestDependencies.Companion.unitTest
import modules.LibraryModule
import modules.LibraryType

val module = LibraryModule(rootDir, LibraryType.Android)

apply(from = module.script())

plugins {
    id(BuildPlugins.Ids.androidLibrary)
}

dependencies {

    implementation(project(":domain"))
    implementation(Libraries.coroutinesCore)
    implementation(Libraries.kodein)

    testImplementation(project(":coroutines-testutils"))

    unitTest {
        forEachDependency { testImplementation(it) }
    }
}