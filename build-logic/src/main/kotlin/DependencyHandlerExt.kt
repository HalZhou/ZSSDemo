import org.gradle.api.artifacts.dsl.DependencyHandler

fun DependencyHandler.api(dependency: Any) {
    this.add("api",dependency)
}

fun DependencyHandler.implementation(dependency : Any) {
    this.add("implementation",dependency)
}

fun DependencyHandler.debugImplementation(dependency: Any) {
    this.add("debugImplementation",dependency)
}

fun DependencyHandler.testImplementation(dependency: Any) {
    this.add("testImplementation",dependency)
}

fun DependencyHandler.androidTestImplementation(dependency: Any) {
    this.add("androidTestImplementation",dependency)
}

fun DependencyHandler.compileOnly(dependency: Any) {
    this.add("compileOnly",dependency)
}

fun DependencyHandler.kapt(dependency: Any) {
    this.add("kapt",dependency)
}

