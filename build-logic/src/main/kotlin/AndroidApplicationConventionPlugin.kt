import com.android.build.gradle.internal.dsl.BaseAppModuleExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.jetbrains.kotlin.gradle.plugin.KaptExtension

class AndroidApplicationConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            // 设置通用插件
            with(pluginManager) {
                apply("com.android.application")
                apply("org.jetbrains.kotlin.android")
                apply("org.jetbrains.kotlin.kapt")
                apply("kotlin-parcelize")
            }

            // 设置android 配置文件
            this.extensions.configure<BaseAppModuleExtension> {
                configureAndroidApplication(this, this@with)
            }

            // 依赖
            dependencies {

            }

            // Kapt
            val kaptExtension = extensions.getByType(KaptExtension::class.java)
            kaptExtension.apply {
                correctErrorTypes = true
            }
        }
    }
}