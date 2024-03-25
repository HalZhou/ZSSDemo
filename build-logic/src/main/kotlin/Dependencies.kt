import org.gradle.api.artifacts.dsl.DependencyHandler

/**
 * androidx - ui
 */
fun DependencyHandler.appcompat() {
    implementation(VersionAndroidX.appcompat)
    implementation(VersionAndroidX.supportV4)
    implementation(VersionAndroidX.coreKtx)
    implementation(VersionAndroidX.activityKtx)
    implementation(VersionAndroidX.fragmentKtx)
}

/**
 * Hilt依赖组
 */
fun DependencyHandler.hint() {
    implementation(VersionAndroidX.Hilt.hiltAndroid)
    kapt(VersionAndroidX.Hilt.hiltCompiler)
}

/**
 * Lifecycle 依赖组
 */
fun DependencyHandler.lifecycle() {
    implementation(VersionAndroidX.Lifecycle.liveDataKtx)
    implementation(VersionAndroidX.Lifecycle.runtimeKtx)

    implementation(VersionAndroidX.Lifecycle.viewModelKtx)
    implementation(VersionAndroidX.Lifecycle.viewModelSavedState)

    kapt(VersionAndroidX.Lifecycle.compiler)
}

/**
 * 协程
 */
fun DependencyHandler.coroutine() {
    implementation(VersionKotlin.Coroutines.android)
    implementation(VersionKotlin.Coroutines.core)
}

/**
 * 测试
 */
fun DependencyHandler.test() {
    implementation(VersionTesting.junit)
    implementation(VersionTesting.junitExt)
    implementation(VersionTesting.espresso)
}

/**
 * 路由
 */
fun DependencyHandler.router() {
    implementation(VersionThirdPart.ARouter.core)
    kapt(VersionThirdPart.ARouter.compiler)
}

/**
 * work任务
 */
fun DependencyHandler.work() {
    implementation(VersionAndroidX.Work.runtime)
    implementation(VersionAndroidX.Work.runtime_ktx)
}

/**
 * 本地存储
 */
fun DependencyHandler.datastore() {
    implementation(VersionAndroidX.DataStore.preferences)
    implementation(VersionAndroidX.DataStore.core)
}

/**
 * 网络请求
 */
fun DependencyHandler.retrofit() {
    implementation(VersionThirdPart.Retrofit.core)
    implementation(VersionThirdPart.Retrofit.convertGson)
    implementation(VersionThirdPart.Retrofit.gson)
    implementation(VersionThirdPart.gsonFactory)
}

//多媒体相机相册
fun DependencyHandler.imageSelector() {
    implementation(VersionThirdPart.ImageSelector.core)
    implementation(VersionThirdPart.ImageSelector.compress)
    implementation(VersionThirdPart.ImageSelector.ucrop)
}

//弹窗
fun DependencyHandler.xpopup() {
    implementation(VersionThirdPart.XPopup.core)
    implementation(VersionThirdPart.XPopup.picker)
    implementation(VersionThirdPart.XPopup.easyAdapter)
}

//下拉刷新
fun DependencyHandler.refresh() {
    implementation(VersionThirdPart.SmartRefresh.core)
    implementation(VersionThirdPart.SmartRefresh.classicsHeader)
}